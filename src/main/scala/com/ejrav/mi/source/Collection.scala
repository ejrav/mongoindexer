package com.ejrav.mi.source

trait Collection[T <: Document] extends Iterator[T] {
	def getAll(): List[T]
}