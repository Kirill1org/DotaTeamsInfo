package bonch.dev.dotateamsinfo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.dotateamsinfo.model.DAO.Player
import bonch.dev.dotateamsinfo.R

class PlayersAdapter(val list: ArrayList<Player>, val context: Context) :
    RecyclerView.Adapter<PlayersAdapter.PlayerHolder>() {

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        return PlayerHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.player_unit, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        val player = list[position]
        holder.bind(player)
    }

    inner class PlayerHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        private val playerNickTV = itemview.findViewById<TextView>(R.id.playerNickTV)
        private val playerGamesCountTV = itemview.findViewById<TextView>(R.id.playerGamesTV)
        private val playerWinsCountTV = itemview.findViewById<TextView>(R.id.playerWinTV)
        private val playerIsInTeamTV = itemview.findViewById<TextView>(R.id.playerIsComandTV)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition].account_id!!)
            }
        }

        fun bind(player: Player) {
            playerNickTV.text = player.name
            playerGamesCountTV.text = player.games_played.toString()
            playerWinsCountTV.text = player.wins.toString()
            playerIsInTeamTV.text = player.is_current_team_member.toString()

        }

    }

/*    fun removeAlbum(position: Int) {
        list.removeAt(position)
        notifyDataSetChanged()
    }*/
}

