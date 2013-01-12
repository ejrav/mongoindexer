package com.ejrav.test.processor

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import com.ejrav.mi.config.Configuration
import com.ejrav.mi.processor.ProcessorFactory
import com.ejrav.mi.source._
import com.ejrav.test.db.SourcFactoryMock

class TestProcessor extends FlatSpec with ShouldMatchers {
  val conf = Configuration("test_config.json")

  "test processor" should "produce" in {

    val conn = SourcFactoryMock.getSource(conf.sources.head)

    saveData(conn)

    val idx = conf.indexes.head
    val coll = conn.get(Query(idx.collections.head.name))
    val proc = idx.processors.head


    var outDoc = new BasicDocument


    val processor = ProcessorFactory.getProccessor(proc)

    val d = coll.next()
    val c = idx.collections.head

    outDoc.merge(processor.run(d, proc, c))


    conn.save(Query("collection_name"), outDoc)

    val fData = getData(conn)
    val doc = fData.next()

    println(doc.toMap)
  }

  def saveData(conn: Connection) {
    val doc = BasicDocument(Map(("field1" -> "ciao come va"), ("b" -> "v")))
    conn.save(Query("collection_1"), doc)
  }

  def getData(conn: Connection): Collection[Document] = {
    val query = Query("collection_name")
    conn.get(query)
  }
}
