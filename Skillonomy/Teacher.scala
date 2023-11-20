import scala.collection.mutable.ListBuffer
import util.Random

class Teacher(user: User) extends User(user.FirstName, user.LastName, user.Age, user.Country, user.City, user.Street){
  private var _course: Course = new Course()
  private var _office: Office = _

  def OwnCourse: Course = _course
  def OwnOffice: Office = _office


  def AddStudent(student: Student) : Unit = { _office.AddStudent(student) }
  def AddStudents(studentList: StudentList) : Unit = {_office.AddStudents(studentList)}
  def UnsubscribeStudent(student: Student, courseCompleted: Boolean = false) : Unit = { if(!courseCompleted) student.RemoveCourse(_course) }
  def UnsubscribeStudents(studentList: StudentList, courseCompleted: Boolean = false): Unit = {
    studentList.list.foreach(student => {
      if(courseCompleted) student.RemoveCourse(_course)
    })
  }

  def SetScore(course: Course, student: Student, score: Int): Int = {
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
    _course = new Course(name, period, salary, successFee)
    DataBase.ListOfCourses.AddElement(_course)
  }
  def RemoveCourse(): Unit = {
    DataBase.ListOfCourses.DeleteElement(_course)
  }

  def CreateOffice(office: Office): Unit = {
    _office = office
    DataBase.ListOfOffices.AddElement(office)
    office.AddTeacher(this)
  }

  override def toString = s"${Print()}"
}
