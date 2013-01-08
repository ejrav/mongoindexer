package com.ejrav.mi.source

object Operation extends Enumeration {
  type Operation = Value
  val eq, neq, lt, lte, gt, gte = Value
}

case class QueryField[T <: Any](name: String, value: T, op: Operation.Value)

object QueryField {
  def apply(name: String, value: Any) = {
    new QueryField[Any](name, value, Operation.eq)
  }
}

case class Limit(start: Int, max: Int)

case class Query(collection: String, filter: List[QueryField[Any]], sort: List[QueryField[Any]], limit: Option[Limit])

object Query {
  def apply(collection: String, filter: List[QueryField[Any]]) = {
    new Query(collection, filter, Nil, None)
  }

  def apply(collection: String) = {
    new Query(collection, Nil, Nil, None)
  }
}