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
* Implement document storage on src/main/java/com/gft/upv/flink/proccess/ExtendedElasticSink.java
* Launch StreamingStokJob on the IDE, (provide the topic parameter)
* Access to  Kibana (localhost:5661)
* Go to Management-->Kibana --> Index Patterns.
* Create quotes (quotes*) pattern. 
* Go to Dev Tools and execute following query and  you will get quotes indexed :
```
GET quotes/_search
{
  "size": 10 
  
}
```
* Create a mapping for twitter index:

```
PUT tweets
{
  "mappings": {
    "properties": {
      "text": { 
        "type": "text",
        "analyzer": "english",
        "fielddata": true,
        "fields": {
          "keyword": { 
            "type": "keyword"
          }
        }
      }
    }
  }
}
```

* Launch src/main/java/com/gft/upv/flink/TwitterStockJob.java (pass Twitter topic as argument)
* Go to Management-->Kibana --> Index Patterns.
* Create twitter (twitter*) pattern. 
* Go to Dev Tools and execute following query and  you will get quotes indexed :
```
GET twitter/_search
{
  "size": 10 
  
}
```


In case you need help, you can check ElasticSink API here --> https://ci.apache.org/projects/flink/flink-docs-stable/dev/connectors/elasticsearch.html
