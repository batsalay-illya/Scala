import scala.collection.mutable.ListBuffer

class ScoreList(listOfScores: ListBuffer[Int] = ListBuffer()) {
  private var _scoreList : Map[Course, ListBuffer[Int]] = Map.empty[Course, ListBuffer[Int]]
  def ScoreList: Map[Course, ListBuffer[Int]] = { _scoreList }

  def AddList(course: Course):Unit = { _scoreList = _scoreList + (course -> new ListBuffer[Int]);}
  def DeleteList(course: Course):Unit = {_scoreList = _scoreList - course}

  def AddScoreToList(course: Course, score: Int): Unit = {
    _scoreList(course) += score
  }

  def GetList(course:Course): ListBuffer[Int] = _scoreList.apply(course)
  def Print(course: Course): Unit = {
    _scoreList(course).foreach(score => print(s"${score}"))
  }
}

