package com.ejrav.mi.source.mongo

import com.mongodb.DBCursor
import com.ejrav.mi.source.Collection
import com.ejrav.mi.source.Document
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.MutableList
import com.mongodb.DBObject
import com.ejrav.mi.source.Document

import com.ejrav.mi.source.mongo.Implicits._

class MongoCollection(cursor: DBCursor) extends Collection[Document] {
  val dbcursor = cursor
  val cursorIte = cursor.iterator()
  
  def getAll(): List[Document] = {
    var ml: MutableList[Document] = new MutableList

    while (cursorIte.hasNext()) {
      ml +== mongoToDocument(cursorIte.next())
    }

    ml.toList
  }

  def hasNext: Boolean = cursorIte.hasNext()

  def next: Document = cursorIte.next()

}