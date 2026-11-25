# C1PK (CERJA-PK)

Repo ini menyimpan **PRD** dan artefak awal untuk **CERJA-PK**: aplikasi Android (target) untuk lingkungan development lokal + agen AI otonom yang menghasilkan APK/AAB melalui **build 100% di perangkat** (tanpa cloud build).

## Isi

- `CERJA-PK_PRD_v1.0.md` — Product Requirements Document v1.0 (draft technical validation)

## Catatan penting (dari PRD)

- **Build lokal wajib**; inferensi AI boleh via API eksternal (OpenAI/Anthropic-compatible, custom Base URL + API Key).
- MVP target awal: **Kotlin/Java Android native** (Flutter/React Native/Web-to-APK disusul setelah validasi ARM64/Toolchain).
- Banyak keputusan arsitektur/toolchain masih **open (OQ-001..012)** — implementasi harus mengacu ke PRD dan tidak mengganti keputusan yang sudah dikunci.

## Status

Dokumen PRD: *Ready for technical feasibility phase; not yet final architecture freeze.* (lihat bagian akhir PRD).

## License

Belum ditentukan.
