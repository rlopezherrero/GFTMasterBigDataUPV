package com.gft.upv.flink.process;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.generic.GenericRecord;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentType;

import com.gft.upv.config.ElasticConfig;


public class ExtendedElasticSink implements ElasticsearchSinkFunction<GenericRecord> {
	
	String index;
	
	public ExtendedElasticSink(String index) {
		this.index=index;
	}
	
	public IndexRequest createIndexRequest(GenericRecord element) {
        //Map<String, String> json = new HashMap<>();
        //json.put("data", element.toString());
    
		//JSONParser parser = new JSONParser(null, null, false); 
		//JSONObject json = (JSONObject) parser.parse(element.toString());
		
		Map<String, Object> map = new HashMap<>();
		element.getSchema().getFields().forEach(field -> 
		    map.put(field.name(), element.get(field.name())));
		
		return Requests.indexRequest()
                .index(index)
                //.type(config.getStockType())
                .source(element.toString(),XContentType.JSON);
	}
	
	
	@Override
	public void process(GenericRecord element, RuntimeContext ctx, RequestIndexer indexer) {
		 indexer.add(createIndexRequest(element));
		
	}
}

/*

}*/