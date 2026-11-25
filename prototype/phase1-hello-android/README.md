# Phase 1 sample — minimal Android (Kotlin)

Proyek ini untuk **bukti feasibility** (PRD Phase 1): build **lokal di device Android ARM64** menghasilkan APK.

## Yang perlu disiapkan di device (on-device)

- JDK, Gradle (atau gunakan Gradle wrapper jika sudah di-generate di lingkungan yang punya Gradle), Android SDK + `platforms` + `build-tools`.
- Versi **Kotlin / AGP / compileSdk / Gradle** di file ini mungkin **perlu disesuaikan** (lihat PRD OQ-012). Catat baseline final di `docs/prototype-phase1/BUILD_EVIDENCE.md`.

## Catatan penting

- Build **tidak boleh** lewat cloud build.
- Jika ada binary toolchain tidak tersedia untuk ARM64, **jangan klaim READY** — catat sebagai blocker sesuai PRD.
- Gradle wrapper (`gradlew`) **tidak** disertakan di repo ini secara otomatis karena butuh unduhan JAR wrapper yang sebaiknya diverifikasi; di device, Anda bisa generate dengan `gradle wrapper` jika `gradle` tersedia, atau pakai instalasi Gradle yang kompatibel.

## Perintah contoh (jalankan di root proyek ini di device)

```bash
# sesuaikan jika pakai gradle system
gradle --version
gradle assembleDebug
# release biasanya perlu signingConfig; untuk bukti awal bisa uji debug dulu,
# lalu konfig release + keystore sesuai kebijakan (jangan commit keystore/password)
gradle assembleRelease
```

Artefak biasanya di `app/build/outputs/apk/...`.
