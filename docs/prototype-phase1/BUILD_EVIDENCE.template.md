# Build evidence (Phase 1)

Isi setelah menjalankan build **di device Android ARM64** (local). Setiap entri harus punya bukti (path file, ukuran, hash, log ringkas).

## Proyek uji (minimal Kotlin/Java Android native)

- Nama proyek:
- Lokasi workspace (di device):
- Tipe: (Kotlin / Java / campuran)
- Commit/tag sumber:

## Debug build

- Perintah:
- Waktu mulai / selesai:
- Exit code:
- Hasil: SUCCESS / FAILED
- APK: `...`
- Ukuran (bytes):
- SHA-256:
- Catatan log penting:

## Release build + signing

- Perintah:
- Variant/signing: (debug keystore / release keystore — **jangan sertakan password/key** di dokumen)
- Exit code:
- Hasil: SUCCESS / FAILED
- Artefak: `...` (APK/AAB sesuai uji)
- Ukuran (bytes):
- SHA-256:
- Verifikasi signature (perintah + output ringkas):

## Install test

- Cara install: (Package Installer / `adb install` dari host **hanya untuk deploy**; build tetap di device)
- Hasil: berhasil / gagal (sertakan pesan error jika gagal)

## Kegagalan (jika ada)

- Klasifikasi (PRD §15): (mis. unsupported ARM64 binary, missing SDK, …)
- Log relevan (lampirkan file atau potongan): 
- Tindakan mitigasi: 
- Status akhir: unresolved / di-skip dengan alasan / resolved (dengan bukti)
