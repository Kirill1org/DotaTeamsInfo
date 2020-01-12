package bonch.dev.dotateamsinfo.view

import bonch.dev.dotateamsinfo.model.DAO.Player



interface ITeamPlayersListView{
    fun showPlayersList(playersList: ArrayList<Player>)
    fun showErrorMessage(s: String)
}
