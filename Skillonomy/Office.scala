class Office(teacher: Teacher = null, location: String = "", id: Int = 0) {
  private var _id: Int = id
  private var _location: String = location
  private var _teacher: Teacher = teacher
  private val _listOfStudents: StudentList = new StudentList()

  final def ID: Int = _id
  final def ID_=(value: Int): Unit = { _id = value }

  final def Location: String = _location
  final def Location_=(value: String): Unit = { _location = value }

  final def Teacher : Teacher = _teacher
  final def ListOfStudents : StudentList = _listOfStudents

  final def AddTeacher(teacher: Teacher): Unit = {_teacher = teacher}

  final def AddStudent(student: Student): Unit = { _listOfStudents.AddElement(student) }
  final def AddStudents(studentList: StudentList): Unit = { _listOfStudents.AddElements(studentList) }
  final def RemoveStudent(student: Student): Unit = { _listOfStudents.DeleteElement(student) }
  final def RemoveStudents(studentList: StudentList): Unit = { _listOfStudents.DeleteElements(studentList) }

  def ProcessMonth(month: Int): Unit = {
    if(month == 1 || month % (_teacher.OwnCourse.Period+1) == 0){
      println("New Students have arrived")
      StudentSelection()
    }
    _listOfStudents.list.foreach(student => {
      val score:Int = _teacher.SetScore(_teacher.OwnCourse, student, month)
    })
    
    print(s"Kicked students: ")
    val kickedStudents: StudentList = ProcessStudentsPayment()
    if(kickedStudents.list.isEmpty) { println("-") }
    else { kickedStudents.Print() }


    if(month % _teacher.OwnCourse.Period == 0) {
      println("Course successfully ended")
      ProcessTeacherSuccessFeePayment()
      _teacher.UnsubscribeStudents(_listOfStudents, true)
      RemoveStudents(_listOfStudents)
      PrintData(false)
      return
    }
    PrintData(true)
  }

  private def StudentSelection(): Unit ={
    DataBase.ListOfStudents.list.foreach(student => {
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
  private def ProcessStudentsPayment(): StudentList = {
      var toKick : StudentList = new StudentList()
      _listOfStudents.list.foreach(student => {
        val reward: Int = _teacher.OwnCourse.GetTaskRewardAmount(student.ScoreList.GetList(_teacher.OwnCourse).last)
        student.AddToBalance(reward)

        if(!student.PayForMonth(_teacher.OwnCourse.Salary)) {
          toKick.AddElement(student)
        }
      })
      RemoveStudents(toKick)
      _teacher.UnsubscribeStudents(toKick)
      toKick
  }
  private def ProcessTeacherSuccessFeePayment(): Unit = {
    _teacher.AddToBalance((_listOfStudents.list.length * (_teacher.OwnCourse.Salary * _teacher.OwnCourse.SuccessFee)*24).toInt)
  }

  def PrintData(full:Boolean): Unit = {
    println("-"*15)
    println(s"Office (Location: ${_location}; ID:${_id}) contains: ")
    _teacher.OwnCourse.Print()
    println("Teacher: ")
    _teacher.Print()
    println("Students: ")
    if(full) { _listOfStudents.FullPrint(_teacher.OwnCourse) }
    else { _listOfStudents.Print() }
    println("-"*15)
  }
}