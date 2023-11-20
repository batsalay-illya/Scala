class Student(user: User) extends User(user.FirstName, user.LastName, user.Age, user.Country, user.City, user.Street){
  private val _courseList: CourseList = new CourseList()
  private var _scoreList: ScoreList = new ScoreList()

  def CourseList: CourseList = _courseList
  def ScoreList: ScoreList = _scoreList
  def ScoreList_=(list: ScoreList) : Unit = { _scoreList = list}

  def AddScore(course: Course, score:Int): Unit = {
    //...
      _scoreList.GetList(course).addOne(score)
  }

  def AddCourse(course: Course): Unit = { _courseList.AddElement(course); _scoreList.AddList(course) }
  def RemoveCourse(course: Course): Unit = { _courseList.DeleteElement(course); _scoreList.DeleteList(course) }
  def PayForMonth(value: Int): Boolean = {
    if(Balance < value){
      if(math.random < 0.5) {
        Balance = 0
        true
      } else { false }
    } else {
      RemoveFromBalance(value)
      true
    }
  }

  def ScoreToString(): String = {
    var result: String = ""
    _courseList.list.foreach(course => {
      result += s"(Course:${course.Name} - ["
      _scoreList.GetList(course).foreach(score => result += s"${score.toString} ")
      result += "])"
    })
    result
  }

  def ScoreToString(course: Course) : String = {
    var result : String = "[ "
    _scoreList.GetList(course).foreach(score => result += s"${score.toString} ")
    result += "]"
    result
  }

  override def Print(): Unit = {
    println(s"First name: ${FirstName}, Last name: ${LastName}, Age: ${Age}, Score: ${ScoreToString()},  Balance: ${Balance}")
  }
  def Print(course: Course): Unit = {
    println(s"First name: ${FirstName}, Last name: ${LastName}, Age: ${Age}, Score: ${ScoreToString(course)},  Current balance: ${Balance} (Study cost:${course.Salary}, Task reward:${course.GetTaskRewardAmount(_scoreList.GetList(course).last)})")
  }
}
