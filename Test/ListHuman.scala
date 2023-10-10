import scala.collection.mutable.ListBuffer
class ListHuman(private val listOfHumans: ListBuffer[Human] = ListBuffer()) {
  private var _list = listOfHumans

  def list: ListBuffer[Human] = { _list }
  def list_=(value: ListBuffer[Human]): Unit = { _list = value }

  def AddElement(value: Human): Unit = { _list += value }
  def AddElements(value: List[Human]): Unit = { value.foreach(elem => _list += elem) }

  def DeleteElement(value: Human): Unit = { _list -= value }
  def DeleteElements(value: List[Human]): Unit = { value.foreach(elem => _list -= elem) }

  def FindByName(name: String): List[Human] = { _list.toList.filter(human => human.FirstName == name) }
  def Filter(p: Human => Boolean): List[Human] = { _list.toList.filter(p) }
  def Print(): Unit = { _list.foreach(human => human.Print()) }
}