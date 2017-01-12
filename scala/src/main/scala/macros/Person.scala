package macros

import java.util.Date

case class Person(id: String, name: String, age: Int = 25, date: Date = new Date())