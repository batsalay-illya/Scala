import scala.util.Random
@main
def main(): Unit = {
  Simulation(12)
}

def Simulation(period: Int): Unit ={
  DataBase.SimulateRegistration(10)

  println(s"Teachers: ${DataBase.ListOfAllTeachers.list.length}, Students: ${DataBase.ListOfAllStudents.list.length}")


  DataBase.ListOfAllTeachers.list.foreach(teacher => teacher.CreateCourse(DataBase.GenerateRandomString(), Random.between(6, 12), if(math.random < 0.5) 500 else 1000, 0.1))
  DataBase.ListOfAllTeachers.list.foreach(teacher => teacher.CreateOffice(new Office(id = DataBase.ListOfAllOffices.list.length)))

  println(s"Offices: ${DataBase.ListOfAllOffices.list.length}")

  for(n <- 1 to period) {
    println("="*15)
    println(s"MONTH:${n}")
    DataBase.ListOfAllOffices.list.foreach(office => {
      office.ProcessMonth(n)
    })
    DataBase.SimulateUserTransactions()
    Exchange.Print()
    println("="*15)
  }
}

enum Role {
  case None, Student, Teacher
}