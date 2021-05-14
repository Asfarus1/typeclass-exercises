package com.example

import com.example.Eq.Syntax.EqOps

case class Person(name: String, id: Int)

object Person {

  object Instances {
    // TODO #9: Define an Eq instance for Person comparing them by name
    //          Extra points: receive an implicit instance for String and use it
    implicit val nameEq: Eq[Person] = Eq.instance(_.name eqTo _.name)

    // TODO #10: Define an Eq instance for Person comparing them by id
    //           Extra points: receive an implicit instance for Int and use it
    implicit val idEq: Eq[Person] = Eq.instance(_.id eqTo _.id)
  }

}