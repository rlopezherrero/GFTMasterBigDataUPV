package com.gft.upv.serde;


import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;

import com.gft.upv.serde.ISerdeSchemaOption;

import java.io.IOException;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;


public class GenericSchemaRegistrySerdeSchema extends ISerdeSchemaOption<GenericRecord> {
    private String topic;
    transient private KafkaAvroDeserializer valueDeserializer;
    transient private KafkaAvroSerializer valueSerializer;
    private String schemaRegistryUrl;

    public GenericSchemaRegistrySerdeSchema(String schemaRegistryUrl){
        this.schemaRegistryUrl = schemaRegistryUrl;
    }

    public GenericSchemaRegistrySerdeSchema(String schemaRegistryUrl, String topic){
        this.schemaRegistryUrl = schemaRegistryUrl;
        this.topic = topic;
    }

    @Override
    public GenericRecord deserialize(byte[] bytes) throws IOException {
        if(this.valueDeserializer == null) {
            this.valueDeserializer = new KafkaAvroDeserializer();
            Map<String, String> config = new HashMap<>();
            config.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,schemaRegistryUrl);
            /*Generic = false*/
            this.valueDeserializer.configure(config, false);
        }
        try{
         return  (GenericRecord) valueDeserializer.deserialize("1", bytes); //it depends on the key value, surely it will be string
        }
        catch(Exception e) {
            if (e.getCause() instanceof ConnectException)
                throw new ConnectException(
                        String.format("Connection to schema registry '%s' could not be established.",
                                schemaRegistryUrl
                        )
                );
            else throw e;
        }
    }
    @Override
    public byte[] serialize(GenericRecord element) {
        if (this.valueSerializer == null) {
            this.valueSerializer = new KafkaAvroSerializer();
            Map<String, String> config = new HashMap<>();
            config.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,schemaRegistryUrl);
            this.valueSerializer.configure(config, false);
        }
        return this.valueSerializer.serialize(topic, element);
    }
    @Override
    public boolean isEndOfStream(GenericRecord t) {
        return false;
    }

    @Override
    public TypeInformation<GenericRecord> getProducedType() {
        return TypeExtractor.getForClass(GenericRecord.class);
    }
}
