package com.ejrav.test.db

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.ejrav.mi.config.Configuration
import com.ejrav.mi.source.Query
import com.ejrav.mi.source.Connection
import com.ejrav.mi.source.BasicDocument
import com.ejrav.mi.source.Document
import com.ejrav.mi.source.QueryField
import com.ejrav.mi.source.Collection
import org.bson.types.ObjectId

class TestDb extends FlatSpec with ShouldMatchers {
  val conf = Configuration("test_config.json")

  "source factory" should "procude a usable source" in {

    val idx = conf.indexes.head
    val source = conf.getSource(idx.source)

    source match {
      case Some(source) => {
        val conn = SourcFactoryMock.getSource(source)
        saveData(conn)

        val data = getData(conn)

        assert(data != null)

        val doc = data.next
        doc.field("a") should equal("v")
        doc.field("b") should equal("v")
        doc.field("_id").getClass should equal(classOf[ObjectId])
      }
      case None => false should equal(true)
    }
  }

  def saveData(conn: Connection) {
    val doc = BasicDocument(Map(("a" -> "v"), ("b" -> "v")))

    conn.save(Query("collection_1"), doc)
  }

  def getData(conn: Connection): Collection[Document] = {
    val query = Query("collection_1", List(QueryField("a", "v")))

    conn.get(query)
  }
}