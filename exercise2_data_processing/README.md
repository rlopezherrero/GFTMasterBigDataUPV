# Exercise 2: Data Processing

In this exercise you will connect Nifi ingestion with Flink data processing

![Exercise architecture](../img/architecture_exercise2.png)

## Setup

Download Kafka stack and visualization tool:

* Confluent Kafka stack: [Confluent 5.2.2](http://packages.confluent.io/archive/5.2/confluent-5.2.2-2.11.tar.gz?_ga=2.174462370.1890803127.1563567511-395073974.1561650126)
* Download Schema registry launcher for windows (download zip from repository): https://github.com/renukaradhya/confluentplatform
* Kafka tool & Avro plugin:
	* Download installer : http://www.kafkatool.com/ 
	* Download avro pluggin: https://github.com/laymain/kafka-tool-avro/releases/download/1.0.2/kafka-tool-avro-1.0.2-jar-with-dependencies.jar
	* Copy kafka-tool-avro-1.0.2-jar-with-dependencies.jar to kafkaTool pluggins folder.
	
Unzip and launch it:

* Zookeeper :
```
confluent-5.2.2\bin\windows\zookeeper-server-start.bat  confluent-5.2.2\etc\kafka\zookeeper.properties
```

* Kafka broker:
```
confluent-5.2.2\bin\windows\kafka-server-start.bat confluent-5.2.2\etc\kafka\server.properties
```

* Unzip  downloaded Schema registry launcher for Windows and follow next steps:
	* Copy bin\windows\schema-registry.bat & bin\windows\schema-registry-run-class.bat to confluent-5.2.2\bin\windows
	* Launch it (If it fails change default port to other different than 8081 on etc/schema-registry.properties)
```
confluent-5.2.2\bin\windows\schema-registry-start.bat confluent-5.2.2\etc\schema-registry\schema-registry.properties
```

* Register Quotes and Twitter schema on schema registry
	* Download curl --> https://curl.haxx.se/windows/dl-7.65.1_3/curl-7.65.1_3-win64-mingw.zip
	* Copy curl.exe to GFTMasterBigDataUPV/exercise2_data_processing folder.  
	* Register both schemas, please be inside exercise2_data_processing folder (If you have launched Schema Registry on different  port please change URL to proper port):
	```
	curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" --data @quotesSchema.json http://localhost:8081/subjects/quotes-value/versions
	curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" --data @tweetsSchema.json http://localhost:8081/subjects/tweets-value/versions
	```
* Check on your ide that you have maven configured.

## Development

* **NiFi**
	* Upload Template `nifi/twitter_quotes_upv_ingestor.xml` 
		* Rigth click, select the template that is inside GFTMasterBigDataUPV/exercise2_data_processing/nifi
	* Configure GetTwitter box access credentials (Consumer and Access Token)
	* Configure IEX Cloud token on InvokeHTTP box rest call URL.
	* Configure UpdateAtribute box adding schema.name property for schema registry (**Hint**: should be topicname-value)
	* Configure PublishKafkaRecord boxes Kafka URL (port is 9092) and the topic names (tweets and quotes)
	* Configure RouteOnAtribute box and filter over Microsoft, Uber, Twitter, Facebook additionally to the Google already defined.
	* Check that the messages are populated on the topics:
		* Launch kafka tool, and leave default parameters.
		* Connect to tweets/quotes topics. 
		* Select Properties, message type to Avro. 
* **Flink processing**
	* Filter the quotes for a list of 10 main technological companies (already defined on appConfig.properties file) 
		* Configure companies filter on `src/main/java/com/gft/upv/flink/proccess/FilterCompanies.java`. You can  get in-scope companies from appConfig.getInScopeCompanies()
	* Enrich quotes messages data adding companies information 
		* Configure enrichment on `src/main/java/com/gft/upv/flink/proccess/EnrichCompany.java`
	* Add those steps to filter the main companies and add companies static data.  
		* Add those  two steps on `src/main/java/com/gft/upv/flink/StreaminStockJob.java`
* **Kafa tool for Output Visualization**
	* Connect  to quotesEnriched topic and see output messages.

In case you need help, you can check Flink API here --> https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/datastream_api.html
