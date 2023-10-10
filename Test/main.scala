import scala.collection.mutable.ListBuffer
@main
def main(): Unit = {
  val human_1 : Human = new Human()
  val human_2 : Human = new Human(25, "John", "Philips", new Address())
  val human_3 : Human = new Human(31, "Lina", "Potter", new Address("Ukraine", "Kiev", "Central"))

  human_1.Age = 22
  human_1.FirstName = "Lina"
  human_1.LastName = "Stewart"
  human_1.Address = new Address("Ukraine", "Kherson", "Central")

  human_1.Print()
  println(human_1.Age)
  println()

  val humanList = new ListHuman(ListBuffer(human_1, human_2))
  humanList.AddElement(human_3)

  println("Print all elements of humanList:")
  humanList.Print()
  println()

  println("Find humans by name 'John':")
  val byName = humanList.FindByName("John")
  byName.foreach(human => human.Print())
  println()

  println("Filter list of humans by name 'Lina':")
  val filteredList = humanList.Filter(human => human.FirstName == "Lina")
  filteredList.foreach(human => human.Print())
}