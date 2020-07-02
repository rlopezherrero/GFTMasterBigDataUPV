
package com.gft.upv.flink;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.formats.json.JsonNodeDeserializationSchema;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;
import org.apache.http.HttpHost;

import com.gft.upv.config.AppConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;



/**
 * Skeleton for GFT UPV Big Data Master
 *
 * <p>Follow TODOs for the exercise development, check the
 * tutorials and examples on the <a href="http://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */

public class StreamingStockJob {

	private String topic;
	private AppConfig appConfig;
	private Properties jobProperties=new Properties();
	private StreamExecutionEnvironment env;
	
	
	public StreamingStockJob(String[] args) {
		this.topic=args[0];
	}
	
	public void setupJob() throws Exception {
	
		ClassLoader classLoader = StreamingStockJob.class.getClassLoader();
		
		//Load application configuration
		InputStream is=classLoader.getResourceAsStream("application.conf");
		InputStreamReader reader = new InputStreamReader(is);
		Config config = ConfigFactory.parseReader(reader);
		appConfig = new AppConfig(config);
				
		//Setup job properties
		jobProperties.put("bootstrap.servers", appConfig.getKafkaConf().getKafkaBrokersUrls());
	    jobProperties.setProperty("group.id", "stockConsumer");
		jobProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		jobProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	
	
	}
	
	public void createPipeline() {
		
		// set up the streaming execution environment
		env = StreamExecutionEnvironment.getExecutionEnvironment();

		 
				
		//Add kafka sink
		JsonNodeDeserializationSchema serde = new JsonNodeDeserializationSchema();
		DataStream<ObjectNode> quotesStream = env
					.addSource(new FlinkKafkaConsumer011<>(this.topic, serde, jobProperties));
						
		//TODO Exercise 2 add filter and enrichement steps
				
		List<HttpHost> httpHosts = new ArrayList<>();
		httpHosts.add(new HttpHost(this.appConfig.getElasticConf().getHost(), this.appConfig.getElasticConf().getPort(), "http"));
		
		//TODO Exercise 3 use a ElasticsearchSink.Builder an use ElasticSink and replace FlinkKafkaproducer Sink
		//TODO Exercise 3 configure bulk requests; this instructs the sink to emit after every element, otherwise they would be buffered
		//Here you can find documentation --> https://ci.apache.org/projects/flink/flink-docs-stable/dev/connectors/elasticsearch.html
			
		quotesStream.addSink(new FlinkKafkaProducer011<>("quotesTransformed", 
				new SerializationSchema<ObjectNode>() {
            		@Override
            		public byte[] serialize( ObjectNode element ) {
            			return element.toString().getBytes();
            		}
				},
				jobProperties));
	}	
	
	public void launchPipeline() throws Exception{
		this.env.execute();
	}
	
	public static void main(String[] args) throws Exception {
		
		if(args.length!=1) {
			System.out.println("Error, you have not specified the right parameters");
			System.out.println("--------------------------------------------------");
			
			System.out.println("Example: StreamingStockJob topic ");
			System.out.println("topic: Kafka topic where you want to consume the messages ");		
		}
		else
		{
			StreamingStockJob streamingJob=new StreamingStockJob(args);
			streamingJob.setupJob();
			streamingJob.createPipeline();
			streamingJob.launchPipeline();
		}
		
	
	}
}
