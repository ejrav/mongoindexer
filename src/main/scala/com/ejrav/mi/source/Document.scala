package com.ejrav.mi.source

trait Document {
	def field(name: String, value: AnyRef)
	
	def field(name: String): AnyRef
	
	def fields(): Set[String]
	
	def merge(doc: Document)
	
	def toMap: Map[String, AnyRef]
}