package com.ejrav.test.lucene


import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.apache.lucene.analysis.TokenStream
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.analysis.it.ItalianAnalyzer
import org.apache.lucene.util.Version
import java.io.StringReader
import com.ejrav.mi.analyzer.AnalyzerFactory
import com.ejrav.mi.analyzer.TAnalyzer
import com.ejrav.mi.analyzer.ItAnalyzer
import com.ejrav.mi.analyzer.EnAnalyzer

class TestLucene extends FlatSpec with ShouldMatchers {

  
  "analyzer factory" should "return the right analyzer" in {
    val analyzerIt = AnalyzerFactory.getAnalyzer("it")
    analyzerIt.getClass() should equal(classOf[ItAnalyzer]) 
    
    val analyzerEn = AnalyzerFactory.getAnalyzer("en")
    analyzerEn.getClass() should equal(classOf[EnAnalyzer])
    
    evaluating {  AnalyzerFactory.getAnalyzer("sds") } should produce [Exception]
  }

  "it analyzer" should "print something" in {
    val analyzerIt = AnalyzerFactory.getAnalyzer("it")
    val l = analyzerIt.tokenize("Diffondiamo la passione del fare impresa. Promuoviamo la cultura dell'intraprendere")
    l.foreach(f => println(f))

    l.size should equal(7)
  }

   "en analyzer" should "print something" in {
    val analyzerEn = AnalyzerFactory.getAnalyzer("en")
    val l = analyzerEn.tokenize("You don't throw an exception, but the class of an exception")
    l.foreach(f => println(f))

    l.size should equal(6)
  }
}