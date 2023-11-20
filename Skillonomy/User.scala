class User(firstName: String, lastName: String, age: Int, country: String, city: String, street: String) extends Address(country, city, street) with Wallet {
  private var _age: Int = age
  private var _firstName: String = firstName
  private var _lastName: String = lastName

  final def Age: Int = _age
  final def FirstName: String = _firstName
  final def LastName: String = _lastName

  final def Age_=(value: Int): Unit = { _age = value }
  final def FirstName_=(value: String): Unit = { _firstName = value }
  final def LastName_=(value: String): Unit = { _lastName = value }

  def Print(): Unit = {
    println(s"First name: ${if(_firstName == "") '-' else _firstName}, Last name: ${if(_lastName == "") '-' else _lastName}, Age: ${_age}, Address: [${GetAddressStr()}], Balance: ${Balance}")
  }
}
