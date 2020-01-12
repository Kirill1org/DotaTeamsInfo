package bonch.dev.dotateamsinfo.model

import bonch.dev.dotateamsinfo.model.DAO.PlayerAccount
import bonch.dev.dotateamsinfo.model.DAO.Profile

data class PlayerInfoModel(
    var playerAccount: PlayerAccount = PlayerAccount(Profile("name","url","code"),null)
)
