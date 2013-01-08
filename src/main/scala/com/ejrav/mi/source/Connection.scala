package com.ejrav.mi.source

trait Connection {
  def get(query: Query): Collection[Document]
  def save(query: Query, doc: Document)
  def update(query: Query, doc: Document)
  def remove(query: Query)
}