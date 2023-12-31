import scala.collection.mutable.ListBuffer

class Office(teacher: Teacher = null, location: String = "", id: Int = 0) {
  private var _id: Int = id
  private var _location: String = location
  private val _price: Int = 500
  private var _teacher: Teacher = teacher
  private val _listOfStudents: CustomList[Student] = new CustomList()


  final def ID: Int = _id
  final def ID_=(value: Int): Unit = { _id = value }

  final def Location: String = _location
  final def Location_=(value: String): Unit = { _location = value }

  final def Teacher : Teacher = _teacher

  final def ListOfStudents: CustomList[Student] = _listOfStudents

  final def AddTeacher(teacher: Teacher): Unit = {_teacher = teacher}

  final def AddStudent(student: Student): Unit = { _listOfStudents.AddElement(student) }
  final def AddStudents(studentList: CustomList[Student]): Unit = { _listOfStudents.AddElements(studentList) }
  final def RemoveStudent(student: Student): Unit = { _listOfStudents.DeleteElement(student) }
  final def RemoveStudents(studentList: CustomList[Student]): Unit = { _listOfStudents.DeleteElements(studentList) }

  def ProcessMonth(month: Int): Unit = {
    if(month == 1 || month % (_teacher.OwnCourse.Period+1) == 0){
      println(s"Office (ID:${_id}) - New Students have arrived")
      StudentSelection()
    }
    _listOfStudents.list.foreach(student => {
      val score:Int = _teacher.SetScore(student, _teacher.OwnCourse)
    })
    
    print(s"Office (ID:${_id}) - Kicked students: ")
    val kickedStudents: CustomList[Student] = ProcessStudentsPayment()
    if(kickedStudents.list.isEmpty) { println("-") }
    else { kickedStudents.list.foreach(student => student.Print()) }


    if(month % _teacher.OwnCourse.Period == 0) {
      println("Course successfully ended")
      ProcessTeacherSuccessFeePayment()
      _teacher.UnsubscribeStudents(_listOfStudents, true)
      RemoveStudents(_listOfStudents)
      return
    }
    PayForOffice()
    Print()
  }

  private def StudentSelection(): Unit ={
    DataBase.ListOfAllStudents.list.foreach(student => {
      if (student.CourseList.list.nonEmpty) {
        if(!student.CourseList.Contains(_teacher.OwnCourse)){
          if (math.random < 0.2) {
            AddStudent(student)
            student.AddCourse(_teacher.OwnCourse)
          }
        }
      } else {
        if (math.random < 0.5) {
          AddStudent(student)
          student.AddCourse(_teacher.OwnCourse)
        }
      }
    })
  }
  private def ProcessStudentsPayment(): CustomList[Student] = {
      var toKick : CustomList[Student] = new CustomList()
      _listOfStudents.list.foreach(student => {
        val reward: Int = _teacher.OwnCourse.GetTaskRewardAmount(student.GetScoreList(_teacher.OwnCourse).last)
        Exchange.TransferToUser(student, reward)

        if(!Exchange.TransferFromUserToUser(student, _teacher, _teacher.OwnCourse.Salary)) {
          toKick.AddElement(student)
        }
      })
      RemoveStudents(toKick)
      _teacher.UnsubscribeStudents(toKick)
      toKick
  }
  private def ProcessTeacherSuccessFeePayment(): Unit = {
    val successFee:Int = (_listOfStudents.list.length * (_teacher.OwnCourse.Salary * _teacher.OwnCourse.SuccessFee)*24).toInt
    Exchange.TransferToUser(_teacher, successFee)
  }
  private def PayForOffice(): Unit = {
    val totalPrice: Int =_price + (100*_listOfStudents.list.length)
    if(_teacher.GetTokenBalance >= totalPrice){
      Exchange.TransferFromUser(_teacher, totalPrice)
    }else{
      if(Exchange.UserBuyTokens(_teacher, _teacher.GetTokenBalance - totalPrice)){
        Exchange.TransferFromUser(_teacher, totalPrice)
      }else{
        DeleteOffice()
      }
    }
  }

  private def DeleteOffice(): Unit = {
    RemoveStudents(_listOfStudents)
    DataBase.ListOfAllCourses.DeleteElement(_teacher.OwnCourse)
    _teacher.SetCourse(null)
    _teacher.SetOffice(null)
    DataBase.ListOfAllOffices.DeleteElement(this)
  }
  def Print(): Unit = {
    println("-"*15)
    println(s"Office (Location: ${_location}; ID:${_id}) contains: ")
    _teacher.OwnCourse.Print()
    println("Teacher: ")
    _teacher.Print()
    println("Students: ")
    _listOfStudents.list.foreach(student => student.Print(_teacher.OwnCourse))
    println("-"*15)
  }
}