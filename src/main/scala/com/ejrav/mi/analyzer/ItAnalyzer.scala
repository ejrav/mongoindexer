package com.ejrav.mi.analyzer

import org.apache.lucene.analysis.it.ItalianAnalyzer
import org.apache.lucene.util.Version

class ItAnalyzer extends TAnalyzer {
  def analyzer = new ItalianAnalyzer(Version.LUCENE_36) 
}

object ItAnalyzer {
  def apply() = {
    new ItAnalyzer
  }
}