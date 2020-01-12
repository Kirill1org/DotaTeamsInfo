package bonch.dev.dotateamsinfo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.dotateamsinfo.R
import bonch.dev.dotateamsinfo.adapter.PlayersAdapter
import bonch.dev.dotateamsinfo.model.DAO.Player
import bonch.dev.dotateamsinfo.presenter.TeamPlayersListPresenter

class TeamPlayersListFragment : Fragment(),ITeamPlayersListView {

    companion object {

        @JvmStatic
        fun newInstance(teamId: Int) =
            TeamPlayersListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, teamId)
                }
            }
    }

    var teamPlayersListPresenter: TeamPlayersListPresenter? = TeamPlayersListPresenter(this)

    private var teamId: Int = 0

    private lateinit var recyclerView: RecyclerView
    private lateinit var playersAdapter: PlayersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            teamId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_players_list, container, false)
    }

    override fun onDetach() {
        super.onDetach()
        teamPlayersListPresenter = null

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        teamPlayersListPresenter?.loadPlayers(teamId)
    }

    private fun initViews(view: View) {

        recyclerView = view.findViewById(R.id.teamPlayersListRV)
    }

    private fun startPlayerInfoFragment(accountId: Int) {

        getActivity()!!.getSupportFragmentManager().beginTransaction()
            .replace(
                R.id.fragment_container,
                PlayerInfoFragment().newInstance(accountId),
                "PlayerInfoFragment"
            )
            .addToBackStack("PlayerInfoFragment")
            .commit()

    }

    override fun showPlayersList(playersList: ArrayList<Player>) {

        recyclerView.layoutManager = LinearLayoutManager(context)
        playersAdapter = PlayersAdapter(playersList, activity!!.applicationContext)

        recyclerView.adapter = playersAdapter
        playersAdapter.onItemClick = { accountId ->
            startPlayerInfoFragment(accountId)
        }

    }

    override fun showErrorMessage(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

}
