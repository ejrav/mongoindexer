package com.ejrav.mi.processor

import com.ejrav.mi.config.Configuration
import com.ejrav.mi.config.Index
import com.ejrav.mi.config.Source
import com.ejrav.mi.source.SourceFactory
import com.ejrav.mi.source.Query

import com.ejrav.mi.source.BasicDocument
import org.slf4j.LoggerFactory


object ProcessorEngine {
  val logger = LoggerFactory.getLogger("ProcessorEngine")

  def run(conf: Configuration) {
    conf.indexes.foreach(idx => processIndex(idx, conf.getSource(idx.source)))
  }

  def processIndex(idx: Index, source: Option[Source]) {

    logger.info("Start indexing...")
    val conn = getConnection(source)
    idx.collections foreach {
      c =>

        logger.info("Processing collection {}", c.name)

        val coll = conn.get(Query(c.name))

        coll.foreach {
          d =>
            try {
              var outDoc = new BasicDocument

              idx.processors foreach {
                p =>
                  val processor = ProcessorFactory.getProccessor(p)


                  if (processor.isReadOnly) {
                    processor.run(d, p, c)
                  } else {
                    outDoc.merge(processor.run(d, p, c))
                  }
              }


              if (outDoc.isEmpty){
                outDoc.field("ref_id", d.field("_id"))
                conn.save(Query(idx.outputCollection), outDoc)
              }
            } catch {
              case e: NoSuchElementException => logger.error("filed not found i doc %s".format(d), e)
            }
        }

    }

    logger.info("End indexing.")

  }

  def getConnection(source: Option[Source]) = {
    SourceFactory.getSource(source.get)
  }
}