package bonch.dev.networking.networking


import bonch.dev.dotateamsinfo.model.DAO.Player
import bonch.dev.dotateamsinfo.model.DAO.PlayerAccount
import bonch.dev.dotateamsinfo.model.DAO.Team
import retrofit2.Response
import retrofit2.http.*


interface RetrofitService {

    @GET("api/teams")
    suspend fun getTeams(): Response<List<Team>>

    @GET("api/teams/{team_id}/players")
    suspend fun getPlayers(@Path("team_id") teamId: Int): Response<List<Player>>

    @GET("api/players/{account_id}")
    suspend fun getPlayerInfo(@Path("account_id") accountId: Int): Response<PlayerAccount>



/*    @GET("/users")
    suspend fun getUsers(): Response<List<User>>*/


}