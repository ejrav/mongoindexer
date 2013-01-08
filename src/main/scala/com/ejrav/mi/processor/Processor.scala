package com.ejrav.mi.processor

import com.ejrav.mi.source.Document

trait Processor {
  def run(document: Document): Document
}