package com.ejrav.mi.source

trait Document {
	def field(name: String, value: Any)
	
	def field(name: String): Any
	
	def fields(): Set[String]
	
	def merge(doc: Document)
	
	def toMap: Map[String, Any]
}