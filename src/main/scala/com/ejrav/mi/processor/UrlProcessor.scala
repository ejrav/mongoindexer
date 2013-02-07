package com.ejrav.mi.processor

import com.ejrav.mi.source.{BasicDocument, Document}
import com.ejrav.mi.config.{Collection, Process}
import com.mongodb.BasicDBObject

import com.ejrav.mi.source.mongo.Implicits._


class UrlProcessor extends Processor {
  def run(document: Document, process: Process, collection: Collection): Document = {
    var doc = new BasicDocument

    val content = document.field(collection.field.name)

    val additionalField = process.parameters.get("additional_field")

    val c = if (additionalField.isEmpty) {
      content.toString
    } else {
      content.toString + " " + getAdditionalFields(document, additionalField.get)
    }

    doc.field(process.outputField, genUrl(c))
    collection.tags.foreach(f => doc.field(f.name, f.value))
    doc
  }

  private def getAdditionalFields(doc: Document, additionField: String): AnyRef = {
    def getValue(doc: Document, f: List[String]): AnyRef = {
      if (f.isEmpty) None
      else {
        val elem = doc.field(f.head)
        if (classOf[BasicDBObject] == doc.field(f.head).getClass)  getValue(elem.asInstanceOf[BasicDBObject], f.tail)
        else elem
      }

    }

    val fields = additionField.split('.')
    getValue(doc, fields.toList).toString
  }

  private def genUrl(str: String): String = {
    def removeUnwantedChars(str: String): String = {
      if (str.isEmpty) ""
      else str.head match {
        case ' ' | '\'' | '?' | '.' | ',' | '-' => "_" + removeUnwantedChars(str.tail)
        case 'è' | 'é' => "e" + removeUnwantedChars(str.tail)
        case 'ò' => "e" + removeUnwantedChars(str.tail)
        case 'à' => "a" + removeUnwantedChars(str.tail)
        case 'ù' | 'ü' => "u" + removeUnwantedChars(str.tail)
        case _ => str.head + removeUnwantedChars(str.tail)
      }
    }

    removeUnwantedChars(str.toLowerCase)
  }


}

object UrlProcessor {
  def apply() = {
    new UrlProcessor
  }
}
