package utils

case class MutableNode[T](var light: T, var next: MutableNode[T])