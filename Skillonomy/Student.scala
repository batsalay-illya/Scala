import scala.collection.mutable.ListBuffer

class Student(user: User) extends User(user.FirstName, user.LastName, user.Age, user.Country, user.City, user.Street) with ScoreList[Course]() {
  private val _courseList: CustomList[Course] = new CustomList()

  def CourseList: CustomList[Course] = _courseList

  def AddScore(course: Course, score:Int): Unit = {
    //...
    GetScoreList(course).addOne(score)
  }

  def AddCourse(course: Course): Unit = { _courseList.AddElement(course); AddScoreList(course) }
  def RemoveCourse(course: Course): Unit = { _courseList.DeleteElement(course); DeleteScoreList(course) }
  def PayForMonth(teacher: Teacher, value: Int): Boolean = {
    Exchange.TransferFromUserToUser(this, teacher, value)
  }

  def Print(course: Course): Unit = {
    println(s"First name: ${FirstName}, Last name: ${LastName}, Age: ${Age}, Score: ${ScoreToString(course)}, Token balance: ${GetTokenBalance}, Currency balance: ${GetCurrencyBalance}")
  }
  override def Print(): Unit = {
    println(s"First name: ${FirstName}, Last name: ${LastName}, Age: ${Age}, Score: ${ScoreToString(_courseList.list.last)}, Token balance: ${GetTokenBalance}, Currency balance: ${GetCurrencyBalance}")
  }
}
