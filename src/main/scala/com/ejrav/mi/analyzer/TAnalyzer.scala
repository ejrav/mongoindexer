package com.ejrav.mi.analyzer

import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.apache.lucene.analysis.it.ItalianAnalyzer
import org.apache.lucene.analysis.Analyzer
import java.io.StringReader
import org.apache.lucene.util.Version

trait TAnalyzer {
  def analyzer: Analyzer

  def tokenize(text: String): List[String] = {
    def wordList(tokenStream: TokenStream): List[String] = {
      if (tokenStream.incrementToken()) tokenStream.getAttribute(classOf[CharTermAttribute]).toString() :: wordList(tokenStream)
      else Nil
    }

    val tokenStream = analyzer.tokenStream(null, new StringReader(text))

    wordList(tokenStream)
  }
}