package com.ccs.rtm.process.critical.evaluation;

import ccs.rtm.domain.entity.FrameData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TemperatureEvaluator extends AbstractEvaluator {

    @Override
    @Async
    public void evaluateVariable(ConsumerRecord<String, FrameData> record) throws InterruptedException {

       Thread.sleep(10);


//        System.out.println("TemperatureEvaluator Executing thread name - " + Thread.currentThread().getName());

        this.printDuration(record);

    }
}
