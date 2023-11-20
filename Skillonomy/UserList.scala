import scala.collection.mutable.ListBuffer
class UserList(listOfUsers: ListBuffer[User] = ListBuffer()) extends CustomList(listOfUsers) {

  def FindByName(name: String): List[User] = { list.toList.filter(user => user.FirstName == name) }
  def Print(): Unit = { list.foreach(user => user.Print()) }
}
