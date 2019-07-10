package com.gft.upv.config;

import com.typesafe.config.Config;

import java.io.Serializable;

public class ElasticConfig implements Serializable {

  //  private final String schTrade;
   
	private final String host;
	private final int port;
	
	private final String stockIndex;
	private final String stockType;
	
    public ElasticConfig(Config confFile) {
    	
    	host = confFile.getString("elastic.elasticHost");
    	port = confFile.getInt("elastic.elasticPort");
    	
    	stockIndex = confFile.getString("elastic.stockIndex");
    	stockType = confFile.getString("elastic.stockType");
     }

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getStockIndex() {
		return stockIndex;
	}

	public String getStockType() {
		return stockType;
	}

    
}
