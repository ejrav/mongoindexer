package com.ejrav.mi.processor

import com.ejrav.mi.config.Process

object ProcessorFactory {
  def getProccessor(process: Process): Processor = {
    process.name match {
      case "index" => IndexProcessor(process)
      case "url" => UrlProcessor(process)
      case "elastic" => ElasticProcessor(process)
      case _ => throw new Exception("No preccessor found with name %s".format(process.name))
    }
  }

  def releaseProcessor(processor: Processor) {
    processor.close
  }
}