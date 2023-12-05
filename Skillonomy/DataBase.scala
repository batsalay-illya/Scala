import scala.util.Random
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

object DataBase {
  private val _listOfAllStudents: CustomList[Student] = new CustomList()
  private val _listOfAllTeachers: CustomList[Teacher] = new CustomList()
  private val _listOfAllCourses: CustomList[Course] = new CustomList()
  private val _listOfAllOffices: CustomList[Office] = new CustomList()

  private val _courseCost: Int = 500
  private val _officeCost: Int = 1000

  def ListOfAllStudents: CustomList[Student] = _listOfAllStudents
  def ListOfAllTeachers: CustomList[Teacher] = _listOfAllTeachers
  def ListOfAllCourses: CustomList[Course] = _listOfAllCourses
  def ListOfAllOffices: CustomList[Office] = _listOfAllOffices

  def CourseCost: Int = _courseCost
  def OfficeCost: Int = _officeCost

  def SimulateRegistration(userCount: Int): Unit = {
    if(userCount <= 1) {
      SimulateRegistration(2)
      return
    }

    //need to control amount of users/students/teachers
    for (u <- 1 to userCount) {
      breakable {
        if (math.random < 0.15 || u == userCount && _listOfAllTeachers.list.isEmpty) {
          CreateNewTeacher()
          break
        }

        CreateNewStudent()
      }
    }
  }
  def SimulateUserTransactions(): Unit = {
    _listOfAllStudents.list.foreach(student =>{
      if((student.GetTokenBalance/4).toInt > 0 && math.random < 0.15) {
        val tokensToSell: Int = Random.between(0, (student.GetTokenBalance/4).toInt)
        Exchange.UserSellTokens(student, tokensToSell)
      }
      //if((student.GetCurrencyBalance).toInt > 0 && math.random < 0.4){
      //  val currencySpend: Int = Random.between(0, (student.GetCurrencyBalance/2).toInt)
      //  student.RemoveCurrency(currencySpend)
      //}
      
      //if((student.GetTokenBalance/4).toInt > 0){
      //  val tokensToSpend: Int = Random.between(0, (student.GetTokenBalance/4).toInt)
      //  student.RemoveTokens(tokensToSpend)
      //  Exchange.GetTransactionFee(tokensToSpend)
      //}
    })
    
    _listOfAllTeachers.list.foreach(teacher => {
      if((teacher.GetTokenBalance/4) > 0 && Random.nextBoolean()) {
        val tokensSpend: Int = Random.between(0, (teacher.GetTokenBalance/4).toInt)
        Exchange.UserSellTokens(teacher, tokensSpend)
      }
      //if (teacher.GetCurrencyBalance > 0 && math.random < 0.4) {
      //  val currencySpend: Int = Random.between(0, (teacher.GetCurrencyBalance / 2).toInt)
      //  teacher.RemoveCurrency(currencySpend)
      //}
      //if (teacher.GetTokenBalance > 0) {
      //  val tokensToSpend: Int = Random.between(0, (teacher.GetTokenBalance / 4).toInt)
      //  teacher.RemoveTokens(tokensToSpend)
      //  Exchange.GetTransactionFee(tokensToSpend)
      //}
    })
  }


  def CreateNewTeacher(): Teacher = {
    val newTeacher: Teacher = new Teacher(User(GenerateRandomString(), GenerateRandomString(), 0, GenerateRandomString(), GenerateRandomString(), GenerateRandomString()))
    newTeacher.AddCurrency(2500)
    Exchange.UserBuyTokens(newTeacher, 1500)
    _listOfAllTeachers.AddElement(newTeacher)
    newTeacher
  }
  def CreateNewStudent(): Student = {
    val newStudent: Student = new Student(User(GenerateRandomString(), GenerateRandomString(), 0, GenerateRandomString(), GenerateRandomString(), GenerateRandomString()))
    newStudent.AddCurrency(1000)
    Exchange.UserBuyTokens(newStudent, 500)
    _listOfAllStudents.AddElement(newStudent)
    newStudent
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