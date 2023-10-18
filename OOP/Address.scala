class Address(country: String, city: String, street: String) {
  private var _country: String = country
  private var _city: String = city
  private var _street: String = street

  def Country: String = _country
  def City: String = _city
  def Street: String = _street

  def County_=(value: String): Unit = { _country = value }
  def City_=(value: String): Unit = { _city = value }
  def Street_=(value: String): Unit = { _street = value }

  def this(country: String, city: String) = this(country, city, "-")
  def this(country: String) = this(country, "-", "-")
  def this() = this("-", "-", "-")

  override def toString = s"Country: ${if(_country == "") '-' else _country }, City: ${if(_city == "") '-' else _city}, Street: ${if(_country == "") '-' else _street}"
}