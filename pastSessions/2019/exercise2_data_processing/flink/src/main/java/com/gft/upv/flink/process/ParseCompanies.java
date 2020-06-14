package com.gft.upv.flink.process;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.minlog.Log;
import com.gft.upv.config.AppConfig;

public class ParseCompanies extends ProcessFunction<String, GenericRecord> {
    private static final Logger logger = LoggerFactory.getLogger(ParseCompanies.class);
    private AppConfig appConfig;
    private Schema schema;	
       public ParseCompanies(AppConfig appConfig) {
        this.appConfig = appConfig;
        try {
        Schema.Parser parser = new Schema.Parser();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("topic_avro_schemes/quote.avsc");
        schema = parser.parse(is);
        }
        catch(Exception ex) {
        	logger.error("Error reading companies avro schema ",ex);
        }
    
    }

    @Override
    public void processElement(String companyJson, Context ctx, Collector<GenericRecord> collector) throws Exception {
     
    	if(this.schema!=null) {
    	  InputStream input = new ByteArrayInputStream(companyJson.getBytes());
    	  DataInputStream din = new DataInputStream(input);
    	  
    	  Decoder decoder = DecoderFactory.get().jsonDecoder(schema, din);
    	
    	  DatumReader<GenericData.Record> reader =	new GenericDatumReader<>(schema);
    	  collector.collect(reader.read(null, decoder));
    	}
    	  
		        
    }
}