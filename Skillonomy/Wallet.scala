import util.Random
trait Wallet {
  private var _tokenBalance: Int = 0
  private var _currencyBalance: Int = 0
  def GetTokenBalance: Int = _tokenBalance
  def GetCurrencyBalance: Int = _currencyBalance

  def SetTokenBalance(value: Int): Unit = {_tokenBalance = value}
  def SetCurrencyBalance(value: Int): Unit = {_currencyBalance = value}

  def AddTokens(value: Int): Unit = {_tokenBalance += value}
  def AddCurrency(value: Int): Unit = {_currencyBalance += value}

  def RemoveTokens(value: Int): Unit = {
    if(_tokenBalance < value) _tokenBalance = 0;
    else _tokenBalance -= value
  }
  def RemoveCurrency(value: Int): Unit = {
    if (_currencyBalance < value) _currencyBalance = 0;
    else _currencyBalance -= value
  }

  def TokenPay(user:User, cost: Int): Boolean = {
    if(_tokenBalance >= cost){
      _tokenBalance -= cost
      return true
    }
    if(Exchange.UserBuyTokens(user, cost - _tokenBalance)){
      _tokenBalance -= cost
      return true
    }
    false
  }
}
