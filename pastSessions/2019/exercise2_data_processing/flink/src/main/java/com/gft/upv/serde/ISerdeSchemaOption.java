package com.gft.upv.serde;

import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.SerializationSchema;

public abstract class ISerdeSchemaOption<T> implements DeserializationSchema<T>,SerializationSchema<T> {
}
