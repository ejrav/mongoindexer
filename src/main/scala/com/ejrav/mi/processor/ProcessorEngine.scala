package com.ejrav.mi.processor

import com.ejrav.mi.config.Configuration
import com.ejrav.mi.config.Index
import com.ejrav.mi.config.Source
import com.ejrav.mi.source.SourceFactory
import com.ejrav.mi.source.Query

import com.ejrav.mi.source.BasicDocument


object ProcessorEngine {
  def run(conf: Configuration) {
    conf.indexes.foreach(idx => processIndex(idx, conf.getSource(idx.source)))
  }

  def processIndex(idx: Index, source: Option[Source]) {
    val conn = getConnection(source)
    idx.collections foreach { c =>

      val coll = conn.get(Query(c.name))

      coll.foreach { d =>
        var outDoc = new BasicDocument
        
        idx.processors foreach { p =>
          val processor = ProcessorFactory.getProccessor(p)

          outDoc.merge(processor.run(d, p, c))
        }

        outDoc.field("ref_id", d.field("_id"))
        conn.save(Query(c.name), outDoc)
      }

    }

  }

  def getConnection(source: Option[Source]) = {
    SourceFactory.getSource(source.get)
  }
}