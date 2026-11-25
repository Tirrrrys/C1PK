# CERJA-PK

## Product Requirements Document (PRD)

**Document version:** 1.0 --- Initial Engineering Draft\
**Status:** Draft for technical validation\
**Product:** CERJA-PK\
**Category:** Autonomous Android Development & Local APK Builder\
**Target platform:** Android\
**Build architecture:** 100% local/on-device\
**Primary language:** Bahasa Indonesia for product-facing documentation;
implementation terminology may use English.

------------------------------------------------------------------------

# 1. Executive Summary

CERJA-PK adalah aplikasi Android yang menyediakan development
environment lokal dan autonomous AI agent untuk membantu pengguna
membuat, mengembangkan, menguji, dan membangun aplikasi Android hingga
menghasilkan APK/AAB.

Pengguna memberikan instruksi melalui antarmuka AI. Agent menganalisis
kebutuhan, memeriksa proyek, membuat atau mengubah source code,
mengelola dependency, menjalankan toolchain lokal, mendiagnosis error,
memperbaiki implementasi, dan mengulangi build sampai berhasil atau
mencapai kondisi berhenti yang dijelaskan.

Seluruh proses kompilasi dan packaging aplikasi harus berlangsung pada
perangkat Android pengguna. API eksternal hanya digunakan untuk
inferensi AI dan pengambilan resource/dependency yang diperlukan.
Layanan cloud build bukan bagian dari arsitektur produk.

CERJA-PK bukan sekadar chat UI atau terminal mobile. Produk ini
merupakan orkestrator antara AI, workspace, filesystem, terminal,
package manager, toolchain, build engine, pengujian, dan pengelolaan
artefak.

## 1.1 Product Principles

1.  **Local build by design:** source code dan proses build tetap berada
    di perangkat.
2.  **Autonomous execution:** AI dapat menyelesaikan rangkaian tugas
    development tanpa persetujuan per langkah.
3.  **Safe autonomy:** otonomi tidak berarti akses tanpa batas; agent
    dibatasi oleh workspace, sandbox, dan kebijakan keamanan.
4.  **Recoverability:** pekerjaan, log, dan checkpoint harus dapat
    dipulihkan setelah gangguan.
5.  **Honest compatibility:** framework hanya dinyatakan didukung
    setelah toolchain dan build target teruji.
6.  **User ownership:** pengguna dapat mengakses, mengekspor, dan
    mengelola source code serta artefak.
7.  **Resource awareness:** penggunaan RAM, CPU, storage, baterai, dan
    jaringan harus diperhitungkan.

# 2. Product Overview

## 2.1 Product Identity

  -----------------------------------------------------------------------
  Attribute                           Specification
  ----------------------------------- -----------------------------------
  Application name                    CERJA-PK

  Product type                        Android application

  Main purpose                        Autonomous local APK/AAB builder

  Main interaction                    AI chat + project workspace

  Build execution                     100% local

  AI operation                        External API-based agent

  API protocols                       OpenAI-compatible and
                                      Anthropic-compatible

  Provider configuration              Custom Base URL and API Key

  Model discovery                     Automatic model-list fetching where
                                      provider supports it

  Target architecture                 Android ARM64 as initial target

  Root access                         No-root preferred; root must not be
                                      required for core MVP

  Cloud build                         Out of scope / prohibited as a
                                      build dependency

  Network                             Required for initial downloads,
                                      dependencies, and AI API; local
                                      build itself must not depend on
                                      cloud compilation
  -----------------------------------------------------------------------

## 2.2 Product Vision

Memungkinkan pengguna membangun aplikasi Android melalui instruksi
natural language, sementara CERJA-PK mengelola pekerjaan teknis dan
menjalankan build secara lokal.

## 2.3 Problem Statement

Pengembangan Android dari perangkat mobile terhambat oleh setup
toolchain yang rumit, keterbatasan terminal mobile, kompatibilitas
binary ARM64, pengelolaan dependency, debugging, dan proses build yang
membutuhkan pengetahuan teknis.

CERJA-PK mengurangi hambatan tersebut melalui setup environment
otomatis, orkestrasi AI, dan local build pipeline.

# 3. Confirmed Decisions

Keputusan berikut telah dikonfirmasi pengguna dan diperlakukan sebagai
baseline requirement.

  -----------------------------------------------------------------------
  ID                      Decision                Confirmed value
  ----------------------- ----------------------- -----------------------
  DEC-001                 Build architecture      100% Local Build

  DEC-002                 AI operation            API-Based Autonomous
                                                  Agent

  DEC-003                 API compatibility       OpenAI-Compatible +
                                                  Anthropic-Compatible

  DEC-004                 Provider configuration  Custom Base URL + API
                                                  Key

  DEC-005                 Model discovery         Automatic model
                                                  fetching

  DEC-006                 Agent autonomy          Full Autonomous

  DEC-007                 Visual identity         Charcoal, dark grey,
                                                  ivory white

  DEC-008                 Layout                  Neat, structured,
                                                  mobile-first

  DEC-009                 First-launch experience Automatic environment
                                                  setup

  DEC-010                 Product name            CERJA-PK

  DEC-011                 Initial                 Kotlin, Java, Flutter,
                          language/framework      React Native,
                          scope                   HTML/CSS/JavaScript;
                                                  native Kotlin/Java
                                                  first
  -----------------------------------------------------------------------

# 4. Product Goals

-   G-001: Menyiapkan environment development secara otomatis pada first
    launch.
-   G-002: Menyediakan autonomous agent yang dapat menjalankan siklus
    development.
-   G-003: Menghasilkan APK/AAB melalui proses build lokal.
-   G-004: Mendukung custom AI provider dan model.
-   G-005: Menyediakan project workspace yang dapat diakses pengguna.
-   G-006: Mengelola dependency dan toolchain secara konsisten.
-   G-007: Menyediakan diagnosis, recovery, dan build retry.
-   G-008: Menjaga performa yang realistis pada perangkat Android.
-   G-009: Memastikan source code dan artefak tidak bergantung pada
    cloud build.

# 5. Non-Goals

Fitur berikut bukan tujuan MVP kecuali diputuskan kemudian:

-   Cloud compilation atau remote build server.
-   Emulator Android penuh di dalam aplikasi.
-   AI model lokal sebagai engine utama.
-   Hosting aplikasi atau backend production.
-   Distribusi otomatis ke Google Play.
-   Marketplace plugin publik.
-   Kolaborasi real-time multi-user.
-   Menjamin semua repository atau framework pihak ketiga dapat dibuild
    di ARM64.

# 6. Target Users and Personas

## 6.1 Primary Persona --- AI-First App Creator

Pengguna yang ingin membuat aplikasi melalui instruksi AI, tetapi tidak
ingin mengatur compiler dan build pipeline secara manual.

Needs: - Setup otomatis. - Instruksi sederhana. - Agent yang benar-benar
dapat mengeksekusi pekerjaan. - Hasil APK yang mudah ditemukan dan
dipasang. - Penjelasan error yang dapat dipahami.

## 6.2 Secondary Persona --- Mobile Developer

Pengembang yang ingin mengedit source code, menjalankan terminal,
mengelola dependency, dan membangun aplikasi dari perangkat Android.

Needs: - Workspace yang dapat diakses. - Terminal dan log. - Kontrol
build. - Version control. - Pengaturan toolchain.

# 7. Product Scope and Prioritization

Priority definitions: - **P0:** wajib untuk operasi fundamental MVP. -
**P1:** penting untuk pengalaman produk dan dapat dirilis setelah core
pipeline stabil. - **P2:** peningkatan lanjutan. - **P3:**
opsional/future.

  ---------------------------------------------------------------------------
  Module ID        Module                           Priority Release
  ---------------- ------------------- --------------------- ----------------
  MOD-001          First-launch setup                     P0 MVP

  MOD-002          Environment manager                    P0 MVP

  MOD-003          AI provider manager                    P0 MVP

  MOD-004          Autonomous agent                       P0 MVP
                   runtime                                   

  MOD-005          Project/workspace                      P0 MVP
                   manager                                   

  MOD-006          Local terminal and                     P0 MVP
                   command runner                            

  MOD-007          Dependency manager                     P0 MVP

  MOD-008          Local Android build                    P0 MVP
                   engine                                    

  MOD-009          Build monitor and                      P0 MVP
                   logs                                      

  MOD-010          Artifact manager                       P0 MVP

  MOD-011          Error diagnosis and                    P0 MVP
                   recovery                                  

  MOD-012          Kotlin/Java project                    P0 MVP
                   templates                                 

  MOD-013          Web-to-APK workflow                    P1 Post-MVP

  MOD-014          Flutter toolchain                      P1 Post-MVP after
                                                             ARM64 validation

  MOD-015          React Native                           P1 Post-MVP after
                   toolchain                                 ARM64 validation

  MOD-016          Git integration                        P1 Post-MVP

  MOD-017          Advanced build                         P2 Future
                   profiles                                  

  MOD-018          Plugin/extension                       P3 Future
                   system                                    
  ---------------------------------------------------------------------------

# 8. Functional Requirements

## FR-001 --- First-Launch Automatic Environment Setup

**Priority:** P0\
**Purpose:** Menginisialisasi dan memasang toolchain yang diperlukan
tanpa konfigurasi manual oleh pengguna.

### Main workflow

1.  Pengguna memasang dan membuka CERJA-PK.
2.  Aplikasi menampilkan onboarding singkat dan ringkasan kebutuhan
    storage/network.
3.  Aplikasi memeriksa kompatibilitas perangkat.
4.  Aplikasi mendeteksi ABI/CPU architecture, versi Android, ruang
    penyimpanan, dan status jaringan.
5.  Aplikasi membuat direktori internal environment.
6.  Aplikasi menginisialisasi package manager dan repository.
7.  Aplikasi memperbarui package index.
8.  Aplikasi melakukan upgrade package yang diperlukan dengan versi
    terkontrol.
9.  Aplikasi mengunduh dan memasang runtime serta build tools yang
    kompatibel.
10. Aplikasi mengatur environment variables dan path.
11. Aplikasi memverifikasi checksum/signature bila tersedia.
12. Aplikasi menjalankan health check.
13. Aplikasi menjalankan smoke test build untuk template native
    sederhana.
14. Environment ditandai READY hanya jika seluruh komponen P0
    tervalidasi.

### Initial toolchain candidates

Core: - ARM64-compatible userspace/environment. - Shell and command
utilities. - Package manager. - OpenJDK. - Gradle. - Android SDK
components. - Android build-tools equivalents compatible with ARM64. -
AAPT2 compatible with target ABI. - D8/R8 and APK packaging/signing
utilities. - Git. - Curl/Wget. - Zip/Unzip. - Node.js/npm for applicable
workflows.

Language/framework toolchains: - Kotlin. - Java. - Dart/Flutter. -
JavaScript/TypeScript. - HTML/CSS.

**Important feasibility constraint:** jangan mengasumsikan semua Android
SDK command-line binaries resmi menyediakan Linux ARM64 build. Toolchain
harus menggunakan binary ARM64 yang legal, dapat diverifikasi, dan
kompatibel. Jika komponen tidak tersedia atau tidak kompatibel, setup
harus menandai fitur terkait sebagai unsupported/degraded, bukan
berpura-pura READY.

### Requirements

-   SETUP-001: Setup dimulai otomatis pada first launch setelah
    onboarding yang diperlukan.
-   SETUP-002: Pengguna dapat melihat tahapan, progress, ukuran
    download, dan status setiap komponen.
-   SETUP-003: Setup dapat dilanjutkan setelah aplikasi dihentikan atau
    perangkat restart.
-   SETUP-004: Komponen yang sudah terpasang harus diverifikasi sebelum
    dilewati.
-   SETUP-005: Package dan toolchain harus menggunakan versi/pin yang
    kompatibel.
-   SETUP-006: Unduhan harus diverifikasi menggunakan checksum atau
    signature jika tersedia.
-   SETUP-007: Kegagalan download mendukung retry dan resume bila server
    mendukung.
-   SETUP-008: Setup tidak boleh menghapus file pribadi pengguna.
-   SETUP-009: Aplikasi harus memeriksa storage sebelum download dan
    instalasi.
-   SETUP-010: Aplikasi harus membedakan mandatory components dan
    optional toolchains.
-   SETUP-011: Setup harus mencatat komponen, versi, sumber, checksum,
    dan status.
-   SETUP-012: Setup harus menyediakan diagnosis yang dapat dibaca
    pengguna.
-   SETUP-013: Proses setup tidak boleh mengklaim sukses sebelum smoke
    test wajib lulus.

### Failure handling

  -----------------------------------------------------------------------
  Trigger                 Expected behavior       Recovery
  ----------------------- ----------------------- -----------------------
  No internet             Pause download and      Retry when online
                          explain                 

  Insufficient storage    Stop before unsafe      Free storage and resume
                          partial installation    

  Package repository      Show repository error   Retry or use configured
  unavailable                                     mirror

  Checksum mismatch       Reject package          Re-download from
                                                  trusted source

  App process killed      Persist setup state     Resume on next launch

  Unsupported ABI         Mark incompatible       Do not attempt unsafe
                                                  installation

  Smoke test failure      Mark environment        Show diagnostics and
                          DEGRADED/FAILED         repair option
  -----------------------------------------------------------------------

### Acceptance criteria

**Given** a compatible device with sufficient storage and internet,\
**When** the user opens CERJA-PK for the first time,\
**Then** the application initializes the environment, installs mandatory
toolchains, verifies them, and reports READY only after mandatory smoke
tests pass.

**Given** setup is interrupted,\
**When** the application is reopened,\
**Then** completed verified components are retained and unfinished steps
can resume.

## FR-002 --- Environment Manager

**Priority:** P0

Capabilities: - View installed tools and versions. - Install optional
toolchains. - Update compatible packages. - Repair damaged components. -
Run environment health checks. - Display storage usage. - View package
installation history. - Manage environment configuration.

Environment states: `NOT_INSTALLED`, `INITIALIZING`, `INSTALLING`,
`VERIFYING`, `READY`, `DEGRADED`, `FAILED`, `REPAIRING`, `UPDATING`.

Requirements: - ENV-001: Setiap toolchain memiliki version metadata. -
ENV-002: Update harus mematuhi compatibility matrix. - ENV-003: Repair
tidak boleh menghapus project workspace. - ENV-004: Package changes
harus memiliki log. - ENV-005: Health check mendeteksi komponen
hilang/rusak. - ENV-006: Pengguna dapat melihat versi dan status
toolchain. - ENV-007: Update tidak boleh mengganti versi aktif secara
diam-diam jika berpotensi merusak proyek. - ENV-008: Environment
backup/restore harus mempertimbangkan ukuran dan konsistensi package
database.

## FR-003 --- AI Provider Manager

**Priority:** P0

### Supported protocols

-   OpenAI-compatible chat/completions interface and tool calling where
    supported.
-   Anthropic-compatible Messages interface and tool use where
    supported.

Protocol compatibility does not guarantee identical feature support.
Provider/model capability detection is required.

### Provider configuration fields

-   Provider display name.
-   API protocol.
-   Base URL.
-   API Key.
-   Model identifier.
-   Request timeout.
-   Optional custom headers only where safe and explicitly configured.
-   Connection/validation status.
-   Model capability metadata.

### Model discovery

1.  User adds or edits a provider.
2.  CERJA-PK validates Base URL format.
3.  CERJA-PK securely stores credentials.
4.  CERJA-PK requests model list using the provider's supported
    discovery endpoint.
5.  Returned models are normalized for display.
6.  User selects a model.
7.  CERJA-PK performs a safe connectivity/capability test.
8.  The selected model becomes available to the agent.

Requirements: - API-001: Support custom Base URL. - API-002: Support API
Key. - API-003: Support OpenAI-compatible and Anthropic-compatible
protocols. - API-004: Support multiple saved providers. - API-005: Fetch
model list where endpoint is available. - API-006: Allow manual model ID
entry if discovery is unsupported. - API-007: Distinguish unsupported
model listing from invalid credentials. - API-008: Preserve project data
when provider/model changes. - API-009: Never write API keys into
generated project source. - API-010: Implement request timeout,
cancellation, and useful error messages. - API-011: Detect tool calling,
streaming, context limits, and other capabilities where possible. -
API-012: Do not claim a model supports tools solely because it appears
in the model list. - API-013: Redact credentials from logs and crash
reports.

## FR-004 --- Autonomous AI Agent

**Priority:** P0

### Agent responsibilities

-   Interpret user instructions.
-   Inspect project structure and relevant files.
-   Build an implementation plan.
-   Create, edit, move, and delete project files.
-   Read local documentation and build logs.
-   Execute approved-in-policy terminal commands.
-   Install project dependencies.
-   Run builds and tests.
-   Diagnose errors.
-   Apply fixes.
-   Rebuild and verify artifacts.
-   Summarize work and unresolved issues.

### Autonomous workflow

1.  Receive instruction.
2.  Identify target project and task scope.
3.  Inspect relevant files and project metadata.
4.  Build a plan with tasks and dependencies.
5.  Execute tasks in bounded steps.
6.  Validate file changes.
7.  Resolve dependencies.
8.  Run appropriate checks.
9.  Execute local build.
10. Parse logs and classify failures.
11. Apply fixes within retry/time/resource budgets.
12. Re-run tests/build.
13. Verify output artifact.
14. Present result, changed files, tests, build status, and limitations.

### Full autonomy policy

AI does not ask for approval for each ordinary coding action. It may
autonomously create/edit project files, run project-local commands,
install project dependencies, debug, test, and rebuild.

Full autonomy is bounded by platform security and application policy: -
Agent is scoped to the active project workspace by default. - Access to
unrelated private files, Android account data, and secrets is prohibited
unless a narrowly scoped feature explicitly authorizes it. - Destructive
operations should use reversible workspace operations/checkpoints where
possible. - Environment-global changes must use a controlled
package/environment manager. - Commands must be logged with arguments,
working directory, exit status, and output redaction. - Network access
must be controlled and visible through policy. - Signing keys and
credentials must be handled by dedicated secure services, not exposed in
prompts or terminal output. - Agent must stop and report when a task
requires unavailable permissions, unsupported architecture, or a policy
boundary.

### Agent execution controls

-   Start, pause, resume, cancel.
-   Maximum iteration count.
-   Execution timeout.
-   Token/request budget where configurable.
-   Build retry budget.
-   Task checkpoint.
-   Stop reason and final status.

Requirements: - AGENT-001: Agent must maintain task state. - AGENT-002:
Agent must inspect before modifying an existing project. - AGENT-003:
Agent must use structured tool calls rather than fabricate tool
results. - AGENT-004: Every tool call must be validated by the execution
layer. - AGENT-005: Agent must receive actual command exit codes and
output. - AGENT-006: Agent must not claim a build succeeded unless the
artifact exists and passes verification. - AGENT-007: Agent must
preserve user data and avoid out-of-workspace access. - AGENT-008: Agent
must checkpoint before high-impact file operations. - AGENT-009: Agent
must provide a concise task summary and changed-file list. - AGENT-010:
Agent must detect model/tool-call capability limitations. - AGENT-011:
Agent must recover from API interruption without duplicating unsafe
operations. - AGENT-012: Agent must support cancellation and stop
scheduling new operations after cancellation. - AGENT-013: Agent must
not expose API keys or signing credentials. - AGENT-014: Agent must have
bounded loops and retry limits to prevent runaway execution.

## FR-005 --- Project Workspace

**Priority:** P0

Capabilities: - Create project. - Import existing project. - Open
project. - Browse files. - View/edit text files. - Search project
files. - Rename/move files. - Delete files with recoverability where
possible. - View project metadata. - Export project. - Associate project
with AI conversation/task history.

Requirements: - PROJ-001: Each project has a stable internal ID. -
PROJ-002: Project metadata records name, path, type, creation date, and
build configuration. - PROJ-003: Projects are isolated from one
another. - PROJ-004: Agent receives access only to the selected project
unless explicitly changed by the user. - PROJ-005: File operations must
handle permission errors and storage limitations. - PROJ-006: Project
import must validate archive paths and reject path traversal. -
PROJ-007: The application must not silently overwrite an existing
project. - PROJ-008: User can export source project independently from
APK artifacts.

## FR-006 --- Local Terminal and Command Runner

**Priority:** P0

Purpose: provide controlled local command execution for the user and AI
agent.

Capabilities: - Interactive terminal. - Non-interactive command
execution. - Working-directory selection. - Process status. -
stdout/stderr streaming. - Exit code reporting. - Process
cancellation. - Command history. - Long-running process management.

Requirements: - TERM-001: Commands execute in the local environment. -
TERM-002: Agent commands are scoped to allowed directories and
policies. - TERM-003: Output streams are captured and displayed. -
TERM-004: Long-running commands expose progress/output. - TERM-005: User
can stop a running process. - TERM-006: Process termination must clean
up child processes where feasible. - TERM-007: Commands must not be
reported successful without exit code/result. - TERM-008: Sensitive
values must be redacted from logs. - TERM-009: Terminal sessions must
recover gracefully after UI recreation. - TERM-010: Interactive shell
and agent command execution must not corrupt each other's session state.

## FR-007 --- Dependency Manager

**Priority:** P0

Capabilities: - Detect project dependencies. - Resolve packages. -
Install/update project dependencies. - Display dependency source and
version. - Report conflicts. - Cache downloads. - Support offline reuse
of cached dependencies where possible.

Requirements: - DEP-001: Dependency operations must be project-aware. -
DEP-002: Resolved versions should be lockable/pinned. - DEP-003:
Dependency source and checksum metadata should be retained where
available. - DEP-004: Failed downloads must be retryable. - DEP-005:
Dependency conflicts must be reported with actionable diagnostics. -
DEP-006: AI must not silently replace major dependency versions without
explaining the change in task summary. - DEP-007: Dependency cache must
be clearable without deleting project source.

## FR-008 --- Local Android Build Engine

**Priority:** P0

Purpose: compile and package Android projects on the device.

### Initial supported build target

-   Native Android Kotlin.
-   Native Android Java.

### Additional planned targets

-   Web application wrapped into Android APK.
-   Flutter.
-   React Native.

These targets are not considered production-supported until ARM64
release-build validation passes.

### Build workflow

1.  Identify project type.
2.  Validate project structure.
3.  Detect Gradle wrapper/build configuration.
4.  Verify JDK, Gradle, Android SDK, build tools, and required native
    binaries.
5.  Resolve dependencies.
6.  Validate SDK/AGP/JDK compatibility.
7.  Execute local build.
8.  Stream logs and progress.
9.  Capture exit code.
10. Locate expected APK/AAB output.
11. Verify artifact existence, size, package metadata, and signature
    state.
12. Register artifact in Artifact Manager.

Requirements: - BUILD-001: All compilation and packaging occur
locally. - BUILD-002: No cloud build fallback. - BUILD-003: Build must
use compatible pinned toolchain versions. - BUILD-004: Build process
must expose logs and state. - BUILD-005: User can cancel a build. -
BUILD-006: Build must detect insufficient storage. - BUILD-007: Build
must distinguish compilation failure from packaging/signing failure. -
BUILD-008: Build must preserve source files after failure. - BUILD-009:
Build output must be verified before success is reported. - BUILD-010:
Build profiles must distinguish debug and release. - BUILD-011: Release
signing must use secure key handling. - BUILD-012: Build process must
support interruption recovery where technically feasible. - BUILD-013:
Unsupported toolchain combinations must fail early with diagnostics. -
BUILD-014: Build must not silently download or execute unverified
binaries.

## FR-009 --- Build Monitor

**Priority:** P0

Display: - Current build stage. - Project name. - Build variant. -
Elapsed time. - Current task/log output. - Warning/error counts where
parsable. - Cancellation control. - Final exit status.

Build states: `QUEUED`, `PREPARING`, `RESOLVING_DEPENDENCIES`,
`COMPILING`, `PACKAGING`, `SIGNING`, `VERIFYING`, `SUCCESS`, `FAILED`,
`CANCELLED`.

Requirements: - MON-001: State changes must be observable. - MON-002:
Logs must be searchable or filterable. - MON-003: Errors must be
distinguishable from warnings. - MON-004: Build history must be retained
per project. - MON-005: UI must remain responsive while build executes.

## FR-010 --- Error Diagnosis and Recovery

**Priority:** P0

The system should classify common errors: - Missing toolchain. -
Incompatible JDK/Gradle/AGP. - Missing SDK component. - Unsupported
ARM64 executable. - Dependency resolution failure. - Network timeout. -
Compilation error. - Resource exhaustion. - Storage exhaustion. -
Signing failure. - Invalid project configuration.

Requirements: - ERR-001: Preserve complete build logs subject to storage
policy. - ERR-002: Parse actionable error locations when available. -
ERR-003: Provide human-readable diagnosis. - ERR-004: Agent may attempt
bounded automatic fixes. - ERR-005: Agent must not claim a fix succeeded
until revalidation passes. - ERR-006: Failed automatic repair must
preserve diagnostics and offer next steps. - ERR-007: Repeated identical
failures must trigger a stop condition rather than infinite retry. -
ERR-008: User can inspect the original error and agent changes.

## FR-011 --- Artifact Manager

**Priority:** P0

Capabilities: - List generated APK/AAB files. - Show project, variant,
timestamp, size, package ID, version, and signing status when
available. - Open file location. - Share/export artifact. - Install APK
using Android package installer flow. - Delete artifact with
confirmation. - Retain build-to-artifact relationship.

Requirements: - ART-001: Artifact must be registered only after
verification. - ART-002: APK installation must use Android-supported
installation flow. - ART-003: App must not bypass Android package
installation security. - ART-004: User must be able to locate exported
APK files. - ART-005: Deleting an artifact must not delete the source
project. - ART-006: Artifact metadata must be retained in build history.

## FR-012 --- Language and Framework Support

**Priority:** P0/P1 by target.

  Language/framework    Planned status   Notes
  --------------------- ---------------- --------------------------------------------------
  Kotlin                P0               Primary native Android target
  Java                  P0               Native Android target
  HTML/CSS/JavaScript   P1               Web-to-APK workflow
  TypeScript            P1               Web/React ecosystem
  Flutter/Dart          P1               Requires ARM64 toolchain validation
  React Native          P1               Requires native dependency and Gradle validation

Requirements: - LANG-001: Project type must be detected or explicitly
selected. - LANG-002: Templates must pin compatible tool versions. -
LANG-003: Unsupported project configurations must be reported before
lengthy builds. - LANG-004: Framework support status must be visible in
the application. - LANG-005: A language being editable does not
automatically mean its APK build target is supported.

# 9. Non-Functional Requirements

## NFR-001 --- Performance

-   UI must remain responsive during build and setup.
-   Long-running work must execute outside the main UI thread.
-   Logs must stream incrementally rather than blocking until process
    completion.
-   Memory-heavy tasks should expose failure diagnostics.
-   Build concurrency should default to one active build to reduce
    resource pressure.
-   Background execution must respect Android lifecycle and battery
    restrictions.

Exact performance thresholds must be established through device
benchmarking.

## NFR-002 --- Reliability

-   Persist setup and agent task state.
-   Preserve source files after failed build.
-   Avoid duplicate execution of non-idempotent operations after
    recovery.
-   Use atomic metadata updates where possible.
-   Keep build logs and failure details.

## NFR-003 --- Compatibility

Initial target: - Android ARM64 devices. - No-root operation
preferred. - Android version baseline must be finalized after prototype
validation.

The application must detect unsupported ABI, OS version, storage, and
toolchain combinations.

## NFR-004 --- Resource Management

-   Check free storage before large downloads/builds.
-   Display environment and cache storage.
-   Allow safe cache cleanup.
-   Avoid unnecessary background polling.
-   Allow cancellation of downloads/builds.
-   Provide configurable retention for old logs/artifacts.

## NFR-005 --- Maintainability

-   Modular architecture.
-   Versioned environment manifests.
-   Versioned project templates.
-   Centralized process execution.
-   Centralized logging and error classification.
-   Compatibility test suite for each toolchain update.

# 10. UI/UX Requirements

## 10.1 Visual Identity

Primary palette: - Charcoal: `#292B2D` - Dark Grey: `#45484A` - Neutral
Grey: `#777875` - Ivory White: `#F0EDE4`

Design direction: - Minimalist. - Clean. - Compact. - Structured. -
Mobile-first. - Consistent spacing and typography. - Restrained use of
accent colors. - Avoid visual clutter and excessive gradients.

## 10.2 Main Navigation

Proposed navigation: 1. Home. 2. Projects. 3. AI Workspace. 4. Builds.
5. Settings.

Navigation may use bottom navigation or a compact adaptive navigation
structure depending on screen size.

## 10.3 Key Screens

### A. First Launch / Setup

-   Welcome.
-   Device compatibility.
-   Storage requirement.
-   Setup stages.
-   Download/install progress.
-   Verification results.
-   Retry/repair state.

### B. Home Dashboard

-   Environment readiness.
-   Recent projects.
-   New project action.
-   Recent build.
-   AI provider status.
-   Storage summary.

### C. AI Workspace

-   Conversation.
-   Project context.
-   Agent activity.
-   Task plan.
-   Tool execution timeline.
-   Stop/pause controls.
-   Changed files.
-   Build result.

### D. Project Workspace

-   File explorer.
-   Code editor.
-   Search.
-   Project information.
-   Build action.
-   Terminal access.

### E. Build Monitor

-   Build stage.
-   Streaming logs.
-   Warnings/errors.
-   Build duration.
-   Cancel action.
-   Result.

### F. Artifact Manager

-   APK/AAB list.
-   Artifact details.
-   Install/share/export.
-   Build history.

### G. Settings

-   AI providers.
-   Model selection.
-   Environment.
-   Storage.
-   Permissions.
-   Theme.
-   Logs and diagnostics.

## 10.4 UX States

Every major screen must define: - Loading state. - Empty state. -
Success state. - Error state. - Offline state. - Permission-denied
state. - Recoverable failure state.

# 11. System Architecture Considerations

## 11.1 Logical Architecture

The implementation should be separated into these logical layers:

1.  **Android Presentation Layer**
    -   Navigation.
    -   Screens.
    -   User interaction.
    -   Progress visualization.
2.  **Application Orchestration Layer**
    -   Project lifecycle.
    -   Agent task lifecycle.
    -   Build orchestration.
    -   Environment orchestration.
3.  **Autonomous Agent Runtime**
    -   Planning.
    -   Model communication.
    -   Tool selection.
    -   Context management.
    -   Retry and stop policies.
4.  **Tool Execution Layer**
    -   Filesystem tools.
    -   Terminal tools.
    -   Package manager tools.
    -   Build tools.
    -   Test tools.
5.  **Local Environment Layer**
    -   ARM64 userspace.
    -   JDK.
    -   Gradle.
    -   Android SDK/build tools.
    -   Language runtimes.
6.  **Persistence Layer**
    -   Project metadata.
    -   Provider metadata.
    -   Task state.
    -   Build history.
    -   Artifact metadata.
    -   Logs.
7.  **External API Layer**
    -   OpenAI-compatible provider.
    -   Anthropic-compatible provider.
    -   Package/dependency repositories.

## 11.2 Architecture Constraints

-   Local compilation is mandatory.
-   No cloud build fallback.
-   AI inference may use external API.
-   Environment must work without root for core use.
-   ARM64 compatibility must be verified per binary.
-   Agent execution must be isolated and policy-controlled.
-   UI must not directly execute arbitrary shell commands without the
    execution service.

## 11.3 Suggested Execution Model

Use a managed local execution service with: - Command allow/deny
policy. - Working-directory restriction. - Process lifecycle tracking. -
Output capture. - Cancellation. - Timeout. - Resource monitoring. -
Audit log.

Do not rely on unrestricted shell access as the only security boundary.

# 12. Data Requirements

Core entities:

## 12.1 Project

-   project_id
-   name
-   path
-   project_type
-   framework
-   created_at
-   updated_at
-   build_configuration

## 12.2 AI Provider

-   provider_id
-   display_name
-   protocol
-   base_url
-   credential_reference
-   selected_model
-   capability_metadata
-   created_at
-   updated_at

API keys must be stored separately in secure storage. They must not be
stored as ordinary project metadata.

## 12.3 Agent Task

-   task_id
-   project_id
-   instruction
-   status
-   plan
-   current_step
-   started_at
-   completed_at
-   stop_reason

## 12.4 Build

-   build_id
-   project_id
-   variant
-   status
-   started_at
-   completed_at
-   exit_code
-   log_reference
-   artifact_id

## 12.5 Artifact

-   artifact_id
-   build_id
-   project_id
-   file_path
-   artifact_type
-   size
-   package_id
-   version
-   signature_status
-   created_at

## 12.6 Environment Component

-   component_id
-   name
-   version
-   source
-   checksum
-   install_path
-   status
-   last_verified_at

# 13. Security and Privacy

## SEC-001 --- API Credential Protection

API keys must be stored using Android Keystore-backed encryption or
equivalent secure storage.

## SEC-002 --- Workspace Isolation

Agent filesystem access must default to the active project workspace.

## SEC-003 --- Secret Redaction

API keys, signing passwords, and sensitive environment variables must be
redacted from logs and AI context.

## SEC-004 --- Package Integrity

Downloaded toolchain packages must be verified using trusted
signatures/checksums where available.

## SEC-005 --- Command Execution

Agent commands must pass through a managed execution layer with policy
checks, working-directory restrictions, and audit logging.

## SEC-006 --- Destructive Operations

Use checkpoints, trash/recovery, or reversible operations where
feasible.

## SEC-007 --- Network Access

Network access must be limited to required providers/repositories and
exposed through clear application policies.

## SEC-008 --- Signing Keys

Release signing keys must be protected and must never be included in
prompts, ordinary logs, or generated source files.

## SEC-009 --- Project Import

Archive extraction must prevent path traversal, unsafe symlinks, and
overwrite outside the selected project.

## SEC-010 --- Honest Security Claims

The product must not claim complete or absolute security.

# 14. Business Rules

-   BR-001: Every build must be executed locally.
-   BR-002: Cloud build is not an allowed fallback.
-   BR-003: An environment is READY only after mandatory verification
    passes.
-   BR-004: AI cannot claim task completion without evidence from actual
    tool execution.
-   BR-005: A build is SUCCESS only after successful exit and artifact
    verification.
-   BR-006: Unsupported toolchains must be explicitly identified.
-   BR-007: Project source must remain available after failed build.
-   BR-008: API provider changes must not delete project data.
-   BR-009: Signing credentials must be handled separately from
    agent-readable project files.
-   BR-010: Full autonomy operates within application security
    boundaries.

# 15. Error Handling and Edge Cases

  -----------------------------------------------------------------------
  Scenario                            Expected system behavior
  ----------------------------------- -----------------------------------
  Empty AI instruction                Ask user to provide a task

  Invalid Base URL                    Validate and explain expected
                                      format

  Invalid API Key                     Show authentication error; do not
                                      expose key

  Model lacks tool calling            Disable autonomous mode for that
                                      model or explain limitation

  API timeout                         Retry within bounded policy;
                                      preserve task state

  API rate limit                      Respect retry guidance and backoff

  Network lost during AI task         Pause safely; preserve completed
                                      work

  Network lost during local build     Build may continue if dependencies
                                      are already available

  Missing dependency                  Attempt local resolution/download;
                                      report if unavailable

  Unsupported ARM64 binary            Stop affected toolchain and report
                                      incompatibility

  Insufficient storage                Stop safely and preserve source

  App killed during build             Recover process/task state where
                                      possible; inspect process before
                                      retry

  Build fails                         Preserve logs and diagnose

  Repeated identical error            Stop automatic retries and report

  File conflict                       Avoid silent overwrite; preserve
                                      recoverable copy

  User cancels task                   Stop scheduling new operations and
                                      terminate managed processes

  Signing failure                     Preserve unsigned output if valid
                                      and policy permits; explain failure

  Corrupt environment                 Mark DEGRADED and offer repair
                                      without deleting projects
  -----------------------------------------------------------------------

# 16. Performance and Resource Requirements

-   NFR-PERF-001: UI operations must not block on compilation or network
    requests.
-   NFR-PERF-002: Logs must stream incrementally.
-   NFR-PERF-003: Only one active build should run by default.
-   NFR-PERF-004: Large downloads must support resumability where
    available.
-   NFR-PERF-005: Storage estimates must be shown before environment
    installation.
-   NFR-PERF-006: The system must monitor low-storage conditions.
-   NFR-PERF-007: Agent loops and retries must be bounded.
-   NFR-PERF-008: Background operations must respect Android background
    execution limits.

Exact RAM, storage, build-time, and Android-version thresholds remain
subject to device benchmarking.

# 17. Compatibility Requirements

## Initial target

-   Android ARM64.
-   No-root preferred.
-   Phone-first UI.

## Toolchain validation matrix

For each supported toolchain, test: - Installation. - Runtime
execution. - Dependency resolution. - Clean debug build. - Clean release
build. - APK packaging. - APK signature verification. - Build after app
restart. - Build under low-storage conditions. - Build cancellation. -
Build log recovery.

Kotlin/Java native Android are the MVP production targets. Flutter,
React Native, and Web-to-APK must be marked experimental until their
full local release pipeline passes the matrix.

# 18. Dependencies

External dependencies: - AI API provider. - Package repositories. -
Android-compatible toolchain distributions. - Gradle/Maven
repositories. - Framework package registries where relevant.

Internal dependencies: - Environment manager. - Process execution
service. - Project workspace. - Provider manager. - Agent runtime. -
Build engine. - Artifact manager.

All external binary sources must be reviewed for licensing, provenance,
integrity, and ARM64 compatibility.

# 19. Constraints and Risks

  -----------------------------------------------------------------------
  Risk                    Impact                  Mitigation
  ----------------------- ----------------------- -----------------------
  ARM64 toolchain gaps    Some projects cannot    Compatibility matrix
                          build                   and verified native
                                                  binaries

  Android background      Long tasks may be       Managed foreground
  restrictions            interrupted             execution where
                                                  appropriate and
                                                  recovery

  Low RAM                 Build failure or app    Single build, resource
                          termination             checks, clear
                                                  diagnostics

  Storage limits          Setup/build cannot      Preflight storage
                          complete                checks and cache
                                                  controls

  Provider API            Agent tool use fails    Protocol adapters and
  incompatibility                                 capability detection

  Model poor tool-use     Incorrect operations    Tool validation,
  behavior                                        bounded loops,
                                                  execution evidence

  Dependency repository   Build blocked           Cache and retry
  outage                                          

  Toolchain licensing     Distribution risk       License review and
                                                  source attribution

  Signing key loss        Release update          Secure backup/export
                          continuity risk         strategy controlled by
                                                  user

  Long setup time         Poor first-use          Progress, resume,
                          experience              staged optional
                                                  toolchains
  -----------------------------------------------------------------------

# 20. Feature Prioritization and MVP

## MVP --- P0

1.  First-launch automatic setup.
2.  Environment health verification.
3.  OpenAI-compatible provider.
4.  Anthropic-compatible provider.
5.  Custom Base URL/API Key.
6.  Model discovery and manual model fallback.
7.  Autonomous AI agent with tool execution.
8.  Project creation and import.
9.  Filesystem access.
10. Terminal execution.
11. Kotlin/Java project templates.
12. Local Gradle build pipeline.
13. Error diagnosis and bounded repair.
14. Build monitor.
15. APK artifact manager.
16. Secure credential handling.

## Post-MVP --- P1

-   Web-to-APK.
-   Flutter toolchain.
-   React Native toolchain.
-   Git integration.
-   Advanced project templates.
-   More detailed build profiles.

## Future --- P2/P3

-   Advanced toolchain profiles.
-   Extension/plugin system.
-   Advanced project automation.
-   Additional language/framework targets.

# 21. User Stories

-   US-001: As a new user, I want CERJA-PK to install its required
    environment automatically so I do not need to configure tools
    manually.
-   US-002: As a user, I want to connect my own AI provider so I can
    choose the model and API service.
-   US-003: As a user, I want to describe an application in natural
    language so the agent can create the project.
-   US-004: As a user, I want the agent to inspect and edit project
    files so I do not need to make every code change manually.
-   US-005: As a user, I want the agent to run local builds so the APK
    is generated on my device.
-   US-006: As a user, I want the agent to diagnose and repair build
    errors so I can recover from common failures.
-   US-007: As a user, I want to inspect build logs so I can understand
    what happened.
-   US-008: As a user, I want to locate and install generated APK files
    easily.
-   US-009: As a developer, I want access to the project files and
    terminal so I can intervene when necessary.
-   US-010: As a user, I want setup and task progress to survive
    interruption so I do not lose completed work.

# 22. User Journeys

## Journey A --- First Launch

1.  Install CERJA-PK.
2.  Open application.
3.  View onboarding and storage/network requirements.
4.  Run device compatibility check.
5.  Start automatic setup.
6.  Observe package installation and verification.
7.  Complete environment smoke test.
8.  Reach Home Dashboard.

## Journey B --- Connect AI

1.  Open AI Provider settings.
2.  Add provider.
3.  Select OpenAI-compatible or Anthropic-compatible protocol.
4.  Enter Base URL and API Key.
5.  Fetch model list.
6.  Select model or enter model ID manually.
7.  Run connection test.
8.  Save provider.

## Journey C --- Create Application

1.  Create new project.
2.  Enter application idea in AI Workspace.
3.  Agent analyzes request.
4.  Agent creates plan and project structure.
5.  Agent writes source code.
6.  Agent resolves dependencies.
7.  Agent runs checks.
8.  Agent builds APK locally.
9.  Agent diagnoses and repairs errors if necessary.
10. Artifact is verified and registered.
11. User receives result and artifact location.

## Journey D --- Import Existing Project

1.  Select Import Project.
2.  Choose project directory/archive.
3.  Validate and extract safely.
4.  Detect framework and toolchain.
5.  Agent inspects project.
6.  User provides task.
7.  Agent performs changes and local build.

# 23. Acceptance Criteria

## AC-001 --- First Setup

Given a supported device with sufficient storage and internet, when the
application is opened for the first time, then the environment setup
starts and reaches READY only after required verification succeeds.

## AC-002 --- Provider Connection

Given valid provider details, when the user saves the configuration and
tests it, then CERJA-PK reports the actual connection result without
exposing the API Key.

## AC-003 --- Model Discovery

Given a provider with a supported model-list endpoint, when the user
requests model discovery, then the available model IDs are displayed. If
discovery is unsupported, manual model entry remains available.

## AC-004 --- Autonomous File Creation

Given an active project and a valid instruction, when the agent executes
the task, then created files exist in the selected workspace and are
listed in the task summary.

## AC-005 --- Local Build

Given a supported Kotlin/Java project and valid local toolchain, when
the agent runs a build, then compilation and packaging execute on-device
and produce a verified artifact.

## AC-006 --- Build Failure Recovery

Given a build failure with actionable diagnostics, when the agent
attempts repair, then changes are applied within retry limits and a new
build is executed. If repair fails, the agent reports the actual
failure.

## AC-007 --- Network Interruption

Given a local build whose dependencies are already available, when
internet connectivity is lost, then the build can continue without
invoking any cloud build service.

## AC-008 --- Task Cancellation

Given an active agent task, when the user cancels it, then the system
stops scheduling new operations and terminates managed processes where
feasible.

## AC-009 --- Credential Safety

Given a configured API Key, when the agent reads project files or build
logs, then the raw credential is not exposed to agent context or
ordinary logs.

## AC-010 --- Artifact Verification

Given a build process reports success, when the artifact manager
registers the output, then the expected artifact must exist and pass
applicable metadata/signature checks.

# 24. Testing Requirements

## 24.1 Environment Tests

-   Fresh install.
-   Interrupted setup.
-   Resume setup.
-   Insufficient storage.
-   Repository failure.
-   Package checksum mismatch.
-   Environment repair.
-   App restart during setup.

## 24.2 AI Tests

-   OpenAI-compatible provider.
-   Anthropic-compatible provider.
-   Invalid API key.
-   Invalid Base URL.
-   Model discovery unavailable.
-   Model without tool calling.
-   Rate limiting.
-   Timeout.
-   Cancellation.
-   Context overflow.
-   Tool execution error.

## 24.3 Agent Tests

-   Create project.
-   Edit project.
-   Multi-file changes.
-   Dependency installation.
-   Build and repair.
-   Repeated failure stop condition.
-   Workspace boundary enforcement.
-   Malicious instruction attempting secret/file access.
-   App interruption and task recovery.

## 24.4 Build Tests

-   Kotlin debug build.
-   Kotlin release build.
-   Java debug build.
-   Java release build.
-   APK packaging.
-   Signing.
-   Missing SDK.
-   Incompatible Gradle/JDK.
-   Unsupported ARM64 binary.
-   Dependency outage.
-   Low storage.
-   Build cancellation.

## 24.5 UI Tests

-   Small phone screen.
-   Keyboard visibility.
-   Long build logs.
-   Empty project list.
-   Loading and error states.
-   App background/foreground.
-   Accessibility and text scaling.

# 25. Release Strategy

## Phase 1 --- Technical Feasibility Prototype

-   Validate ARM64 execution environment.
-   Validate JDK and Gradle.
-   Validate Android build tools.
-   Produce a simple Kotlin APK locally.
-   Verify APK signing and installation.
-   Confirm no root requirement.

## Phase 2 --- MVP Foundation

-   Implement setup wizard.
-   Implement environment manager.
-   Implement provider manager.
-   Implement project workspace.
-   Implement terminal and process execution.

## Phase 3 --- Autonomous Development

-   Implement agent runtime.
-   Implement tool calling.
-   Implement task state and checkpointing.
-   Implement error diagnosis and repair.
-   Implement local build orchestration.

## Phase 4 --- Product Completion

-   Implement artifact manager.
-   Complete UX.
-   Run security and regression testing.
-   Validate supported-device matrix.

## Phase 5 --- Additional Frameworks

-   Validate Web-to-APK.
-   Validate Flutter.
-   Validate React Native.
-   Release only after full build validation.

# 26. Future Roadmap

Potential future enhancements: - More native Android templates. - Better
project import and migration. - Git-based checkpoints. - Build cache
optimization. - Configurable build profiles. - Framework compatibility
catalog. - Advanced agent planning and parallelizable tasks. -
User-defined agent instructions. - Optional offline project editing and
cached dependency builds.

These are not MVP commitments.

# 27. Open Questions and Unresolved Decisions

The following decisions remain unresolved and must not be silently
invented by implementation agents.

  ----------------------------------------------------------------------------
  ID                      Question                     Why it matters
  ----------------------- ---------------------------- -----------------------
  OQ-001                  Minimum Android version?     Determines API and
                                                       runtime compatibility

  OQ-002                  Minimum RAM recommendation?  Determines realistic
                                                       build capability

  OQ-003                  Minimum free storage for     Toolchains may consume
                          initial setup?               substantial space

  OQ-004                  Should environment use a     Major architecture and
                          Termux-compatible base,      licensing decision
                          custom userspace, or another 
                          approach?                    

  OQ-005                  Exact source/distribution    Legal, integrity, and
                          for ARM64 Android build      compatibility
                          tools?                       requirements

  OQ-006                  Should users be able to      Storage and Android
                          choose installation          scoped-storage
                          location?                    implications

  OQ-007                  Should Git be included in    Scope and
                          MVP?                         implementation effort

  OQ-008                  Release signing UX and key   Long-term update
                          backup policy?               continuity and security

  OQ-009                  Should the editor be         UI complexity and
                          full-featured or lightweight memory usage
                          in MVP?                      

  OQ-010                  What device benchmark        Need measurable device
                          defines supported build      compatibility
                          sizes?                       

  OQ-011                  Should background builds     Android foreground
                          continue when the app is not service and battery
                          visible?                     policy

  OQ-012                  Which exact                  Reproducibility and
                          Kotlin/Java/Gradle/AGP/SDK   supportability
                          versions form the first      
                          compatibility baseline?      
  ----------------------------------------------------------------------------

# 28. Requirement Traceability Matrix

  Requirement group   Module                Acceptance criteria
  ------------------- --------------------- ------------------------
  SETUP               Environment Setup     AC-001
  ENV                 Environment Manager   AC-001
  API                 Provider Manager      AC-002, AC-003, AC-009
  AGENT               Autonomous Agent      AC-004, AC-006, AC-008
  PROJ                Project Workspace     AC-004
  TERM                Terminal              AC-004, AC-008
  DEP                 Dependency Manager    AC-005, AC-007
  BUILD               Local Build Engine    AC-005, AC-006, AC-007
  MON                 Build Monitor         AC-005, AC-008
  ERR                 Error Recovery        AC-006
  ART                 Artifact Manager      AC-010
  SEC                 Security              AC-009

# 29. Final Quality Assessment

## Completeness

Core product modules, principal workflows, local build requirement, AI
integration, setup, and artifact management are defined. Several
platform and toolchain decisions remain open.

## Internal Consistency

The document consistently distinguishes API-based AI inference from
local compilation. Cloud build is excluded. Full autonomy is bounded by
workspace and security controls.

## Technical Feasibility

Kotlin/Java local build is the initial feasibility target. Flutter,
React Native, and Web-to-APK require individual ARM64 validation. The
exact environment architecture and binary distribution strategy remain
unresolved.

## Testability

Core workflows have Given/When/Then acceptance criteria. Additional
numerical performance thresholds require prototype benchmarking.

## Implementation Readiness

This PRD is suitable for architecture discovery and technical
feasibility prototyping. It is not yet a complete implementation lock
because the unresolved questions in Section 27 can materially affect the
architecture.

**Overall status: Ready for technical feasibility phase; not yet final
architecture freeze.**

------------------------------------------------------------------------

# 30. Development Handoff Directive

The engineering agent must:

1.  Read this PRD in full before implementation.
2.  Preserve all confirmed decisions.
3.  Resolve open architecture questions through evidence and prototype
    tests.
4.  Never replace local build with cloud build.
5.  Never claim ARM64 support without successful local release-build
    evidence.
6.  Maintain traceability between requirements, implementation, and
    tests.
7.  Report deviations before changing confirmed requirements.
8.  Prioritize a working Kotlin/Java local APK pipeline before expanding
    framework support.
9.  Keep the application modular and resource-aware.
10. Deliver a working product rather than a visual-only prototype.

**Definition of success:** A user can install CERJA-PK, complete
automatic environment setup, connect an AI provider, instruct the agent
to create or modify an Android application, and receive a verified APK
built entirely on the user's Android device.
