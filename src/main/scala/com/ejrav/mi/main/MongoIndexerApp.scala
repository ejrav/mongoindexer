package com.ejrav.mi.main

import org.rogach.scallop.exceptions._
import org.rogach.scallop.ScallopConf
import org.rogach.scallop.LazyScallopConf


case class Arguments(configFile: String = "")

object MongoIndexerApp extends App {

  val conf = new LazyScallopConf(args) {
    version("mongoindexer 0.1 ")
    banner("""Usage: mongoindexer [OPTION]
           |mongoindexer is an little program, which halps you to create a full text serach index      
           |in mongodb:
           |""".stripMargin)
    footer("\nFor all other tricks, consult the documentation!")

    val configFile = opt[String]("config", required = true)
  }

  conf.initialize {
    case Help => conf.printHelp
    case Version => conf.printHelp
    case Exit() => conf.printHelp
    case ScallopException(message) => {
      conf.printHelp
      println(message)
      sys.exit(1)
    }
    case RequiredOptionNotFound(optionName) => {
      conf.printHelp
      println(optionName)
      sys.exit(1)
    }
  }
  
  println(conf.configFile.get.get)
}