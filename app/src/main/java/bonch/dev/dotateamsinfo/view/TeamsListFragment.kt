package bonch.dev.dotateamsinfo.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.dotateamsinfo.R
import bonch.dev.dotateamsinfo.adapter.TeamsAdapter
import bonch.dev.dotateamsinfo.model.DAO.Team
import bonch.dev.dotateamsinfo.presenter.TeamsListPresenter


class TeamsListFragment : Fragment(), ITeamsListView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var teamsAdapter: TeamsAdapter

    var teamsListPresenter: TeamsListPresenter? = TeamsListPresenter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teams_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews(view)
        teamsListPresenter?.loadTeams()


    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.teamsListRV)
    }

    override fun onDetach() {
        super.onDetach()
        teamsListPresenter = null
    }

    private fun startTeamPlayersFragment(teamId: Int) {

        getActivity()!!.getSupportFragmentManager().beginTransaction()
            .replace(
                R.id.fragment_container,
                TeamPlayersListFragment.newInstance(teamId),
                "teamPlayersFragment"
            )
            .addToBackStack("teamPlayersFragment")
            .commit()

    }

    override fun showTeamsList(teamList: ArrayList<Team>) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        teamsAdapter = TeamsAdapter(teamList, activity!!.applicationContext)

        recyclerView.adapter = teamsAdapter
        teamsAdapter.onItemClick = { teamId -> startTeamPlayersFragment(teamId) }

    }

    override fun showErrorMessage(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()

    }
}
