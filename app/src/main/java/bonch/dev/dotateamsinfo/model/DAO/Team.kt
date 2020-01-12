package bonch.dev.dotateamsinfo.model.DAO


data class Team(
    val team_id:Int,
    val rating:Double,
    val wins:Int,
    val losses:Int,
    val name:String,
    val logo_url:String
)