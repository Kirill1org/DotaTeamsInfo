package bonch.dev.dotateamsinfo.model.DAO

data class Player(
    val account_id: Int,
    val name: String?,
    val games_played: Int,
    val wins: Int,
    val is_current_team_member: Boolean?
)