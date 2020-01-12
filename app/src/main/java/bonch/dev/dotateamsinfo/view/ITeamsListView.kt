package bonch.dev.dotateamsinfo.view

import bonch.dev.dotateamsinfo.model.DAO.Team

interface ITeamsListView {
    fun showTeamsList(teamList: ArrayList<Team>)
    fun showErrorMessage(s: String)
}