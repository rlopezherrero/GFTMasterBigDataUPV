package com.gft.upv.parser;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.util.Utf8;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gft.upv.config.SchemaConfig;

public class CompaniesParser {

    private static final Logger logger = LoggerFactory.getLogger(CompaniesParser.class);
    private static Map<String,GenericRecord> companiesMap;
    
    public static Map<String,GenericRecord> getCompaniesMap(SchemaConfig schemaConfig) throws Exception {
    	if(companiesMap==null)
    		companiesMap=parseCompaniesJson(schemaConfig);
    	return companiesMap;
    }
    
    private static Map<String,GenericRecord> parseCompaniesJson(SchemaConfig schemaConfig) throws Exception {
    	Path path = FileSystems.getDefault().getPath("src"+File.separator+"main"+File.separator+"resources"+File.separator+"companies.json");
    	List<String> companiesStaticData = Files.readAllLines(path);	       	
           	 
    	HashMap<String,GenericRecord> companiesMap = new HashMap<String,GenericRecord>(); 
    	Schema companySchema = schemaConfig.getCompanySchema();
    	
    	DatumReader<GenericData.Record> reader=new GenericDatumReader<>(companySchema);
    	for(String companyJson:companiesStaticData) {
    		InputStream input = new ByteArrayInputStream(companyJson.getBytes());
    		DataInputStream din = new DataInputStream(input);
    		
    		Decoder decoder = DecoderFactory.get().jsonDecoder(companySchema, din);
    		GenericRecord companyGenericRecord=reader.read(null, decoder);
    		
    		String companyId=((Utf8)companyGenericRecord.get("symbol")).toString();
    		companiesMap.put(companyId,companyGenericRecord );
    	}
    	return companiesMap;		        
    }
}


