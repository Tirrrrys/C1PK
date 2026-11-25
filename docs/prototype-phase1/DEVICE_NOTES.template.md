# Device notes (Phase 1) — isi saat uji di device Android ARM64

> Simpan salinan terisi sebagai `DEVICE_NOTES.md` (jangan commit secret). PRD: target ARM64, no-root preferred.

## Identitas device

- Tanggal uji:
- Device (model / codename):
- Manufacturer:
- Android version (API level):
- ABI(s) dilaporkan OS:
- CPU (ringkas):
- RAM fisik (perkiraan):
- Storage free sebelum setup (GB):
- Storage free setelah setup (GB):
- Koneksi saat unduh toolchain: (Wi‑Fi / seluler / offline setelah cache)
- Root? (ya/tidak) — catatan: core flow **tidak boleh** butuh root

## Lingkungan CERJA-PK / toolchain (catat versi aktual)

- Metode instalasi environment: (mis. Termux/custom userspace — sesuai keputusan nanti; **jangan asumsi** sebelum ada bukti)
- JDK: 
- Gradle: 
- Android SDK path:
- `platforms;android-*`:
- `build-tools;*`:
- AAPT2: (path + versi/catatan)
- D8/R8/APK signer tools: (daftar + versi/catatan)
- Kotlin compiler (jika terpisah): 
- Catatan kompatibilitas ARM64 untuk setiap binary penting: (ada/tidak, sumber/verifikasi)

## Keputusan terbuka (OQ) yang relevan muncul saat uji

Catat temuan singkat; **jangan isi nilai final** di PRD tanpa proses keputusan, tapi simpan bukti di sini.

- OQ-001 (min Android): 
- OQ-002 (min RAM): 
- OQ-003 (min free storage setup): 
- OQ-004 (basis userspace): 
- OQ-005 (sumber binary ARM64): 
- OQ-006 (lokasi instalasi): 
- OQ-007 (Git di MVP): 
- OQ-008 (signing UX/backup): 
- OQ-009 (editor MVP): 
- OQ-010 (benchmark definisi supported build sizes): 
- OQ-011 (background build): 
- OQ-012 (baseline versi Kotlin/Java/Gradle/AGP/SDK): 

## Catatan tambahan

- 
