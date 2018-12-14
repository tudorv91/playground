package utils

case class MutableNode[T](var data: T, var previous: MutableNode[T], var next: MutableNode[T])