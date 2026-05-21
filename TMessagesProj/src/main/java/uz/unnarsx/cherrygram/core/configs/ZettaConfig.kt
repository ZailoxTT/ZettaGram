package uz.unnarsx.cherrygram.core.configs

import android.app.Activity
import android.content.SharedPreferences
import org.telegram.messenger.ApplicationLoader
import uz.unnarsx.cherrygram.preferences.boolean

object ZettaConfig {

    private val prefs: SharedPreferences =
        ApplicationLoader.applicationContext.getSharedPreferences("zettaconfig", Activity.MODE_PRIVATE)

    var testFeatureOne by prefs.boolean("ZG_TestFeatureOne", false)
    var testFeatureTwo by prefs.boolean("ZG_TestFeatureTwo", false)
    var testFeatureThree by prefs.boolean("ZG_TestFeatureThree", false)

}
