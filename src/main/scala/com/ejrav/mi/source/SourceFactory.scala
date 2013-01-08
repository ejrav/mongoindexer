package com.ejrav.mi.source

import com.mongodb.casbah.Imports._
import com.mongodb.{ Mongo, ServerAddress }
import com.ejrav.mi.config.Source
import com.mongodb.casbah.MongoDB
import com.ejrav.mi.source.mongo.MongoConn

object SourceFactory {
  def getSource(source: Source): Connection = {
    val address = new ServerAddress(source.host, source.port)
    val db = MongoConnection(address).getDB(source.db)

    if (source.user != null && !source.user.isEmpty()) {
      db.authenticate(source.user, source.password)
    }
    
    val  m = new Mongo
    new MongoConn(db)
  }
}

