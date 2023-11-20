trait Wallet {
  private var _balance: Int = 0
  def Balance: Int = _balance

  def Balance_=(value: Int): Unit = {_balance = value}
  def AddToBalance(value: Int): Unit = {_balance += value}
  def RemoveFromBalance(value: Int): Unit = {_balance -= value}
}
