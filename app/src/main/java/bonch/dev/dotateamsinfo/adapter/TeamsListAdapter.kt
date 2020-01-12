package bonch.dev.dotateamsinfo.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.dotateamsinfo.R
import bonch.dev.dotateamsinfo.model.DAO.Team
import com.bumptech.glide.Glide

class TeamsAdapter(val list: ArrayList<Team>, val context: Context) :
    RecyclerView.Adapter<TeamsAdapter.TeamHolder>() {

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        return TeamHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.team_unit, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        val team = list[position]
        holder.bind(team)
    }

    inner class TeamHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        private val teamTitleTV = itemview.findViewById<TextView>(R.id.teamTitleTV)
        private val teamWinsTV = itemview.findViewById<TextView>(R.id.teamWinTV)
        private val teamLossesTV = itemview.findViewById<TextView>(R.id.teamLoseTV)
        private val teamRatingTV = itemview.findViewById<TextView>(R.id.teamRatingTV)
        private val teamIV = itemview.findViewById<ImageView>(R.id.teamIV)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition].team_id)
            }
        }

        fun bind(team: Team) {
            teamTitleTV.text = team.name
            teamWinsTV.text = team.wins.toString()
            teamLossesTV.text = team.losses.toString()
            teamRatingTV.text = team.rating.toString()

            Glide.with(itemView)
                .load(team.logo_url)
                .into(teamIV)

        }

    }

/*    fun removeAlbum(position: Int) {
        list.removeAt(position)
        notifyDataSetChanged()
    }*/
}

