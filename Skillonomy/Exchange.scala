object Exchange {
  private var tokenAmount: Int = 1000000
  private var reserve: Int = 0


  def TransferToUser(user:User, amount:Int): Unit = {
    if(tokenAmount >= amount){
      user.AddToBalance(amount)
      tokenAmount = amount
    }else{
      println(s"Not enough tokens for transferring to user ${user.FirstName}...")
    }
  }
}
