package com.gft.upv.config;

import com.typesafe.config.Config;

import java.io.Serializable;

public class ElasticConfig implements Serializable {

  //  private final String schTrade;
   
	private final String host;
	private final int port;
	
	private final String stockIndex;
	private final String stockType;
	
	private final String twitterIndex;
	private final String twitterType;
	
    public ElasticConfig(Config confFile) {
    	
    	host = confFile.getString("elastic.elasticHost");
    	port = confFile.getInt("elastic.elasticPort");
    	
    	stockIndex = confFile.getString("elastic.stockIndex");
    	stockType = confFile.getString("elastic.stockType");
    	
    	twitterIndex = confFile.getString("elastic.twitterIndex");
    	twitterType = confFile.getString("elastic.twitterType");
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

	public String getTwitterIndex() {
		return twitterIndex;
	}

	public String getTwitterType() {
		return twitterType;
	}

    
}
