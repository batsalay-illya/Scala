class Address(private val country: String = "", private val city: String = "", private val street: String = "") {
  private var _country: String = country
  private var _city: String = city
  private var _street: String = street

  def Country: String = _country
  def City: String = _city
  def Street: String = _street

  def County_=(value: String): Unit = { _country = value }
  def City_=(value: String): Unit = { _city = value }
  def Street_=(value: String): Unit = { _street = value }

  def Print(): Unit = { 
    println(s"Country: ${if(_country == "") '-' else _country }, City: ${if(_city == "") '-' else _city}, Street: ${if(_country == "") '-' else _street}") 
  }
}