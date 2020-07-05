package com.gft.upv.parser;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CompaniesParser {

    private static final Logger logger = LoggerFactory.getLogger(CompaniesParser.class);
    private static Map<String,ObjectNode> companiesMap;
    
    public static Map<String,ObjectNode> getCompaniesMap() throws Exception {
    	if(companiesMap==null)
    		companiesMap=parseCompaniesJson();
    	return companiesMap;
    }
    
    private static Map<String,ObjectNode> parseCompaniesJson() throws Exception {
    	Path path = FileSystems.getDefault().getPath("src\\main\\resources\\companies.json");
    	List<String> companiesStaticData = Files.readAllLines(path);	       	
           	 
    	HashMap<String,ObjectNode> companiesMap = new HashMap<String,ObjectNode>(); 
    	
    	ObjectMapper mapper = new ObjectMapper();
    	 
    	for(String companyJson:companiesStaticData) {
    		ObjectNode company=mapper.readValue(companyJson,ObjectNode.class);
    		companiesMap.put(company.get("symbol").asText(), company);
    	}
    	
    	
    	return companiesMap;		        
    }
}


