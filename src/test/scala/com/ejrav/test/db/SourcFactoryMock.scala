package com.ejrav.test.db

import com.mongodb.casbah.Imports._

import com.mongodb.Mongo
import com.mongodb.ServerAddress
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.MongoDB
import com.foursquare.fongo.Fongo


import com.ejrav.mi.source.SourceFactory
import com.ejrav.mi.source.Connection
import com.ejrav.mi.config.Source
import com.ejrav.mi.source.mongo.MongoConn

object SourcFactoryMock {
  def getSource(source: Source): Connection = {
    val fongo: Fongo = new Fongo("mongo server 1")

    new MongoConn(MongoDB(fongo.getMongo(), source.db))
  }
}