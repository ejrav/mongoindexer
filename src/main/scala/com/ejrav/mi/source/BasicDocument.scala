package com.ejrav.mi.source

import scala.collection.mutable.Map
import scala.Predef._


class BasicDocument extends Document {
  var map = Map[String, AnyRef]()

  def field(name: String, value: AnyRef) {
    map(name) = value
  }

  def field(name: String): AnyRef = {
    map(name)
  }

  def fields(): Set[String] = {
    map.keySet.toSet
  }

  def merge(doc: Document) {
    map ++= doc.toMap
  }


  def isEmpty: Boolean = map.isEmpty

  def toMap: scala.collection.immutable.Map[String, AnyRef] = map.toMap

}

object BasicDocument {
  def apply(map: scala.collection.Map[String, AnyRef]) = {
    val nDoc = new BasicDocument
    map.foreach {
      case (k, v) => nDoc.field(k, v)
    }
    nDoc
  }
}