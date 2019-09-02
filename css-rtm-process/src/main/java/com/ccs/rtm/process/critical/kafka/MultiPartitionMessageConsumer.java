package com.ccs.rtm.process.critical.kafka;

import ccs.rtm.domain.entity.FrameData;
import com.ccs.rtm.process.critical.evaluation.AccidentEvaluator;
import com.ccs.rtm.process.critical.evaluation.DoorSensorEvaluator;
import com.ccs.rtm.process.critical.evaluation.PanicButtonEvaluator;
import com.ccs.rtm.process.critical.evaluation.TemperatureEvaluator;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

import java.util.concurrent.CountDownLatch;

import static com.ccs.rtm.process.critical.util.Constants.TOPIC_CCS_RTM;

public class MultiPartitionMessageConsumer {

//    public CountDownLatch countDownLatch0 = new CountDownLatch(10);
//    public CountDownLatch countDownLatch1 = new CountDownLatch(10);
//    public CountDownLatch countDownLatch2 = new CountDownLatch(10);

    @Autowired
    private AccidentEvaluator accidentEvaluator;

    @Autowired
    private DoorSensorEvaluator doorSensorEvaluator;

    @Autowired
    private PanicButtonEvaluator panicButtonEvaluator;

    @Autowired
    private TemperatureEvaluator temperatureEvaluator;

    @KafkaListener(id = "id0", topicPartitions = { @TopicPartition(topic = TOPIC_CCS_RTM, partitions = { "0" }) })
    public void listenPartition0(ConsumerRecord<String, FrameData> record) throws InterruptedException {

//        System.out.println("Listener Id0, Thread ID: " + Thread.currentThread().getId());
        evaluateFrame(record);
//        countDownLatch0.countDown();
    }



    @KafkaListener(id = "id1", topicPartitions = { @TopicPartition(topic = TOPIC_CCS_RTM, partitions = { "1" }) })
    public void listenPartition1(ConsumerRecord<String, FrameData> record) throws InterruptedException {

//        System.out.println("Listener Id1, Thread ID: " + Thread.currentThread().getId());
        evaluateFrame(record);
//        countDownLatch1.countDown();
    }

    @KafkaListener(id = "id2", topicPartitions = { @TopicPartition(topic = TOPIC_CCS_RTM, partitions = { "2" }) })
    public void listenPartition2(ConsumerRecord<String, FrameData> record) throws InterruptedException {

//        System.out.println("Listener Id2, Thread ID: " + Thread.currentThread().getId());
        evaluateFrame(record);
//        countDownLatch2.countDown();
    }


    private void evaluateFrame(ConsumerRecord<String, FrameData> record) throws InterruptedException {
        accidentEvaluator.evaluateVariable(record);
        doorSensorEvaluator.evaluateVariable(record);
        panicButtonEvaluator.evaluateVariable(record);
        temperatureEvaluator.evaluateVariable(record);
    }


}
