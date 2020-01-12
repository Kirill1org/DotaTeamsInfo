package bonch.dev.dotateamsinfo.presenter

import android.util.Log
import bonch.dev.dotateamsinfo.model.DAO.Team
import bonch.dev.dotateamsinfo.model.TeamsListModel
import bonch.dev.dotateamsinfo.view.ITeamPlayersListView
import bonch.dev.dotateamsinfo.view.ITeamsListView
import bonch.dev.networking.networking.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TeamsListPresenter {

    private var teamListModel: TeamsListModel = TeamsListModel()

    private var teamListView: ITeamsListView

    constructor(teamListView: ITeamsListView) {
        this.teamListView = teamListView
    }

    fun loadTeams() {
        val service = RetrofitFactory.makeRetrofitService()

        CoroutineScope(Dispatchers.IO).launch {

            val response = service.getTeams()

            try {

                withContext(Dispatchers.Main) {

                    if (response.isSuccessful) {
                        teamListModel.teamList.addAll(response.body()!!)
                        teamListView.showTeamsList(teamListModel.teamList)

                    } else {
                        teamListView.showErrorMessage("${response.errorBody()}")
                    }
                }

            } catch (err: HttpException) {

                Log.e("Retrofit", "${err.printStackTrace()}")

            }
        }
    }
}