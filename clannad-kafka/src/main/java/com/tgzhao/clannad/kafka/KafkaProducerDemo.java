package com.tgzhao.clannad.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by tgzhao on 2016/10/11.
 */
public class KafkaProducerDemo
{

    private int total = 10000;

    public static void main( String[] args ) {
        new KafkaProducerDemo().send();
    }

    public void send(){

        long start = System.currentTimeMillis();
        System.out.println("Kafka Producer send msg start,total msgs:"+total);

        // set up the producer
        Producer<String, String> producer = null;
        try {
            //Properties props = PropertyUtils.load("producer_config.properties");
            Properties props = new Properties();
            props.put("bootstrap.servers", "10.32.82.156:9092,10.32.82.161:9092");
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("acks", "all");
            props.put("batch.size", "16384");


            producer = new KafkaProducer<>(props);

            for (int i = 0; i < total; i++){
                producer.send(new ProducerRecord<String, String>("hello",
                        String.valueOf(i), String.format("{\"type\":\"test\", \"t\":%d, \"k\":%d}", System.currentTimeMillis(), i)));

                // every so often send to a different topic
                if (i % 100 == 0) {
                    producer.send(new ProducerRecord<String, String>("test", String.format("{\"type\":\"marker\", \"t\":%d, \"k\":%d}", System.currentTimeMillis(), i)));
                    producer.send(new ProducerRecord<String, String>("hello", String.format("{\"type\":\"marker\", \"t\":%d, \"k\":%d}", System.currentTimeMillis(), i)));

                    producer.flush();
                    System.out.println("Sent msg number " + i);
                    Thread.sleep(1000);
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            producer.close();
        }

        System.out.println("Kafka Producer send msg over,cost time:"+(System.currentTimeMillis()-start)+"ms");
    }
}

