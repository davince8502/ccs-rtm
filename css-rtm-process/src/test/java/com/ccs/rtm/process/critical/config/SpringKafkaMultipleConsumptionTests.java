package com.ccs.rtm.process.critical.config;

import ccs.rtm.domain.entity.FrameData;
import com.ccs.rtm.process.critical.kafka.MultiPartitionMessageConsumer;
import com.ccs.rtm.process.critical.kafka.SingleMessageConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.ccs.rtm.process.critical.util.Constants.TOPIC_CCS_RTM;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaMultipleConsumptionTests {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private MultiPartitionMessageConsumer multiPartitionMessageConsumer;

    @Autowired
    private SingleMessageConsumer singleListener;

    @Test
    public void Test1SingleConsumerEvents() throws InterruptedException {

        System.out.println("INICIO --------------> SingleKafkaTopic " +java.time.LocalTime.now());

        Long inicio;

        for (int i = 0; i <10000; i++) {

            inicio = new Date().getTime();

            if(i%10 == 0 && i != 0){
                Thread.sleep(2);
            }

            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("SingleKafkaTopic",
                     new FrameData().toBuilder()
                            .deviceId("device-"+i)
                            .accidentSensor(i)
                            .doorSensor(i)
                            .panicButoom(i)
                            .temperature(0.2F+i)
                            .dateArrive(inicio)
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

//        assertThat(this.singleListener.countDownLatchs.await(60, TimeUnit.SECONDS)).isTrue();

        System.out.println("End TOPIC ---------------> : SingleKafkaTopic" +java.time.LocalTime.now());

        Thread.sleep(5000);

    }
    @Test
    public void Test2MultipleConsumerEvents() throws InterruptedException {


        System.out.println("INICIO --------------> "+ TOPIC_CCS_RTM +" " +java.time.LocalTime.now());

        Long inicio;

        for (int i = 0; i < 10000; i++) {

            inicio = new Date().getTime();

            if(i%10 == 0 && i != 0){
                Thread.sleep(2);
            }

            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_CCS_RTM,
                    new FrameData().toBuilder()
                            .deviceId("device-"+i)
                            .accidentSensor(i)
                            .doorSensor(i)
                            .panicButoom(i)
                            .temperature(0.2F+i)
                            .dateArrive(inicio)
                            .build());
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onSuccess(SendResult<String, Object> result) {
                    System.out.println("Sent message --------------> :  "+ TOPIC_CCS_RTM +" " +java.time.LocalTime.now());
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Failed to send message");
                }
            });
        }

//        assertThat(this.multiPartitionMessageConsumer.countDownLatch0.await(60, TimeUnit.SECONDS)).isTrue();
//        assertThat(this.multiPartitionMessageConsumer.countDownLatch1.await(60, TimeUnit.SECONDS)).isTrue();
//        assertThat(this.multiPartitionMessageConsumer.countDownLatch2.await(60, TimeUnit.SECONDS)).isTrue();

        System.out.println("End TOPIC --------------> : "+ TOPIC_CCS_RTM +" " +java.time.LocalTime.now());

        Thread.sleep(5000);
    }

}
