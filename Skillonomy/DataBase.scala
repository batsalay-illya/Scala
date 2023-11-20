import scala.util.Random
import scala.util.control.Breaks._

object DataBase {
  private val _office: Office = new Office()
  private val _listOfUsers: UserList = new UserList()
  private val _listOfStudents: StudentList = new StudentList()
  private val _listOfTeachers: TeacherList = new TeacherList()
  private val _listOfCourses: CourseList = new CourseList()
  private val _listOfOffices: OfficeList = new OfficeList()

  final def ListOfUsers: UserList =  _listOfUsers
  final def ListOfStudents: StudentList =  _listOfStudents
  final def ListOfTeachers: TeacherList =  _listOfTeachers
  final def ListOfCourses: CourseList = _listOfCourses

  final def ListOfOffices: OfficeList = _listOfOffices

  def SimulateRegistration(userCount: Int): Unit = {
    if(userCount <= 1) {
      SimulateRegistration(2)
      return
    }

    //need to control amount of users/students/teachers
    for (u <- 1 to userCount) {
      breakable {
        val newUser: User = new User(GenerateRandomString(), GenerateRandomString(), 0, GenerateRandomString(), GenerateRandomString(), GenerateRandomString())
        newUser.AddToBalance(1000)
        _listOfUsers.AddElement(newUser)
        if (math.random < 0.2) {
          val newTeacher: Teacher = new Teacher(newUser)
          _listOfTeachers.AddElement(newTeacher)
          break
        }

        if (u == userCount && _listOfTeachers.list.isEmpty) {
          val newTeacher: Teacher = new Teacher(newUser)
          _listOfTeachers.AddElement(newTeacher)
          break
        }

        val newStudent: Student = new Student(newUser)
        _listOfStudents.AddElement(newStudent)
      }
    }
  }

  def SimulateRegistration(userCount: Int, studentCount: Int, teacherCount: Int): Unit = {

    //need to control amount of users/students/teachers
    //for (u <- 1 to userCount){
    //  var newUser: User = new User(GenerateRandomString(), GenerateRandomString(), 0, GenerateRandomString(), GenerateRandomString(), GenerateRandomString(), 1500)
    //  _listOfUsers.AddElement(newUser)
    //}

    //for (s <- 1 to studentCount){
    //  breakable{
    //    _listOfUsers.list.foreach(user => {
    //      if (user.Role == Role.None) {
    //        var newStudent: Student = new Student(user)
    //        _listOfStudents.AddElement(newStudent)
    //        break
    //      }
    //    })
    //  }
    //}

    //for (t <- 1 to teacherCount) {
    //  breakable {
    //    _listOfUsers.list.foreach(user => {
    //      if (user.Role == Role.None) {
    //        val newTeacher: Teacher = new Teacher(user)
    //        _listOfTeachers.AddElement(newTeacher)
    //        break
    //      }
    //    })
    //  }
    //}

  }

  //temp function for simulation
  def GenerateRandomString() : String = {
    var str: String = ""
    val chars = ('A' to 'Z')
    val num = 100 + Random.nextInt(899)
    val sb = new StringBuilder
    for (i <- 1 to 2) {
      sb.append(chars(scala.util.Random.nextInt(chars.length)))
    }
    sb.append(num)
    sb.toString()
  }
}