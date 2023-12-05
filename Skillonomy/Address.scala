trait Address(country: String, city: String, street: String) {
  private var _country: String = country
  private var _city: String = city
  private var _street: String = street

  final def Country: String = _country
  final def City: String = _city
  final def Street: String = _street

  final def County_=(value: String): Unit = { _country = value }
  final def City_=(value: String): Unit = { _city = value }
  final def Street_=(value: String): Unit = { _street = value }

  def GetFullAddress(): String = { s"Country: ${_country}, City: ${_city}, Street: ${_street}" }
}