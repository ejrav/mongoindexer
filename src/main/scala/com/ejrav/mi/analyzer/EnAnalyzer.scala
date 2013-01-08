package com.ejrav.mi.analyzer

import org.apache.lucene.util.Version
import org.apache.lucene.analysis.en.EnglishAnalyzer

class EnAnalyzer extends TAnalyzer {
  def analyzer = new EnglishAnalyzer(Version.LUCENE_36)
}

object EnAnalyzer {
  def apply() = {
    new EnAnalyzer
  }
}