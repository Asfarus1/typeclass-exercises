package com.example

trait Eq[A] {
  // TODO #1: Define an 'eq' method that takes two A values as parameters, and returns a Boolean
  def eq(a: A, b: A): Boolean
}

object Eq {
  // TODO #2: Define the method 'apply' so we can summon instances from implicit scope
  def apply[A](implicit eq: Eq[A]): Eq[A] = eq

  // TODO #3: Define the method 'instance' so we can build instances of the Eq typeclass more easily.
  //          It should take as the only parameter a function of type (A, A) => Boolean
  def instance[A](f: (A, A) => Boolean): Eq[A] = f(_, _)

  // TODO #4: Define an Eq instance for String
  implicit val stringEq: Eq[String] = instance[String](_ equals _)

  // TODO #5: Define an Eq instance for Int
  implicit val intEq: Eq[Int] = instance[Int](_ equals  _)

  // TODO #6: Define an Eq instance for Person. Two persons are equal if both their names and ids are equal.
  //          Extra points: receive implicit instances for String and Int and use them
  implicit val personEq: Eq[Person] = instance[Person] { (a, b) =>
    Eq[Int].eq(a.id, b.id) && Eq[String].eq(a.name, b.name)
  }

  // TODO #7: Provide a way to automatically derive instances for Eq[Option[A]] given that we have an implicit
  //          instance for Eq[A]
  implicit def optionEq[A](implicit eq: Eq[A]): Eq[Option[A]] = {
    case (None, None) => true
    case (Some(a), Some(b)) => eq.eq(a, b)
    case _ => false
  }

  object Syntax {

    // TODO #8: Define a class 'EqOps' with a method 'eqTo' that enables the following syntax:
    //          "hello".eqTo("world")
    implicit class EqOps[A](val a: A) extends AnyVal {
      def eqTo(b: A)(implicit eq: Eq[A]): Boolean = eq.eq(a, b)
    }

  }

}