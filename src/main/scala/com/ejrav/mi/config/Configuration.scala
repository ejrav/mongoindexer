package com.ejrav.mi.config

import java.io.FileReader
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.Reader
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import java.io.File
import com.fasterxml.jackson.annotation.JsonProperty


case class Source(name: String, host: String, port: Int, db: String, user: String, password: String)

case class Index(
                  source: String,
                  @JsonProperty("output_collection") outputCollection: String,
                  collections: List[Collection],
                  processors: List[Process])

case class Process(name: String,
                   @JsonProperty("output_field") outputField: String,
                   parameters: Map[String, String])

case class Field(name: String)


case class Tag(name: String, ctype: String, value: String)

case class Transformation(
                      name: String,
                      collections: List[Collection],
                      transformation: String)

case class Collection(
                       name: String,
                       field: Field,
                       tags: List[Tag])

case class Configuration(
                          sources: List[Source],
                          indexes: List[Index]) {

  def getSource(name: String) = {
    sources.find(p => p.name == name)
  }
}

object Configuration {
  def apply(confFile: String): Configuration = {
    val reader: Reader = new FileReader(new File(confFile));
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)

    val r = mapper.readValue(reader, classOf[Configuration]).asInstanceOf[Configuration]
    reader.close();
    r
  }
}