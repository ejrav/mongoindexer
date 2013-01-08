package com.ejrav.test.indexer

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.ejrav.mi.config.Configuration

class TestIndexer extends FlatSpec with ShouldMatchers {
  val conf = Configuration("test_config.json")
  
  "indexer" should "create a new collection" in {
	  
  }
}