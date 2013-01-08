package com.ejrav.mi.config

import java.io.FileReader
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.Reader
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import java.io.File

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

    mapper.readValue(reader, classOf[Configuration]).asInstanceOf[Configuration]
  }
}