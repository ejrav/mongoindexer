package com.ejrav.mi.analyzer

object AnalyzerFactory {
  def getAnalyzer(name: String): TAnalyzer = {
    name match {
      case "it" => ItAnalyzer()
      case "en" => EnAnalyzer()
      case _ => throw new Exception("No analyzer found with name %s".format(name))
    }
  }
}