/**
 * This is the source code of Cherrygram for Android.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 * Please, be respectful and credit the original author if you use this code.
 *
 * Copyright github.com/arsLan4k1390, 2022-2026.
 */

package org.telegram.ui

import android.os.Handler
import android.os.Looper
import com.aheaditec.talsec_security.security.api.SuspiciousAppInfo
import com.aheaditec.talsec_security.security.api.Talsec
import com.aheaditec.talsec_security.security.api.TalsecConfig
import com.aheaditec.talsec_security.security.api.ThreatListener
import org.telegram.messenger.ApplicationLoader
import org.telegram.messenger.HuaweiApplicationLoader
import uz.unnarsx.cherrygram.Extra
import uz.unnarsx.cherrygram.core.configs.CherrygramCoreConfig
import uz.unnarsx.cherrygram.core.helpers.AppRestartHelper
import uz.unnarsx.cherrygram.donates.DonatesManager

class HuaweiApplicationLoaderlmpl : HuaweiApplicationLoader(), ThreatListener.ThreatDetected {

    /** SDK Integration start */
    companion object {
        private const val expectedPackageName = "com.zettagram.messenger"

        private val expectedSigningCertificateHashBase64 = arrayOf(
            "kvfZiHEa7O+D5gLZZtTHwBlVj+iFxOvReNwW/PQfXVM=" // SHA-256 of extera.jks/zettagram
        )

        private const val watcherMail = "arslan4k1390@gmail.com"

        private val supportedAlternativeStores = arrayOf(
            "com.android.vending",
            "com.sec.android.app.samsungapps",
            "com.huawei.appmarket",
            "com.xiaomi.market",
            "com.oppo.market",
            "com.bbk.appstore",
            "com.lenovo.leos.appstore",
            "com.amazon.venezia"
        )

        private const val isProd = true
        private const val killOnBypass = false
    }

    override fun onCreate() {
        super.onCreate()

        val config = TalsecConfig.Builder(expectedPackageName, expectedSigningCertificateHashBase64)
            .watcherMail(watcherMail)
            .supportedAlternativeStores(supportedAlternativeStores)
            .prod(isProd)
            .killOnBypass(killOnBypass)
            .build()

        ThreatListener(this, null, null).registerListener(this)
        Talsec.start(this, config)

        /*registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Talsec.blockScreenCapture(activity, false)
            }
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {
                ScreenProtector.INSTANCE.registerScreenCallbacks(activity)
            }
            override fun onActivityPaused(activity: Activity) {
                ScreenProtector.INSTANCE.unregisterScreenCallbacks(activity)
            }
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })*/
    }

    override fun onRootDetected() {
        if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications) uh()
    }

    override fun onDebuggerDetected() {
        /*if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications)*/ uh()
    }

    override fun onEmulatorDetected() {
//        if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications) uh()
    }

    override fun onTamperDetected() {
        /*if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications)*/ uh()
    }

    override fun onUntrustedInstallationSourceDetected() {
//        if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications) uh()
    }

    override fun onHookDetected() {
        if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications) uh()
    }

    override fun onDeviceBindingDetected() {
//        if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications) uh()
    }

    override fun onObfuscationIssuesDetected() {
        /*if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications)*/ uh()
    }

    override fun onMalwareDetected(suspiciousApps: List<SuspiciousAppInfo>) {
//        if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications) uh()
    }

    override fun onScreenshotDetected() {
//        if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications) uh()
    }

    override fun onScreenRecordingDetected() {
//        if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications) uh()
    }

    override fun onMultiInstanceDetected() {
//        if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications) uh()
    }

    override fun onUnsecureWifiDetected() {
//        if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications) uh()
    }

    override fun onTimeSpoofingDetected() {
//        if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications) uh()
    }

    override fun onLocationSpoofingDetected() {
//        if (!DonatesManager.checkAllDonatedAccountsForMarketplace() && !CherrygramCoreConfig.showNotifications) uh()
    }
    /** SDK Integration finish */

    private fun uh() {
        Handler(Looper.getMainLooper()).postDelayed({
            AppRestartHelper.restartApp(ApplicationLoader.applicationContext)
        }, 15_000)
    }

}
