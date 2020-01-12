package bonch.dev.dotateamsinfo.presenter

import android.util.Log
import bonch.dev.dotateamsinfo.model.TeamPlayersListModel
import bonch.dev.dotateamsinfo.view.ITeamPlayersListView
import bonch.dev.networking.networking.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TeamPlayersListPresenter {
    private var teamPlayersListModel: TeamPlayersListModel = TeamPlayersListModel()
    private var teamPlayersListView: ITeamPlayersListView

    constructor(teamPlayersListView: ITeamPlayersListView) {
        this.teamPlayersListView = teamPlayersListView
    }

    fun loadPlayers(teamId: Int) {
        val service = RetrofitFactory.makeRetrofitService()

        CoroutineScope(Dispatchers.IO).launch {

            val response = service.getPlayers(teamId)

            try {

                withContext(Dispatchers.Main) {

                    if (response.isSuccessful) {
                        teamPlayersListModel.playersList.addAll(response.body()!!)
                        teamPlayersListView.showPlayersList(teamPlayersListModel.playersList)

                    } else {

                        teamPlayersListView.showErrorMessage("${response.errorBody()}")
                    }
                }

            } catch (err: HttpException) {

                Log.e("Retrofit", "${err.printStackTrace()}")

            }
        }
    }
}