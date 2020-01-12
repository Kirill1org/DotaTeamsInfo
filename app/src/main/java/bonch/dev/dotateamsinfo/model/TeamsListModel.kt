package bonch.dev.dotateamsinfo.model

import bonch.dev.dotateamsinfo.model.DAO.Team

data class TeamsListModel(
    var teamList: ArrayList<Team> = ArrayList<Team>()
)
