import scala.collection.mutable.ListBuffer
import util.Random

class Teacher(speciality: String, age: Int, firstName: String, lastName: String, address: Address) extends Human(age, firstName, lastName, address) {
  private val _speciality : String = speciality
  private val _listOfStudents : ListBuffer[Student] = ListBuffer()

  def ListOfStudents : ListBuffer[Student] = _listOfStudents

  def AddStudent(student: Student) : Unit = { _listOfStudents += student }
  def RemoveStudent(student: Student) : Unit = { _listOfStudents -= student }


  def RandomScore(student: Student) : Unit = {
    val list = List.fill[Int](5)(Random.between(0,5))
    student.ScoreList = list
  }

  def RandomScoreForAll() : Unit = {
    _listOfStudents.foreach(student => student.ScoreList = List.fill(5)(Random.between(0, 5)))
  }

  def PrintStudentsScore(): Unit = _listOfStudents.foreach(student => println(s"${student.FirstName} : ${student.PrintScore()}"))

  override def toString = s"Speciality: ${speciality}, ${Print()}"
}
