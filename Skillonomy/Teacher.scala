import scala.collection.mutable.ListBuffer
import util.Random

class Teacher(user: User) extends User(user.FirstName, user.LastName, user.Age, user.Country, user.City, user.Street){
  private var _course: Course = new Course()
  private var _office: Office = _

  def OwnCourse: Course = _course
  def OwnOffice: Office = _office
  def SetCourse(value: Course): Unit = { _course = value }
  def SetOffice(value: Office): Unit = { _office = value }

  def AddStudent(student: Student) : Unit = { _office.AddStudent(student) }
  def AddStudents(studentList: CustomList[Student]) : Unit = {_office.AddStudents(studentList)}
  def UnsubscribeStudent(student: Student, courseCompleted: Boolean = false) : Unit = { if(!courseCompleted) student.RemoveCourse(_course) }
  def UnsubscribeStudents(studentList: CustomList[Student], courseCompleted: Boolean = false): Unit = {
    studentList.list.foreach(student => {
      if(courseCompleted) student.RemoveCourse(_course)
    })
  }

  def SetScore(student: Student, course: Course): Int = {
    val score: Int = Random.between(1,5)
    student.AddScore(course, score)
    score
  }
  def SetScore(student: Student, course: Course, score: Int) : Unit = {
    student.AddScore(course, score)
  }

  def SetScoreForAllStudents(course: Course, score: Int): Unit = {
    _office.ListOfStudents.list.foreach(student => student.AddScore(course, score))
  }
  def SetScoreForAllStudents(course: Course): Unit = {
    _office.ListOfStudents.list.foreach(student => student.AddScore(course, Random.between(0,5)))
  }

  def CreateCourse(name:String, period:Int, salary:Int, successFee:Float): Unit = {
    if (TokenPay(this, DataBase.CourseCost)) {
      _course = new Course(name, period, salary, successFee)
      DataBase.ListOfAllCourses.AddElement(_course)
      Exchange.GetTransactionFee(DataBase.CourseCost)
    }
  }
  def RemoveCourse(): Unit = {
    DataBase.ListOfAllCourses.DeleteElement(_course)
  }

  def CreateOffice(office: Office): Unit = {
    if (TokenPay(this, DataBase.OfficeCost)) {
      _office = office
      DataBase.ListOfAllOffices.AddElement(office)
      office.AddTeacher(this)
      Exchange.GetTransactionFee(DataBase.OfficeCost)
    }
  }

  override def toString = s"${Print()}"
}
