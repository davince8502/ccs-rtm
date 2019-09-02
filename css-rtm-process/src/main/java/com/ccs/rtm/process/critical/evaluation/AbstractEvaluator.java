package com.ccs.rtm.process.critical.evaluation;

import ccs.rtm.domain.entity.FrameData;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Date;

public abstract class  AbstractEvaluator {

    abstract  protected void evaluateVariable(ConsumerRecord<String, FrameData> record) throws InterruptedException;

    protected void printDuration(ConsumerRecord<String, FrameData> record) throws InterruptedException {


        final long now = new Date().getTime();
        long diffInMillies = now - ((FrameData) record.value()).getDateArrive();

//        System.out.println("--> DUration: " + diffInMillies + " --> Received Data: " + record );
        System.out.println("--> DUration: " + diffInMillies + " --> Received Data: "+ ((FrameData)record.value()).getDeviceId() );
    }


}
