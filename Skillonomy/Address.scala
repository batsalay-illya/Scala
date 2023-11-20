abstract class Address(country: String, city: String, street: String) {
  private var _country: String = country
  private var _city: String = city
  private var _street: String = street

  final def Country: String = _country
  final def City: String = _city
  final def Street: String = _street

  final def County_=(value: String): Unit = { _country = value }
  final def City_=(value: String): Unit = { _city = value }
  final def Street_=(value: String): Unit = { _street = value }

  def this(country: String, city: String) = this(country, city, "-")
  def this(country: String) = this(country, "-", "-")
  def this() = this("-", "-", "-")

  def GetAddressStr(): String = { s"Country: ${if (_country == "") '-' else _country}, City: ${if (_city == "") '-' else _city}, Street: ${if (_country == "") '-' else _street}" }
}