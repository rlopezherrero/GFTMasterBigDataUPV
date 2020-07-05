package com.gft.upv.flink.process;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gft.upv.config.AppConfig;

public class FilterCompanies implements FilterFunction<ObjectNode> {
    private static final Logger logger = LoggerFactory.getLogger(FilterCompanies.class);
    private AppConfig appConfig;
   
	public FilterCompanies(AppConfig appConfig) {
        this.appConfig = appConfig;
     }

	@Override
	public boolean filter(ObjectNode value) throws Exception {
		// TODO Exercise 2 Filter elements checking symbol field
		// Hint, value is on Utf8
		
		return true;
	}
	
	
}