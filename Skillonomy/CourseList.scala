import scala.collection.mutable.ListBuffer
class CourseList(courseList: ListBuffer[Course] = ListBuffer()) extends CustomList(courseList) {

  def FindByName(name: String): List[Course] = { list.toList.filter(course => course.Name == name) }
  def Contains(course:Course): Boolean = { list.contains(course) }
  def Print(): Unit = { list.foreach(course => course.Print()) }
}