import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag

class CustomList[T]() {
  private var _list: ListBuffer[T] = ListBuffer()

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
  def Contains(p: T): Boolean = {
    try {
      if(_list.contains(p)) true
      else false
    } catch {
      case e:Exception => {
        println("list.contain check error, return false...")
        false
      }
    }
  }
}