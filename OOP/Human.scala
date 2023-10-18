class Human (age: Int, firstName: String, lastName: String, address: Address){
  private var _age: Int = age
  private var _firstName: String = firstName
  private var _lastName: String = lastName
  private var _address: Address = address

  def Age: Int = _age
  def FirstName: String = _firstName
  def LastName: String = _lastName
  def Address: Address = _address

  def Age_=(value: Int): Unit = { _age = value }
  def FirstName_=(value: String): Unit = { _firstName = value }
  def LastName_=(value: String): Unit = { _lastName = value }
  def Address_=(value: Address): Unit = { _address = value }

  def this(age: Int, firstName: String, lastName: String) = this(age, firstName, lastName, new Address())
  def this(age: Int, firstName: String) = this(age, firstName, "-", new Address())
  def this(age: Int) = this(age, "-", "-", new Address)
  def this() = this(0, "-", "-", new Address())

  def Print(): Unit = {
    println(s"First name: ${if(_firstName == "") '-' else _firstName}, Last name: ${if(_lastName == "") '-' else _lastName}, Age: ${_age}, Address: ${_address}")
  }
}
