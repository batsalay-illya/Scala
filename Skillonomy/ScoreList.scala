import scala.collection.mutable.ListBuffer

trait ScoreList[T]() {
  private var _scoreList : Map[T, ListBuffer[Int]] = Map.empty[T, ListBuffer[Int]]
  def ScoreList: Map[T, ListBuffer[Int]] = { _scoreList }

  def AddScoreList(key: T):Unit = { _scoreList = _scoreList + (key -> new ListBuffer[Int]);}
  def DeleteScoreList(key: T):Unit = {_scoreList = _scoreList - key}

  def AddScoreToList(key: T, score: Int): Unit = {
    _scoreList(key) += score
  }

  def GetScoreList(key:T): ListBuffer[Int] = _scoreList.apply(key)
  def PrintScores(key: T): Unit = {
    _scoreList(key).foreach(score => print(s"${score}"))
  }

  def ScoreToString(key: T): String = {
    var result: String = "[ "
    GetScoreList(key).foreach(score => result += s"${score.toString} ")
    result += "]"
    result
  }

}

