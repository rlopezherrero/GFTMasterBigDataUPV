package com.gft.upv.config;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


public class AppConfig implements Serializable {

    private final Config config;
    private final KafkaConfig kafkaConfig;
    private final SchemaConfig schemaConfig;
    private final FlinkConfig flinkConfig;
    private final ElasticConfig elasticConfig;
    private final List<String> inScopeCompanies;
    
    public AppConfig(Config conf) {
        this.config = conf;
        kafkaConfig = new KafkaConfig(conf);
        schemaConfig = new SchemaConfig(conf);
        flinkConfig = new FlinkConfig(conf);
        elasticConfig = new ElasticConfig(conf);
        inScopeCompanies=config.getStringList("filters.inScopeCompanies");
    }
    
    public AppConfig(String path) {
        this.config = ConfigFactory.parseFile(new File(path));
        kafkaConfig = new KafkaConfig(config);
        schemaConfig = new SchemaConfig(config);
        flinkConfig = new FlinkConfig(config);
        elasticConfig = new ElasticConfig(config);        
        inScopeCompanies=config.getStringList("filters.inScopeCompanies");
        
    }

    public FlinkConfig getFlinkConf() {
        return flinkConfig;
    } 

    public KafkaConfig getKafkaConf() {
        return kafkaConfig;
    }

    public Config getConf() {
        return config;
    }

   

    public SchemaConfig getSchemaConf() { return schemaConfig; }

    public ElasticConfig getElasticConf() { return elasticConfig; }

    
    public List<String> getInScopeCompanies() {
		return inScopeCompanies;
	}

	@Override
    public String toString() {
        return "AppConfig{" +
                ", kafkaConf=" + kafkaConfig +
                ", elasticConf=" + elasticConfig +
                ", schemaRegConf=" + schemaConfig +
                ", conf=" + config +
                '}';
    }
}