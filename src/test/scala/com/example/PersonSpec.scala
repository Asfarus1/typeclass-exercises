package com.example

import com.example.laws.discipline.EqTests

class PersonSpec extends MySpec {
  // TODO #17: Write tests for additional Eq instances defined in Person using
  //           Discipline and the 'checkAll' method
  checkAll("Eq[Person] by id", EqTests[Person](Person.Instances.idEq).rules)
  checkAll("Eq[Person] by name", EqTests[Person](Person.Instances.nameEq).rules)
}
