# Exercise 3: Data Storage

In this exercise you will store realtime messages on Elastic Search from Flink

![Exercise architecture](../img/architecture_exercise3.png)

## Setup

Download Elastic and Kibana:

* Elastic: https://www.elastic.co/es/downloads/elasticsearch
* Kibana: https://www.elastic.co/es/downloads/kibana

Unzip and launch it:

* Launch elastic on elastic installation installation folder:
```
bin\elasticsearch
```

* Launch kibana on kibana installation folder:
```
bin\kibana
```

## Development

* Evolve the code used on exercise 2. 
* Add sink process to the pipeline on src/main/java/com/gft/upv/flink/StreaminStockJob.java
* Implement document storage on src/main/java/com/gft/upv/flink/proccess/StockElasticSink.java
* Access to  Kibana (localhost:5661)
* Go to Management-->Index Management. You will see your quotes index created. 
* Go to Dev Tools and execute following query and  you will get quotes indexed :
````
GET quotes/_search
{
  "size": 10 
  
}
``

In case you need help, you can check ElasticSink API here --> https://ci.apache.org/projects/flink/flink-docs-stable/dev/connectors/elasticsearch.html
