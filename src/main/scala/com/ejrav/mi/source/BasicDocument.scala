package com.ejrav.mi.source

import scala.collection.mutable.Map
import scala.collection.TraversableOnce

class BasicDocument extends Document {
  var map = Map[String, Any]()

  def field(name: String, value: Any) {
    map(name) = value
  }

  def field(name: String): Any = {
    map(name)
  }

  def fields(): Set[String] = {
    map.keySet.toSet
  }

  def merge(doc: Document) {
	  map ++= doc.toMap
  }

  def toMap: scala.collection.immutable.Map[String, Any] = map.toMap

}

object BasicDocument {
  def apply(map: scala.collection.Map[String, Any]) = {
    var nDoc = new BasicDocument
    map.foreach { case (k, v) => nDoc.field(k, v) }
    nDoc
  }
}