# Real Estate App

## 📱 Overview
Android app for browsing real estate listings with:
- REST API (Retrofit)
- Clean Architecture (DTO → Domain → UI)
- Bookmark feature (Room DB)
- Reactive UI (StateFlow + Jetpack Compose)
- Basic error handling system

---

## 🧱 Architecture

### Data layer
- Remote: Retrofit API + DTO models
- Local: Room database (bookmarks)
- Repository: single source of truth per feature

### Domain layer
- Business models (Property, enums)
- Repository interfaces
- AppError + ResponseResult wrapper

### Presentation layer
- ViewModels (state management)
- UI models (PropertyUiModel)
- Compose screens
- Mappers (Domain → UI)

---

## 🔁 Data Flow

API → DTO → Domain Model → UI Model → Compose UI

Bookmarks:
Room DB → Flow → UI (reactive updates)

---

## ⚙️ Tech Stack

- Kotlin
- Jetpack Compose
- Retrofit + Gson
- Room
- Coroutines + Flow
- Coil (image loading)

---

## ✅ Features

- Property listing screen
- Loading state handling
- Error handling (network, server, unknown)
- Swipe-to-refresh support
- Bookmark toggle (offline persistence)
- Reactive UI updates via Flow

---

## ⚠️ Important Edge Cases Handled

- Room persistence survival after app restart
- No internet connection
- API failure / server error
- Null or missing fields in DTO
- Empty property list
- Image loading fallback (Coil)
---

## 🚨 Important Design Decisions

### 1. Single Source of Truth for bookmarks
Room Flow is the only source for bookmark state  
→ UI reacts automatically without manual refresh

---

### 2. ResponseResult wrapper
Used for:
- Loading state
- Success state
- Error state

This avoids UI crashes and simplifies state handling.

---

### 3. No Dependency Injection (intentional)
Manual DI used for simplicity (task scope).  
In production → should be replaced with Hilt/Koin.

---

### 4. UI is reactive
Compose recomposes automatically when:
- Flow emits new data
- Bookmark state changes

---

## 🚀 Future Improvements

- Dependency Injection (Hilt)
- Unit + UI tests
- UI animations (bookmark + transitions)

---

## ▶️ Run Instructions

1. Clone project
2. Open in Android Studio
3. Sync Gradle
4. Run on emulator/device

---