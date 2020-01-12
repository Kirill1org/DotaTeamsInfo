package bonch.dev.dotateamsinfo.presenter

import android.util.Log
import bonch.dev.dotateamsinfo.model.PlayerInfoModel
import bonch.dev.dotateamsinfo.view.IPlayerInfoView
import bonch.dev.networking.networking.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class PlayerInfoPresenter {


    private var playerInfoView: IPlayerInfoView

    constructor(playerInfoView: IPlayerInfoView) {
        this.playerInfoView = playerInfoView
    }

    fun loadAccInfo(accountId: Int) {
        val service = RetrofitFactory.makeRetrofitService()

        CoroutineScope(Dispatchers.IO).launch {

            val response = service.getPlayerInfo(accountId)

            try {

                withContext(Dispatchers.Main) {

                    if (response.isSuccessful) {

                        val  playerInfoModel : PlayerInfoModel = PlayerInfoModel(response.body()!!)

                        playerInfoView.showPlayerInfo(
                            response.body()!!.profile.personaname,
                            response.body()!!.profile.loccountrycode,
                            response.body()!!.profile.avatarfull,
                            response.body()!!.competitiveRank
                        )

                    } else {

                        playerInfoView.showErrorMessage("${response.errorBody()}")
                    }
                }

            } catch (err: HttpException) {

                Log.e("Retrofit", "${err.printStackTrace()}")

            }
        }
    }
}