package io.inceptor.drl.drl.datasource.impl;

import io.inceptor.drl.drl.datasource.AbstractDatasource;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.record.RecordMetaData;

import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaDatasource extends AbstractDatasource {
    private String name;

    private Properties properties;
    private Producer<Object, Object> producer;
    private boolean inited = false;

    public KafkaDatasource(String name, Properties properties) {
        this.name = name;
        this.properties = properties;
    }

    @Override
    public void init() {
        if (inited)
            return;
        producer = new KafkaProducer<Object, Object>(properties);
    }

    @Override
    public void close() {
        if (inited)
            producer.close();
        inited = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Future<RecordMetaData> putMsg(String topic, Integer partition, long timestamp, Object key, Object msg) {
        final ProducerRecord record = new ProducerRecord(topic, partition, timestamp, key, msg);
        return producer.send(record);
    }

    @Override
    public Future<RecordMetaData> putMsg(String topic, Integer partition, Object key, Object msg) {
        final ProducerRecord record = new ProducerRecord(topic, partition, key, msg);
        return producer.send(record);
    }

    @Override
    public Future<RecordMetaData> putMsg(String topic, Object key, Object msg) {
        final ProducerRecord record = new ProducerRecord(topic, key, msg);
        return producer.send(record);
    }

    @Override
    public Future<RecordMetaData> putMsg(String topic, Object msg) {
        final ProducerRecord record = new ProducerRecord(topic, msg);
        return producer.send(record);
    }


}
