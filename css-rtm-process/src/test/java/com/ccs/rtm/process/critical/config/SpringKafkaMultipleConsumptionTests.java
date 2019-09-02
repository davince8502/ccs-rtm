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

import static com.ccs.rtm.process.critical.util.Constants.TOPIC_CCS_RTM;
import static com.ccs.rtm.process.critical.util.Constants.TOPIC_SINGLE_CCS_RTM;

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
    public void Test1ProcessOneLoopMasiveDataframesSingleConsumerEvents() throws InterruptedException {

        System.out.println("INICIO --------------> " +TOPIC_SINGLE_CCS_RTM + " " +java.time.LocalTime.now());

        Long inicio;

        for (int i = 0; i <5000; i++) {

            inicio = new Date().getTime();

            if(i%10 == 0 && i != 0){
                Thread.sleep(2);
            }

            ListenableFuture<SendResult<String, Object>> future =
                    kafkaTemplate.send(TOPIC_SINGLE_CCS_RTM,
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
//                       System.out.println("Sent message --------------> : " +TOPIC_SINGLE_CCS_RTM + " " +java.time.LocalTime.now());
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Failed to send message");
                }
            });
        }

        System.out.println("End TOPIC ---------------> : " +TOPIC_SINGLE_CCS_RTM + " " +java.time.LocalTime.now());

        Thread.sleep(5000);

    }


    @Test
    public void Test2ProcessSeveralLoopsMasiveDataframesSingleConsumerEvents() throws InterruptedException {

        System.out.println("INICIO --------------> " +TOPIC_SINGLE_CCS_RTM + " " +java.time.LocalTime.now());

        for (int s = 0; s <30; s++) {

            Long inicio;

            for (int i = 0; i <5000; i++) {

                inicio = new Date().getTime();

                if(i%10 == 0 && i != 0){
                    Thread.sleep(2);
                }

                ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_SINGLE_CCS_RTM,
                        new FrameData().toBuilder()
                                .deviceId("device-"+s+i)
                                .accidentSensor(i)
                                .doorSensor(i)
                                .panicButoom(i)
                                .temperature(0.2F+i)
                                .dateArrive(inicio)
                                .build());
                future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                    @Override
                    public void onSuccess(SendResult<String, Object> result) {
//                       System.out.println("Sent message --------------> : " +TOPIC_SINGLE_CCS_RTM + " " +java.time.LocalTime.now());
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        System.out.println("Failed to send message");
                    }
                });
            }
        }

        System.out.println("End TOPIC ---------------> : " +TOPIC_SINGLE_CCS_RTM + " " +java.time.LocalTime.now());

        Thread.sleep(5000);

    }


    @Test
    public void Test3OneLoopMasiveDataframesMultipleConsumerEvents() throws InterruptedException {

        System.out.println("INICIO --------------> "+ TOPIC_CCS_RTM +" " +java.time.LocalTime.now());

            Long inicio;

            for (int i = 0; i < 50000; i++) {

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
    //                    System.out.println("Sent message --------------> :  "+ TOPIC_CCS_RTM +" " +java.time.LocalTime.now());
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        System.out.println("Failed to send message");
                    }
                });
        }

        System.out.println("End TOPIC --------------> : "+ TOPIC_CCS_RTM +" " +java.time.LocalTime.now());

        Thread.sleep(5000);
    }


    @Test
    public void Test4ProcessSeveralLoopsMasiveDataframesMultipleConsumerEvents() throws InterruptedException {

        System.out.println("INICIO --------------> "+ TOPIC_CCS_RTM +" " +java.time.LocalTime.now());

        for (int s = 0; s <30; s++) {

        Long inicio;

        for (int i = 0; i < 50000; i++) {

            inicio = new Date().getTime();

            if(i%10 == 0 && i != 0){
                Thread.sleep(2);
            }

            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_CCS_RTM,
                    new FrameData().toBuilder()
                            .deviceId("device-"+s+i)
                            .accidentSensor(i)
                            .doorSensor(i)
                            .panicButoom(i)
                            .temperature(0.2F+i)
                            .dateArrive(inicio)
                            .build());
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onSuccess(SendResult<String, Object> result) {
//                    System.out.println("Sent message --------------> :  "+ TOPIC_CCS_RTM +" " +java.time.LocalTime.now());
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Failed to send message");
                }
            });

         }
        }

        System.out.println("End TOPIC --------------> : "+ TOPIC_CCS_RTM +" " +java.time.LocalTime.now());
        Thread.sleep(5000);
    }
}
