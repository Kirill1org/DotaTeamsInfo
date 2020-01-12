package bonch.dev.dotateamsinfo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import bonch.dev.dotateamsinfo.R
import bonch.dev.dotateamsinfo.presenter.PlayerInfoPresenter
import com.bumptech.glide.Glide

const val ARG_PARAM1 = "param1"

class PlayerInfoFragment : Fragment(), IPlayerInfoView {

    private var accountId: Int = 0

    private lateinit var accountIV: ImageView
    private lateinit var accountName: TextView
    private lateinit var accountCountryCode: TextView
    private lateinit var accountSoloMmr: TextView

    var playerInfoPresenter: PlayerInfoPresenter? = PlayerInfoPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            accountId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onDetach() {
        super.onDetach()
        playerInfoPresenter = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        playerInfoPresenter?.loadAccInfo(accountId)

    }

    private fun initViews(view: View) {
        accountIV = view.findViewById(R.id.accountIV)
        accountName = view.findViewById(R.id.accountNameTV)
        accountCountryCode = view.findViewById(R.id.countryCodeTV)
        accountSoloMmr = view.findViewById(R.id.soloMmrTV)

    }

    fun newInstance(accountId: Int) =
        PlayerInfoFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_PARAM1, accountId)
            }
        }

    override fun showPlayerInfo(
        personaname: String,
        loccountrycode: String?,
        avatarfull: String,
        competitiveRank: Integer?
    ) {
        Glide.with(context!!.applicationContext)
            .load(avatarfull)
            .into(accountIV)

        accountName.text = personaname
        accountCountryCode.text = loccountrycode
        accountSoloMmr.text = competitiveRank.toString()


    }

    override fun showErrorMessage(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }
}

