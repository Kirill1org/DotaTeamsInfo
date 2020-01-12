package bonch.dev.dotateamsinfo.model

import bonch.dev.dotateamsinfo.model.DAO.Player


data class TeamPlayersListModel(
    var playersList: ArrayList<Player> = ArrayList<Player>()
)
