package com.gft.upv.flink.process;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.apache.flink.api.common.functions.FilterFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gft.upv.config.AppConfig;

public class FilterCompanies implements FilterFunction<GenericRecord> {
    private static final Logger logger = LoggerFactory.getLogger(FilterCompanies.class);
    private AppConfig appConfig;
   
	public FilterCompanies(AppConfig appConfig) {
        this.appConfig = appConfig;
     }

	@Override
	public boolean filter(GenericRecord value) throws Exception {
		// TODO Exercise 2 Filter elements checking symbol field
		// Hint, value is on Utf8
		
		return true;
	}
	
	
}