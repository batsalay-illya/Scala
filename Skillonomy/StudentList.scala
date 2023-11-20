import scala.collection.mutable.ListBuffer
class StudentList(listOfStudents: ListBuffer[Student] = ListBuffer()) extends CustomList(listOfStudents) {

  def FindByName(name: String): List[Student] = { list.toList.filter(student => student.FirstName == name) }
  def Print(): Unit = { list.foreach(student => student.Print()) }
  def FullPrint(course: Course): Unit = { list.foreach(student => student.Print(course))}
}
