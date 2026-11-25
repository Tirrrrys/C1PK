# Phase 1 — Technical Feasibility Prototype (CERJA-PK)

Sesuai PRD §25 Phase 1. Tujuan: validasi eksekusi toolchain **Android ARM64/on-device** secara **local build** (tanpa cloud build), sebelum membangun aplikasi CERJA-PK penuh.

## Tujuan yang harus dibuktikan (evidence-based)

1. **ARM64 execution environment** berjalan di device target (Android, preferred no-root untuk core flow).
2. **JDK** kompatibel untuk proyek Android target (versi baseline ditetapkan setelah prototyping; lihat OQ-012).
3. **Gradle** berjalan lokal dan dapat menghasilkan build Android.
4. **Android build tools** (SDK components/build-tools/AAPT2/D8/R8/APK tooling) tersedia dalam bentuk **ARM64-compatible & dapat diverifikasi** (bukan asumsi).
5. **Kotlin/Java simple app** menghasilkan **APK debug** secara lokal, lalu **release build + signing** tervalidasi, **APK terinstal**.
6. Tidak ada langkah yang bergantung pada **cloud build**.

## Bukan scope Phase 1

- UI CERJA-PK lengkap, agent runtime penuh, provider manager lengkap (itu Phase 2–4).
- Flutter/React Native/Web-to-APK sebagai “supported” (masih perlu validasi terpisah per PRD).

## Deliverables Phase 1 (yang harus ada sebagai bukti)

- Catatan device: model, Android version, ABI, storage free, RAM (kasar) — simpan di `DEVICE_NOTES.md` (dibuat saat uji).
- Log setup toolchain + versi terpasang (JDK, Gradle, SDK/build-tools).
- Hasil build:
  - `assembleDebug` sukses, file APK ada dan dicatat path + ukuran + SHA-256.
  - `assembleRelease` (atau jalur release yang dipilih) sukses, APK/AAB sesuai rencana, **signature verification** tercatat.
- Bukti install: screenshot/notes instalasi berhasil di device (atau `adb install` log jika diuji via adb dari host — tapi build tetap di device per PRD).
- Jika ada kegagalan: log lengkap + klasifikasi error (sesuai PRD §15) + langkah mitigasi yang dicoba.

## Lingkungan uji yang dimaksud PRD

- Target utama: **Android ARM64**, **no-root preferred** untuk alur inti.
- Build **harus** dilakukan di perangkat Android (on-device). Dokumen ini juga mencatat apa yang bisa/divalidasi di host Linux non-Android (hanya persiapan repo), dan harus dipisahkan dari bukti “local build on device”.

## Risiko / hal yang sering menghambat (ingat PRD)

- Tidak semua binary Android SDK CLI resmi tersedia untuk Linux **ARM64** — harus pakai artefak ARM64 yang legal & terverifikasi; jika tidak ada, fitur terkait ditandai **unsupported/degraded**, bukan diklaim READY.
- Resource (RAM/storage, battery, background limits) memengaruhi build panjang.

## Referensi cepat ke PRD

- §3 Confirmed decisions (DEC-001..011)
- §8 FR-001/002/008 dan §17 Compatibility matrix
- §25 Release Strategy Phase 1
- §27 Open Questions (OQ-001..012) — **jangan diisi asumsi** tanpa bukti; catat sebagai “unresolved” jika masih terbuka.
