package com.ccs.rtm.process.critical.config;

import com.ccs.rtm.process.critical.kafka.MultiPartitionMessageConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaMultipleConsumptionTests {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private MultiPartitionMessageConsumer multiPartitionMessageConsumer;

    @Test
    public void contextLoads() throws InterruptedException {

        System.out.println("INICIO --------------> SingleKafkaTopic " +java.time.LocalTime.now());

        for (int i = 0; i < 30; i++) {
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("SingleKafkaTopic",
                    "Messsage:" + i);
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                   System.out.println("Sent message --------------> : SingleKafkaTopic" +java.time.LocalTime.now());
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Failed to send message");
                }
            });
        }

        assertThat(this.multiPartitionMessageConsumer.countDownLatchs.await(60, TimeUnit.SECONDS)).isTrue();

        System.out.println("End TOPIC ---------------> : SingleKafkaTopic" +java.time.LocalTime.now());

        System.out.println("INICIO --------------> SpringKafkaTopic " +java.time.LocalTime.now());

        for (int i = 0; i < 6; i++) {
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("SpringKafkaTopic",
                    "Messsage:" + i);
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    System.out.println("Sent message --------------> : SpringKafkaTopic" +java.time.LocalTime.now());
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Failed to send message");
                }
            });
        }

        assertThat(this.multiPartitionMessageConsumer.countDownLatch0.await(60, TimeUnit.SECONDS)).isTrue();
        assertThat(this.multiPartitionMessageConsumer.countDownLatch1.await(60, TimeUnit.SECONDS)).isTrue();
        assertThat(this.multiPartitionMessageConsumer.countDownLatch2.await(60, TimeUnit.SECONDS)).isTrue();

        System.out.println("End TOPIC --------------> : SpringKafkaTopic" +java.time.LocalTime.now());
    }

}
