package app.iptv.patches.unlock

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.extensions.instructions
import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patcher.util.proxy.mutableTypes.MutableMethod.Companion.toMutable

/**
 * Bypasses Google Play install source check (Pairip LicenseClient).
 * The app verifies getInstallSourceInfo().getInstallingPackageName() == "com.android.vending"
 * via performLocalInstallerCheck()Z and also does remote licensing via checkLicense().
 * Sideloaded / Morphe-patched APKs fail this and trigger paywall/lock.
 *
 * Patch: force both checks to pass + kill paywall activity.
 */
@Suppress("unused")
val installSourcePatch = bytecodePatch(
    name = "Bypass Play Store install check (IPTV)",
    description = "Fixes 'Local install check failed due to wrong installer.' by forcing local installer check and license check to pass + disabling paywall activity.",
    default = true
) {
    compatibleWith(Constants.COMPATIBILITY_IPTV)

    execute {
        // Lcom/pairip/licensecheck/LicenseClient;->checkLicense(Landroid/content/Context;)V
        try {
            LicenseCheckFingerprint.method.toMutable().apply {
                instructions.clear()
                addInstructions(0, """
                    sget-object v0, Lcom/pairip/licensecheck/LicenseClient${"$"}LicenseCheckState;->FULL_CHECK_OK:Lcom/pairip/licensecheck/LicenseClient${"$"}LicenseCheckState;
                    sput-object v0, Lcom/pairip/licensecheck/LicenseClient;->licenseCheckState:Lcom/pairip/licensecheck/LicenseClient${"$"}LicenseCheckState;
                    return-void
                """.trimIndent())
            }
        } catch (_: Exception) {
            try {
                LicenseCheckFingerprint.method.toMutable().apply {
                    instructions.clear()
                    addInstructions(0, "return-void")
                }
            } catch (_: Exception) {}
        }

        try {
            LocalInstallerCheckFingerprint.method.toMutable().apply {
                instructions.clear()
                addInstructions(0, """
                    const/4 v0, 0x1
                    return v0
                """.trimIndent())
            }
        } catch (_: Exception) {}

        try {
            LicenseActivityOnStartFingerprint.method.toMutable().apply {
                instructions.clear()
                addInstructions(0, "return-void")
            }
        } catch (_: Exception) {}
        try {
            LicenseActivityPaywallFingerprint.method.toMutable().apply {
                instructions.clear()
                addInstructions(0, "return-void")
            }
        } catch (_: Exception) {}
        try {
            LicenseActivityErrorDialogFingerprint.method.toMutable().apply {
                instructions.clear()
                addInstructions(0, "return-void")
            }
        } catch (_: Exception) {}
    }
}
