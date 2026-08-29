# 👋🧩 IPTV Morphe Patches

Custom Morphe patches for **IPTV Pro Stream Player** (`com.iptvprostreamplayer.v1`).

Unlocks the Pro subscription locally by bypassing the Firebase `subscription_needed` flag and forcing the RevenueCat `pro` entitlement to active. Works fully offline — no server validation.

### How to use these patches

Click here to add these patches to Morphe: https://morphe.software/add-source?github=Okazakee/iptv-morphe-patches

In Morphe Manager, add source `Okazakee/iptv-morphe-patches`, select **Unlock Pro (IPTV)** and patch your APK.

## 🩹 Patches list

<!-- PATCHES_START EXPANDED -->
> **[v1.1.0](https://github.com/Okazakee/iptv-morphe-patches/releases/tag/v1.1.0)**&nbsp;&nbsp;•&nbsp;&nbsp;`main`&nbsp;&nbsp;•&nbsp;&nbsp;2 patches total
<details open>
<summary>📦 IPTV Pro Stream Player&nbsp;&nbsp;•&nbsp;&nbsp;2 patches</summary>
<br>

**🎯 Supported versions:**

| 1.2.7 | 2.7.17 |
| :---: | :---: |

| 💊&nbsp;Patch | 📜&nbsp;Description | ⚙️&nbsp;Options |
|----------|----------------|-----------|
| [Bypass Play Store install check (IPTV)](#bypass-play-store-install-check-iptv) | Fixes 'Local install check failed due to wrong installer.' by forcing local installer check and license check to pass. |  |
| [Unlock Pro (IPTV)](#unlock-pro-iptv) | Deactivates subscription_needed gate and forces RevenueCat pro entitlement to active. Works offline, no server check. |  |

</details>

<!-- PATCHES_END -->

### 🛠️ Building locally

- Run `./gradlew buildAndroid`
- The built patches .mpp file is found in `patches/build/libs/patches-*.mpp`
- Patch the mpp file using [Morphe-Desktop](https://github.com/MorpheApp/morphe-desktop)
  like any other patch bundle.

See the [Morphe documentation](https://github.com/MorpheApp/morphe-documentation) for more information.

## 📜 License

IPTV Morphe Patches are licensed under the [GNU General Public License v3.0](LICENSE)
