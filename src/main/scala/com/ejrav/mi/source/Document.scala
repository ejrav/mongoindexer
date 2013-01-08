package com.ejrav.mi.source

trait Document {
	def field(name: String, value: Any)
	
	def field(name: String): Any
	
	def fields(): Set[String]
}