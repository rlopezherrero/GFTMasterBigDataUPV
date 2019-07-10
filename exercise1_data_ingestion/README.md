# Exercise 1: Data Ingestion

In this exercise you will connect to your data sources and store the data into a file, using Apache NiFi.

![Exercise architecture](../img/architecture_exercise1.png)

## Data sources

* **IEX Cloud**: https://iexcloud.io/
  * Sign up
  * Tickers URL: https://cloud.iexapis.com/stable/stock/market/collection/sector?collectionName=Technology&token=your_token
* **Twitter**: https://developer.twitter.com/
  * Sign up
  * Create app

## Setup

Download Apache Nifi:

* https://nifi.apache.org/download.html

Unzip and run:

```
\nifi-1.9.2\bin\run-nifi.bat
```

Once it is running, go to http://localhost:8080/nifi

## Development

In the NiFi canvas, you will need to create the following:

* Read from Twitter (GetTwitter processor) and save to file (PutFile processor)
  * You will need the credentials from your Twitter app
* Read from IEX Cloud REST Service (InvokeHTTP processor) and save to file (PutFile processor)
  * You will need the tickers REST URL
  * Set the scheduler to every 30 seconds

For more info on how the processors work, read the [Apache NiFi documentation](https://nifi.apache.org/docs.html).

**IMPORTANT**: In case you get stuck, feel free to use the templates in the "src" folder.
