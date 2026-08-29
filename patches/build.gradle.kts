group = "app.iptv"

patches {
    about {
        name = "IPTV IPTV patches"
        description = "Unlock Pro for IPTV Pro Stream Player (com.iptvprostreamplayer.v1) - bypasses Firebase subscription_needed and RevenueCat pro entitlement"
        source = "git@github.com:Okazakee/iptv-morphe-patches.git"
        author = "okazakee"
        contact = "na"
        website = "na"
        license = "GPLv3"
    }
}

// Separate configuration so gson is available at runtime for the
// generatePatchesList task but never bundled into the APK.
val patchListGeneratorClasspath = configurations.create("patchListGeneratorClasspath")

dependencies {
    compileOnly(libs.gson)
    patchListGeneratorClasspath(libs.gson)
}

tasks {
    register<JavaExec>("generatePatchesList") {
        description = "Build patch with patch list"

        dependsOn(build)

        classpath = sourceSets["main"].runtimeClasspath + patchListGeneratorClasspath
        mainClass.set("util.PatchListGeneratorKt")
    }

    // Used by gradle-semantic-release-plugin.
    publish {
        dependsOn("generatePatchesList")
    }
}
