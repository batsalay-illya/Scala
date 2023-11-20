import scala.collection.mutable.ListBuffer

class OfficeList(listOfOffices: ListBuffer[Office] = ListBuffer()) extends CustomList(listOfOffices) {

  def FilterByID(id: Int): List[Office] = {
    list.toList.filter(office => office.ID == id)
  }
  def FilterByLocation(location: String): List[Office] = {
    list.toList.filter(office => office.Location == location)
  }
  def GetOffice(location: String, id: Int): Office = {
    list.foreach(office => {
      if(office.Location == location && office.ID == id)
      {
        return office
      }
    })
    println("There is no offices with given parameters, function return empty class...")
    new Office()
  }
  def ProcessMonthForAll(month: Int): Unit ={ list.foreach(office => office.ProcessMonth(month)) }
  def PrintOfficesData(): Unit = { list.foreach(office => office.PrintData(false)) }
  def PrintFullOfficesData(): Unit = { list.foreach(office => office.PrintData(true)) }

}