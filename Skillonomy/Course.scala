case class Course(name:String = "", period:Int = 0, salary:Int = 0, successFee:Float = 0, topRewardCoefficient:Float = 1.2, topMidRewardCoefficient:Float = 1.1, midRewardCoefficient:Float = 1, lowMidRewardCoefficient:Float = 0.85, lowRewardCoefficient:Float = 0.75) {
  private var _name: String = name
  private var _period: Int = period
  private var _salary: Int = salary
  private var _successFee: Float = successFee

  private val _topRewardCoefficient: Float = topRewardCoefficient
  private val _topMidRewardCoefficient: Float = topMidRewardCoefficient
  private val _midRewardCoefficient: Float = midRewardCoefficient
  private val _lowMidRewardCoefficient: Float = lowMidRewardCoefficient
  private val _lowRewardCoefficient: Float = lowRewardCoefficient

  final def Name: String = _name
  final def Period: Int = _period
  final def Salary: Int = _salary
  final def SuccessFee: Float = _successFee

  final def TopRewardCoefficient: Float = _topRewardCoefficient
  final def TopMidRewardCoefficient: Float = _topMidRewardCoefficient
  final def MidRewardCoefficient: Float = _midRewardCoefficient
  final def LowMidRewardCoefficient: Float = _lowMidRewardCoefficient
  final def LowRewardCoefficient: Float = _lowRewardCoefficient

  final def Name_=(value: String): Unit = { _name = value }
  final def Period_=(value: Int): Unit = { _period = value }
  final def Salary_=(value: Int): Unit = { _salary = value }
  final def SuccessFee_=(value: Float): Unit = { _successFee = value }

  def GetTaskRewardAmount(score:Int): Int ={
    score match
      case 1 => (_salary * _lowRewardCoefficient).toInt
      case 2 => (_salary * _lowMidRewardCoefficient).toInt
      case 3 => (_salary * _midRewardCoefficient).toInt
      case 4 => (_salary * _topMidRewardCoefficient).toInt
      case 5 => (_salary * _topRewardCoefficient).toInt

  }
  def Print(): Unit = {
    println(s"Course name:${_name}, Period:${_period}, Salary:${_salary}, SuccessFee:${_successFee} ");
  }
}
