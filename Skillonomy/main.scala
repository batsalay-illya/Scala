import scala.util.Random
@main
def main(): Unit = {
  Simulation(12)
}

def Simulation(period: Int): Unit ={
  DataBase.SimulateRegistration(10)

  DataBase.ListOfTeachers.list.foreach(teacher => teacher.CreateCourse(DataBase.GenerateRandomString(), Random.between(6, 12), if(math.random < 0.5) 500 else 1000, 0.1))
  DataBase.ListOfTeachers.list.foreach(teacher => teacher.CreateOffice(new Office(id = DataBase.ListOfOffices.list.length)))

  for(n <- 1 to period) {
    println("="*15)
    println(s"${n} month")
    DataBase.ListOfOffices.ProcessMonthForAll(n)
    println("="*15)
  }
}

enum Role {
  case None, Student, Teacher
}
