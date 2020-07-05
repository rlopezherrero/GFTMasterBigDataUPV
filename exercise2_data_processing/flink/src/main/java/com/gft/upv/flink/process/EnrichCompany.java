package com.gft.upv.flink.process;

import java.util.List;
import java.util.Map;

import org.apache.avro.Schema.Field;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gft.upv.config.AppConfig;
import com.gft.upv.parser.CompaniesParser;

public class EnrichCompany extends ProcessFunction<ObjectNode, ObjectNode> {
    private static final Logger logger = LoggerFactory.getLogger(EnrichCompany.class);
    private AppConfig appConfig;
     
	public EnrichCompany(AppConfig appConfig) {
        this.appConfig = appConfig;
     }
	
	
	@Override
    public void processElement(ObjectNode quote, Context ctx, Collector<ObjectNode> collector) throws Exception {
     
		Map<String,ObjectNode> companiesMap=CompaniesParser.getCompaniesMap();
		
		//TODO Exercise 2 get company static data from the map and add those fields to quoteEnriched
    	
		
        collector.collect(quote);
        
    }
}