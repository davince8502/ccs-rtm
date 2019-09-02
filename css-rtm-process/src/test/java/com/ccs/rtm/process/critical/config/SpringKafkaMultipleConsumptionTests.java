package com.ccs.rtm.process.critical.config;

import ccs.rtm.domain.entity.FrameData;
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
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private MultiPartitionMessageConsumer multiPartitionMessageConsumer;

    @Test
    public void contextLoads() throws InterruptedException {

        System.out.println("INICIO --------------> SingleKafkaTopic " +java.time.LocalTime.now());

        for (int i = 0; i <3000; i++) {

            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("SingleKafkaTopic",
                     new FrameData().toBuilder()
                            .deviceId("device-"+i)
                            .accidentSensor(1)
                            .doorSensor(0)
                            .panicButoom(0)
                            .temperature(45.5F)
                            .build());
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onSuccess(SendResult<String, Object> result) {
                   System.out.println("Sent message --------------> : SingleKafkaTopic" +java.time.LocalTime.now());
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Failed to send message");
                }
            });
        }

        assertThat(this.multiPartitionMessageConsumer.countDownLatchs.await(60, TimeUnit.SECONDS)).isTrue();

//        System.out.println("End TOPIC ---------------> : SingleKafkaTopic" +java.time.LocalTime.now());
//
//        System.out.println("INICIO --------------> SpringKafkaTopic " +java.time.LocalTime.now());
//
//        for (int i = 0; i < 9; i++) {
//            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("SpringKafkaTopic",
//                    new FrameData().toBuilder()
//                            .deviceId("device-"+i)
//                            .accidentSensor(2)
//                            .doorSensor(0)
//                            .panicButoom(0)
//                            .temperature(23.5F)
//                            .build());
//            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//                @Override
//                public void onSuccess(SendResult<String, Object> result) {
//                    System.out.println("Sent message --------------> : SpringKafkaTopic" +java.time.LocalTime.now());
//                }
//
//                @Override
//                public void onFailure(Throwable ex) {
//                    System.out.println("Failed to send message");
//                }
//            });
//        }
//
//        assertThat(this.multiPartitionMessageConsumer.countDownLatch0.await(60, TimeUnit.SECONDS)).isTrue();
//        assertThat(this.multiPartitionMessageConsumer.countDownLatch1.await(60, TimeUnit.SECONDS)).isTrue();
//        assertThat(this.multiPartitionMessageConsumer.countDownLatch2.await(60, TimeUnit.SECONDS)).isTrue();
//
//        System.out.println("End TOPIC --------------> : SpringKafkaTopic" +java.time.LocalTime.now());

        Thread.sleep(1000);
    }

}
