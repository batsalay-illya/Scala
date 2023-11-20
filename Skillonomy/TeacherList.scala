import scala.collection.mutable.ListBuffer

class TeacherList(listOfTeachers: ListBuffer[Teacher] = ListBuffer()) extends CustomList(listOfTeachers) {

  def SetScoreForStudents(month: Int): Unit = {
    //list.foreach(teacher => teacher.SetScore())
  }
  def FindByName(name: String): List[Teacher] = {
    list.toList.filter(teacher => teacher.FirstName == name)
  }
  def Print(): Unit = {
    list.foreach(teacher => teacher.Print())
  }

}
