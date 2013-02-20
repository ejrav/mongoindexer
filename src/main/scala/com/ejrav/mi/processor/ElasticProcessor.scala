package com.ejrav.mi.processor

import com.ejrav.mi.source.Document
import com.ejrav.mi.config

import com.ejrav.mi.config._

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest
import org.elasticsearch.common.settings.ImmutableSettings


import scala.collection.JavaConversions._
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.transport.InetSocketTransportAddress

class ElasticProcessor(process: Process) extends Processor {
  def isReadOnly: Boolean = true

  val indexName = process.parameters.get("index_name").get
  val indexType = process.parameters.get("index_type").get
  val serverHost = process.parameters.get("server_host").get
  val serverPort = process.parameters.get("server_port").get

  val client = new TransportClient()
    .addTransportAddress(new InetSocketTransportAddress(serverHost, serverPort.toInt));

  val bulkRequest = client.prepareBulk()

  createIndexWithProperties(process.parameters.get("index_name").get)

  def run(document: Document, process: config.Process, collection: Collection): Document = {
    bulkRequest.add(client.prepareIndex(indexName, indexType, document.field("_id").toString).setSource(document.toMap))
    null
  }

  def close {
    bulkRequest.execute().actionGet()
    client.close()
  }

  private def createIndexWithProperties(indexName: String) = {
    val indexRequest = new CreateIndexRequest(indexName)
    indexRequest.settings(ImmutableSettings.settingsBuilder().
      put("index.number_of_shards", 13).put("index.number_of_replicas", 1).build())

    client.admin().indices().create(indexRequest).actionGet()
  }
}

object ElasticProcessor {
  def apply(process: Process) = {
    new ElasticProcessor(process)
  }
}
