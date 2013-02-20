package com.ejrav.mi.processor

import com.ejrav.mi.source.Document
import com.ejrav.mi.config.Collection
import com.ejrav.mi.config.Process

trait Processor {
  def run(document: Document, process: Process, collection: Collection): Document

  def isReadOnly: Boolean

  def close
}