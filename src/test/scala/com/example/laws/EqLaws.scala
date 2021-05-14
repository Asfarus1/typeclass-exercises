package com.example.laws

import com.example.Eq

trait EqLaws[A] {
  def eq: Eq[A]

  final def eql: (A, A) => Boolean = eq.eq(_, _)

  // TODO #11: Define a 'reflexivity' property which checks that a value is equal to itself
  def reflexivity(a: A): Boolean =
    eql(a, a)

  // TODO #12: Define a 'symmetry' property which checks that when 'x' is equal to 'y' then 'y' is equal to 'x',
  //           and viceversa
  def symmetry(a: A, b: A): Boolean =
    eql(a, b) == eql(b, a)

  // TODO #13: Define a 'transitivity' property which checks that if 'x' is equal to 'y' and 'y' is equal to 'z'
  //           then 'x' is equal to 'z'
  //           Hint: The proposition 'IF p THEN q' can be stated as 'NOT p OR q'
  def transitivity(a: A, b: A, c: A): Boolean =
    !(eql(a, b) && eql(b, c)) || eql(a, c)
}
