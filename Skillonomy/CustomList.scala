import scala.collection.mutable.ListBuffer

abstract class CustomList[T](listBuffer: ListBuffer[T] = ListBuffer()) {
  private var _list: ListBuffer[T] = listBuffer

  def list: ListBuffer[T] = { _list }
  def list_=(value: ListBuffer[T]): Unit = { _list = value }

  def AddElement(value: T): Unit = { _list += value }
  def AddElements(value: CustomList[T]): Unit = { value.list.foreach(elem => _list += elem)}
  def AddElements(value: List[T]): Unit = { value.foreach(elem => _list += elem) }
  def AddElements(value: ListBuffer[T]): Unit = { value.foreach(elem => _list += elem) }

  def DeleteElement(value: T): Unit = { _list -= value }
  def DeleteElements(value: List[T]): Unit = { _list --= value }
  def DeleteElements(value: ListBuffer[T]): Unit = { _list --= value }
  def DeleteElements(value: CustomList[T]): Unit = { _list --= value.list }

  def Filter(p: T => Boolean): List[T] = { _list.toList.filter(p) }
}