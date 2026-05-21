package uz.unnarsx.cherrygram.core.configs

import android.app.Activity
import android.content.SharedPreferences
import org.telegram.messenger.ApplicationLoader
import uz.unnarsx.cherrygram.preferences.boolean

object ZettaConfig {

    private val prefs: SharedPreferences =
        ApplicationLoader.applicationContext.getSharedPreferences("zettaconfig", Activity.MODE_PRIVATE)

    var ghostMode by prefs.boolean("ZG_GhostMode", false)
    var antiDelete by prefs.boolean("ZG_AntiDelete", false)
    var localPremium by prefs.boolean("ZG_LocalPremium", false)

}
