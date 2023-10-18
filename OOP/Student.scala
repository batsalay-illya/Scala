class Student(group : String, age: Int, firstName: String, lastName: String, address: Address) extends Human(age, firstName, lastName, address) {
  private var _group : String = group
  private var _scoreList : List[Int] = List()

  def Group : String = _group
  def Group_=(value: String) : Unit = _group = value

  def ScoreList : List[Int] = _scoreList
  def ScoreList_=(list: List[Int]) : Unit = { _scoreList = list}

  def PrintScore() : String = {
    var result : String = "[ "
    for(score<-_scoreList){
      result += s"${score.toString} "
    }
    result += "]"
    result
  }
  override def toString = s"Group: ${_group}, ScoreList: ${_scoreList.foreach(print)}, ${Print()}"
}
