package com.gft.upv.flink.process;

import java.util.List;
import java.util.Map;

import org.apache.avro.Schema.Field;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gft.upv.config.AppConfig;
import com.gft.upv.parser.CompaniesParser;

public class EnrichCompany extends ProcessFunction<GenericRecord, GenericRecord> {
    private static final Logger logger = LoggerFactory.getLogger(EnrichCompany.class);
    private AppConfig appConfig;
     
	public EnrichCompany(AppConfig appConfig) {
        this.appConfig = appConfig;
     }
	
	
	@Override
    public void processElement(GenericRecord quote, Context ctx, Collector<GenericRecord> collector) throws Exception {
     
		Map<String,GenericRecord> companiesMap=CompaniesParser.getCompaniesMap(appConfig.getSchemaConf());
		GenericRecord quoteEnriched = new GenericData.Record(this.appConfig.getSchemaConf().getQuoteSchema());
      	      	
		List<Field> quoteFields=quote.getSchema().getFields();
		for(Field field:quoteFields) {
			quoteEnriched.put(field.name(), quote.get(field.name()));
		}
		
		//TODO Exercise 2 get company static data from the map and add those fields to quoteEnriched
    	
		
        collector.collect(quoteEnriched);
        
    }
}