package com.ejrav.mi.source.mongo

import com.mongodb.casbah.Imports._

import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import com.ejrav.mi.source.Document
import com.ejrav.mi.source.Query
import com.ejrav.mi.source.Operation
import com.ejrav.mi.source.QueryField

object Implicits {
  implicit def documentToMongo(doc: Document): DBObject = {
    MongoDBObject(doc.fields.map(f => (f, doc.field(f))).toList)
  }


  implicit def mongoToDocument(obj: DBObject): Document = {
    new MongoDocument(obj)
  }

  implicit def mongoToDocument(obj: MongoDBObject): Document = {
    new MongoDocument(obj)
  }

  implicit def queryToMongoObject(query: Query): DBObject = {
    def reduce(x1: MongoDBObject, x2: MongoDBObject): MongoDBObject = {
      x1.putAll(x2)
      x1
    }

    if (query.filter.isEmpty) new BasicDBObject
    else query.filter.map(f => filterToMongoObject(f)).reduceLeft((x1, x2) => reduce(x1, x2))
  }

  def filterToMongoObject(f: QueryField[_]): MongoDBObject = {
    f.op match {
      case Operation.eq => new BasicDBObject(f.name, f.value)
      case Operation.neq => new BasicDBObject(f.name, new BasicDBObject("$ne", f.value))
      case Operation.lt => new BasicDBObject(f.name, new BasicDBObject("$lt", f.value))
      case Operation.lte => new BasicDBObject(f.name, new BasicDBObject("$lte", f.value))
      case Operation.gt => new BasicDBObject(f.name, new BasicDBObject("$gt", f.value))
      case Operation.gte => new BasicDBObject(f.name, new BasicDBObject("$gte", f.value))
    }
  }
}