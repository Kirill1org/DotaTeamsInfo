package bonch.dev.dotateamsinfo.view


interface IPlayerInfoView  {
    fun showPlayerInfo(
        personaname: String,
        loccountrycode: String?,
        avatarfull: String,
        competitiveRank: Integer?
    )
    fun showErrorMessage(s: String)
}