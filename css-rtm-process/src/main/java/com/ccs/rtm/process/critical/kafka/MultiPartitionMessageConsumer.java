package com.ccs.rtm.process.critical.kafka;

import ccs.rtm.domain.entity.FrameData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.concurrent.CountDownLatch;

public class MultiPartitionMessageConsumer {
    public CountDownLatch countDownLatchs = new CountDownLatch(1);
    public CountDownLatch countDownLatch0 = new CountDownLatch(3);
    public CountDownLatch countDownLatch1 = new CountDownLatch(3);
    public CountDownLatch countDownLatch2 = new CountDownLatch(3);

    @KafkaListener(id = "id3", topicPartitions = { @TopicPartition(topic = "SingleKafkaTopic", partitions = { "0" }) })
    public void listenPartitionSingle(ConsumerRecord<String, FrameData> record ) throws InterruptedException {
        System.out.println("Listener Single ------>, Thread ID: " + Thread.currentThread().getId());
        System.out.println("Received: " + record);
        countDownLatchs.countDown();
    }

    @KafkaListener(id = "id0", topicPartitions = { @TopicPartition(topic = "SpringKafkaTopic", partitions = { "0" }) })
    public void listenPartition0(ConsumerRecord<String, FrameData> record) throws InterruptedException {


        System.out.println("Listener Id0, Thread ID: " + Thread.currentThread().getId());
        System.out.println("Received: " + record);
        countDownLatch0.countDown();
    }

    @KafkaListener(id = "id1", topicPartitions = { @TopicPartition(topic = "SpringKafkaTopic", partitions = { "1" }) })
    public void listenPartition1(ConsumerRecord<String, FrameData> record) throws InterruptedException {
        System.out.println("Listener Id1, Thread ID: " + Thread.currentThread().getId());
        System.out.println("Received: " + record);
        countDownLatch1.countDown();
    }

    @KafkaListener(id = "id2", topicPartitions = { @TopicPartition(topic = "SpringKafkaTopic", partitions = { "2" }) })
    public void listenPartition2(ConsumerRecord<String, FrameData> record) throws InterruptedException {
        System.out.println("Listener Id2, Thread ID: " + Thread.currentThread().getId());
        System.out.println("Received: " + record.toString());
        countDownLatch2.countDown();
    }
}
