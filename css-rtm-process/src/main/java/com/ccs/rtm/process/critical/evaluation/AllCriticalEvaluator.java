package com.ccs.rtm.process.critical.evaluation;

import ccs.rtm.domain.entity.FrameData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AllCriticalEvaluator extends AbstractEvaluator {

    @Override
    @Async
    public void evaluateViariable(ConsumerRecord<String, FrameData> record) throws InterruptedException {

        Thread.sleep(10);
        System.out.println("AccidentEvaluation Executing thread name - " + Thread.currentThread().getName());

        Thread.sleep(10);
        System.out.println("DoorSensorEvaluation Executing thread name - " + Thread.currentThread().getName());


        Thread.sleep(10);
        System.out.println("PanicButtonEvaluation Executing thread name - " + Thread.currentThread().getName());

        Thread.sleep(10);
        System.out.println("TemperatureEvaluation Executing thread name - " + Thread.currentThread().getName());

        this.printDuration(record);
    }

}
