package com.ejrav.mi.source.mongo

import com.mongodb.casbah.Imports._
import scala.collection.JavaConversions._

import com.mongodb.DBObject
import com.ejrav.mi.source.Document

class MongoDocument(obj: DBObject) extends Document {
  def field(name: String, value: Any) {
    obj.put(name, value)
  }

  def field(name: String): Any = {
    obj.get(name)
  }

  def fields(): Set[String] = {
    obj.keySet().toSet
  }

  def merge(doc: Document) {
    doc.fields.foreach(f => obj.put(f, doc.field(f)))
  }

  def toMap: scala.collection.immutable.Map[String, Any] = {
    obj.toMap.asInstanceOf[Map[String, Any]]
  }
}