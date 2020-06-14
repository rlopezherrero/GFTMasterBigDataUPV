package com.gft.upv.config;

import java.io.InputStream;
import java.io.Serializable;

import org.apache.avro.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gft.upv.parser.CompaniesParser;
import com.typesafe.config.Config;

public class SchemaConfig implements Serializable {

    private static Schema companySchema;
    private static Schema quoteSchema;
    private static final Logger logger = LoggerFactory.getLogger(SchemaConfig.class);
    
    public SchemaConfig(Config confFile) {
    	try {
        	Schema.Parser parser = new Schema.Parser();
        	  
      	    InputStream isQuote = this.getClass().getClassLoader().getResourceAsStream(confFile.getString("schemaregistry.quoteSchema"));
      	    quoteSchema	 = parser.parse(isQuote);
      	    
      	  
      	    InputStream isCompany = this.getClass().getClassLoader().getResourceAsStream(confFile.getString("schemaregistry.companySchema"));
      	    companySchema = parser.parse(isCompany);    	
    	}
    	catch(Exception ex) {
    		logger.error("Error parsing avro schemas",ex);
    	}

    
    }

	public Schema getCompanySchema() {
		return companySchema;
	}

	public Schema getQuoteSchema() {
		return quoteSchema;
	}
	
}
