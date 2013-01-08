package com.ejrav.mi.processor

import com.ejrav.mi.config.Process

object ProcessorFactory {
  def getProccessor(process: Process): Processor = {
    process.name match {
      case "index" => IndexProcessor()
    }
  }
}