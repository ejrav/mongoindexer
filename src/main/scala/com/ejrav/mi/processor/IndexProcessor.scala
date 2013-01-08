package com.ejrav.mi.processor

import com.ejrav.mi.analyzer.AnalyzerFactory
import com.ejrav.mi.config.Collection
import com.ejrav.mi.config.Process
import com.ejrav.mi.source.BasicDocument
import com.ejrav.mi.source.Document

class IndexProcessor extends Processor {
  def run(document: Document, process: Process, collection: Collection): Document = {
    var doc = new BasicDocument
    val analyzer = AnalyzerFactory.getAnalyzer(process.parameters("analyzer"))
    val content: String = document.field(collection.field.name).asInstanceOf[String]

    doc.field(process.outputField, analyzer.tokenize(content))
    collection.categories.foreach(f => doc.field(f.name, f.value))
    doc
  }
}

object IndexProcessor {
  def apply() = {
    new IndexProcessor
  }
}