package com.ejrav.mi.source.mongo


import com.ejrav.mi.source.Document
import com.mongodb.casbah.commons.MongoDBObject

class MongoDocument(var obj: MongoDBObject) extends Document {
  def field(name: String, value: AnyRef) {
    obj.put(name, value)
  }

  def field(name: String): AnyRef = {
    obj.as[AnyRef](name)
  }

  def fields(): Set[String] = {
    obj.keys.toSet
  }

  def merge(doc: Document) {
    doc.fields.foreach(f => obj.put(f, doc.field(f)))
  }

  def toMap: scala.collection.immutable.Map[String, AnyRef] = {
    obj.toSet.toMap
  }

  override def toString = obj.toString()
}