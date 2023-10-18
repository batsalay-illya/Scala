@main
def main(): Unit = {
  var student_1 : Student = new Student("241", 19, "Ivan", "Ivanov", new Address("Ukraine", "Kiev", "Central"))
  var student_2 : Student = new Student("241", 20, "Tom", "Adams", new Address())
  var teacher : Teacher = new Teacher("IT", 5, "Oleg", "Shevchenko", new Address())
  teacher.AddStudent(student_1)
  teacher.AddStudent(student_2)
  teacher.RandomScore(student_1)
  println(s"${student_1.FirstName}: ${student_1.PrintScore()}")
  teacher.RandomScoreForAll()

  teacher.PrintStudentsScore()

}