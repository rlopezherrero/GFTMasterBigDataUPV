# Exercise 2: Data Processing

In this exercise you will connect Nifi ingestion with Flink data processing

## Setup

Download Kafka stack and visualization tool:

* Confluent Kafka stack: https://www.confluent.io/download/
* Download Schema registry launcher for windows:
	* https://github.com/renukaradhya/confluentplatform/blob/master/bin/windows/schema-registry-start.bat
	* https://github.com/renukaradhya/confluentplatform/blob/master/bin/windows/schema-registry-run-class.bat
* kafka tool & avro pluggin: http://www.kafkatool.com/ , https://github.com/laymain/kafka-tool-avro

Unzip and launch it:

* Zookeeper :
```
confluent-5.2.2\bin\windows\zookeeper-server-start.bat  etc\kafka\zookeeper.properties
```

* Kafka broker:
```
confluent-5.2.2\bin\windows\kafka-server-start.bat etc\kafka\server.properties
```

* Schema registry:
	* Copy schema-registry.bat & schema-registry-run-class.bat to confluent-5.2.2\bin\windows
	* Launch it (If it fails change default port 8081 on etc/schema-registry.properties)
```
confluent-5.2.2\bin\windows\schema-registry-start.bat etc\schema-registry\schema-registry.properties
```

## Development

* Nifi
	* Replace File sink box and with Kafka publisher box,  topics to  be used are quotes and  twitter.
* Flink processing	
	* Configure companies filter on src/main/java/com/gft/upv/flink/proccess/FilterCompanies.java. You can  get in-scope companies from appConfig.getInScopeCompanies()
	* Configure enrichment on src/main/java/com/gft/upv/flink/proccess/EnrichCompany.java
	* Add those  two steps on src/main/java/com/gft/upv/flink/proccess/StreaminStockJob.java
* Kafa tool for Output Visualization
	* Connect  to quotesEnriched topic and see output messages. 
