package com.example.laws.discipline

import com.example.Eq
import com.example.laws.EqLaws
import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll
import org.typelevel.discipline.Laws

trait EqTests[A] extends Laws {
  def laws: EqLaws[A]

  // TODO #14: Define a RuleSet containing the laws in EqLaws
  def rules(implicit arb: Arbitrary[A]) = new DefaultRuleSet(
    name = "Eq",
    parent = None,
    props =
      "reflexivity" -> forAll(laws.reflexivity _),
    "symmetry" -> forAll(laws.symmetry(_, _)),
    "transitivity" -> forAll(laws.transitivity(_, _, _))
  )
}

object EqTests {
  // TODO #15: Define a companion object with an 'apply' method so that we can
  //           easily instantiate tests with e.g. EqTests[Int]
  def apply[A](implicit eqObj: Eq[A]): EqTests[A] = new EqTests[A] {
    override def laws: EqLaws[A] = new EqLaws[A] {
      override def eq: Eq[A] = eqObj
    }
  }
}
