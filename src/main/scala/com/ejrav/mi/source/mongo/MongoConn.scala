package com.ejrav.mi.source.mongo

import com.mongodb.casbah.Imports._

import com.mongodb.casbah.MongoDB
import com.ejrav.mi.source.Connection
import com.ejrav.mi.source.Query
import com.ejrav.mi.source.Collection
import com.ejrav.mi.source.Document

import com.ejrav.mi.source.mongo.Implicits._

class MongoConn(db: MongoDB) extends Connection {

  def get(query: Query): Collection[Document] = {
    new MongoCollection(db.getCollection(query.collection).find(query))
  }

  def save(query: Query, doc: Document) {
    db.getCollection(query.collection).save(doc)
  }

  def update(query: Query, doc: Document) {
    db.getCollection(query.collection).update(query, doc)
  }

  def remove(query: Query) {
    db.getCollection(query.collection).remove(query)
  }
}

