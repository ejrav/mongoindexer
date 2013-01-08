package com.ejrav.test.config

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import javax.xml.bind.JAXBContext
import java.io.StringReader
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import java.io.FileReader
import java.io.Reader
import java.io.File
import com.ejrav.mi.config._


class TestJsonConfig extends FlatSpec with ShouldMatchers {

  "configuration" should "containt at least one collection" in {
    val conf = Configuration("test_config.json")
    
    conf.indexes should not equal (Nil)
    conf.sources should not equal (Nil)
    
    conf.indexes.size should equal (1)
    
    conf.indexes(0).processors should not equal (Nil)
    conf.indexes(0).processors.size should equal (2)
    
    conf.indexes(0).processors(0).parameters should not equal (Map.empty)
    conf.indexes(0).processors(0).parameters("key1") should equal("value1")
  }

}