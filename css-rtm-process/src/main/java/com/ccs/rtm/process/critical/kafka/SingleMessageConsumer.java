package com.ccs.rtm.process.critical.kafka;

import ccs.rtm.domain.entity.FrameData;
import com.ccs.rtm.process.critical.evaluation.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class SingleMessageConsumer {

//    public CountDownLatch countDownLatchs = new CountDownLatch(1);

    @Autowired
    private AllCriticalEvaluator allCriticalEvaluator;

    @Autowired
    private AccidentEvaluator accidentEvaluator;

    @Autowired
    private DoorSensorEvaluator doorSensorEvaluator;

    @Autowired
    private PanicButtonEvaluator panicButtonEvaluator;

    @Autowired
    private TemperatureEvaluator temperatureEvaluator;

    @KafkaListener(id = "id99", topicPartitions = { @TopicPartition(topic = "SingleKafkaTopic", partitions = { "0" }) })
    public void listenPartitionSingle(ConsumerRecord<String, FrameData> record ) throws InterruptedException {
        System.out.println("Listener Single ------>, Thread ID: " + Thread.currentThread().getId());


        //<<<<<<<<<< Llamar proceso secuencial de evaluacion >>>>>>>>>>>>

        allCriticalEvaluator.evaluateViariable(record);

        //<<<<<<<<<< Llamar procesos de evaluacion por separado >>>>>>>>>>>>

//        accidentEvaluator.evaluateViariable(record);
//        doorSensorEvaluator.evaluateViariable(record);
//        panicButtonEvaluator.evaluateViariable(record);
//        temperatureEvaluator.evaluateViariable(record);

//        countDownLatchs.countDown();
    }
}
