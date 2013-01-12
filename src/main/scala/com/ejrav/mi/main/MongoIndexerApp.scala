package com.ejrav.mi.main

import org.rogach.scallop.ScallopConf
import com.ejrav.mi.config.Configuration
import com.ejrav.mi.processor.ProcessorEngine


object MongoIndexerApp extends App {

  val conf = new ScallopConf(args) {
    version("mongoindexer 0.1 ")
    banner( """Usage: mongoindexer [OPTION]
              |mongoindexer is an little program, which halps you to create a full text serach index
              |in mongodb:
              | """.stripMargin)
    footer("\nFor all other tricks, consult the documentation!")

    val configFile = opt[String]("config", required = true)

    override  def onError(e: Throwable) ={
      println("Error: %s".format(e.getMessage))
      printHelp()
      sys.exit(1)
    }

  }

  ProcessorEngine.run(Configuration(conf.configFile()))
}