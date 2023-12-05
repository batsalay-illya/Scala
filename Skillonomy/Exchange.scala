object Exchange {
  private var _tokenAmount: Int = 1000000
  private var _fiat: Int = 1000000
  private var _price: Float = 1

  private val _fee: Float = 0.05;

  def TokenAmount: Int = _tokenAmount
  def Price: Double = _price
  def Fee: Float = _fee

  def UserBuyTokens(user: User, amount: Int): Boolean = {
    if (_tokenAmount < amount) {
      println("Not enough tokens...")
      return false
    }

    var totalPrice: Int = (_price * amount).toInt
    if (user.GetCurrencyBalance >= totalPrice){
      user.RemoveCurrency(totalPrice)
      user.AddTokens(amount)
      _tokenAmount -= amount
      _fiat += totalPrice

      CalculatePrice()
      return true
    }
    false
  }
  def UserSellTokens(user: User, amount: Int): Boolean = {
    var totalPrice: Int = (_price * amount).toInt
    if(_fiat < totalPrice){
      println("Not enough tokens...")
      return false
    }

    if (user.GetTokenBalance >= amount) {
      user.RemoveTokens(amount)
      user.AddCurrency(totalPrice)
      _tokenAmount += amount
      _fiat -= totalPrice

      CalculatePrice()
      return true
    }
    false
  }

  def TransferFromUserToUser(fromUser:User, toUser:User, amount:Int): Boolean = {
    if(fromUser.GetTokenBalance >= amount + (amount*_fee).toInt) {
      fromUser.TokenPay(fromUser, amount + (amount*_fee).toInt)
      toUser.AddTokens(amount)
      GetTransactionFee(amount)
      return true
    } 
    if(fromUser.GetCurrencyBalance >= (fromUser.GetTokenBalance - (amount + (amount*_fee).toInt)) * _price) {
      UserBuyTokens(fromUser, fromUser.GetTokenBalance - (amount + (amount*_fee).toInt))
      fromUser.TokenPay(fromUser, amount + (amount * _fee).toInt)
      toUser.AddTokens(amount)
      GetTransactionFee(amount)
      return true
    }
    false
  }
  def TransferToUser(toUser: User, amount:Int): Unit = {
    _tokenAmount -= amount
    toUser.AddTokens(amount - (amount*_fee).toInt)
    GetTransactionFee(amount)
  }
  def TransferFromUser(fromUser:User, amount:Int): Boolean = {
    if(fromUser.GetTokenBalance >= amount){
      fromUser.RemoveTokens(amount)
      _tokenAmount += amount
      return true
    }
    false
  }
  def GetTransactionFee(transactionAmount: Int): Unit = {
    _tokenAmount += (transactionAmount * _fee).toInt
    CalculatePrice()
  }

  def Print(): Unit = {
    println(s"[Exchange data] - Token amount: ${_tokenAmount}, Fiat: ${_fiat}, Price: ${_price}")
  }
  private def CalculatePrice(): Float = {
    _price = _fiat.toFloat/_tokenAmount.toFloat
    _price
  }
}
