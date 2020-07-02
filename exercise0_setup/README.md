# Exercise 0: Setup

In this exercise you will understand the software requirements, and how to do an initial setup.

## Software requirements

* IDE for Java development (your choice)
  * https://www.jetbrains.com/idea/download/ (Choose InteliJ Community)
  * https://www.eclipse.org/downloads/packages/
* Maven (for dependencies Management)
  * https://maven.apache.org/download.cgi
* GIT client (for code  management)
  * https://git-scm.com/download/win
* Java Virtual Machine
  * https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html
* Docker Desktop
  * https://www.docker.com/products/docker-desktop

## Architecture software

* Apache NiFi: https://nifi.apache.org/download.html
* Apache Kafka stack: https://www.confluent.io/download/
* Schema registry for windows: https://github.com/renukaradhya/confluentplatform/tree/master/bin/windows
* Kafka tool & Avro plugin: http://www.kafkatool.com/ , https://github.com/laymain/kafka-tool-avro
* Elastic: https://www.elastic.co/es/downloads/elasticsearch
* Kibana: https://www.elastic.co/es/downloads/kibana
* Jupyter: https://www.anaconda.com/distribution/

**NOTE**: The architecture software will be installed when required in each of the exercises. There is no need to download or install them yet.

## Infrastructure Setup

In order to launch the required infrastructure you just have to go to the "docker" folder and run the following:

´´´
docker-compose up -d
´´´

This will the following components, which will be used during the course:

| Component | Description | URL/port |
| ------------- | ------------- | ------------- |
| **Apache NiFi**  | Data Ingestion Tool  | http://localhost:8090  |
| **Confluent Kafka**  | Message Broker  | localhost:9092  |
| **Elasticsearch**  | Data storage and search engine  | http://localhost:9200  |
| **Kibana**  | Dashboarding and Elasticsearch dev/admin tool  | http://localhost:5601  |
| **Jupyter**  | Notebooks analytics  | http://localhost:8888  |
| **cAdvisor**  | (Optional) Docker monitoring tool  | http://localhost:8080  |

## Development Environment Setup

* Install IDE
* Download and unzip Maven
* Download the code with any of these two options:

  1. Install GIT and clone the project
 
 ```
 git clone https://github.com/rlopezherrero/GFTMasterBigDataUPV
 ```

  2. Download the course exercise directly from the repository: https://github.com/rlopezherrero/GFTMasterBigDataUPV (click on "Clone or download")
