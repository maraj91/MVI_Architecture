# 🎬 MVI Architecture Movie App

A modern Android application demonstrating **Model-View-Intent (MVI)** architecture using **Jetpack Compose**, **Hilt**, and **Clean Architecture** principles.  

This app integrates with **OMDb API** to fetch movie data and displays a responsive UI with search and detailed movie screens.

---

## 📱 Features

- Search movies by title with real-time filtering  
- View detailed information about selected movies  
- Clean and responsive **Jetpack Compose** UI  
- Asynchronous data handling with **Coroutines** and **Flow**  
- Error handling and loading states  
- Dependency Injection with **Hilt**  
- Image loading with **Coil**  

---

## 🏗 Architecture Overview

The app follows **Clean Architecture** principles and the **MVI pattern**, ensuring separation of concerns and testable components.

### 1️⃣ Presentation Layer
- **UI (Compose Screens)**: Responsible for rendering UI and observing state  
- **ViewModel**: Processes **Intents**, updates **State**, triggers **Use Cases**  
- **Intent**: Represents user actions like search or item click  
- **State**: Represents current UI state (loading, success, error)  

### 2️⃣ Domain Layer
- **Use Cases**: Encapsulate business logic, orchestrate repository calls  
- **Repository Interface**: Abstracts data operations  
- **Models**: Domain-level models used across the app  

### 3️⃣ Data Layer
- **Repository Implementation**: Implements repository interface, handles mapping  
- **API Service (Retrofit)**: Fetches data from OMDb API  
- **DTOs & Data Models**: Represents raw API responses  
- **Mappers**: Convert DTOs to domain models  

---

## 🛠 Tech Stack

| Layer | Libraries / Tools |
|-------|-----------------|
| Language | Kotlin |
| UI | Jetpack Compose |
| DI | Hilt |
| Networking | Retrofit + OkHttp |
| Image Loading | Coil |
| Async | Coroutines + Flow |
| Architecture | MVI + Clean Architecture |
| Navigation | Jetpack Compose Navigation |

---

## 🔄 Data Flow (MVI + Clean Architecture)

1. **User Interaction**  
   - User types a search query or taps a movie  
   - **Intent** is emitted to the **ViewModel**

2. **ViewModel Processing**  
   - Receives **Intent**, updates **State** to `Loading`  
   - Calls relevant **Use Case**  

3. **Use Case Execution**  
   - Handles business logic  
   - Calls **Repository** to fetch data  

4. **Repository Handling**  
   - Checks cache or network  
   - Maps DTOs to **Domain Models**  

5. **UI Update**  
   - **State** updated in ViewModel (`Success`, `Error`)  
   - Compose UI observes State and renders accordingly  

---

## 🗂 Project Structure

```
com.example.mvi_architecture/
├── app/                        # Application class and setup
│   └── MovieApp.kt
├── data/
│   ├── model/                  # DTOs for API responses
│   ├── remote/                 # API interfaces (Retrofit)
│   └── repository/             # Repository implementations
├── di/                         # Dependency Injection modules (Hilt)
├── domain/
│   ├── model/                  # Domain models
│   ├── repository/             # Repository interfaces
│   └── usecase/                # Business logic / use cases
├── navigate/                   # Compose navigation graphs
├── presentation/
│   ├── movie_detail/           # Movie detail screen
│   └── movie_list/             # Movie list screen
├── ui.theme/                   # Compose theming
└── MainActivity.kt             # Entry point
```

---

## 🧩 Dependency Injection

- **Hilt** provides DI across layers:  
  - **Repository** → **Use Case** → **ViewModel** → **UI**
- Use `@Inject` in constructors to automatically provide dependencies  

**Example:**

```kotlin
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() { ... }
```

---

## 📡 API Integration

- OMDb API is used for fetching movies:  
  - Search: `https://www.omdbapi.com/?s={query}&apikey={API_KEY}`  
  - Details: `https://www.omdbapi.com/?i={movieID}&apikey={API_KEY}`  

- **DTO → Domain Mapping** ensures separation of concerns

---

## 🚀 Getting Started

1. Clone the repository:  
   ```bash
   git clone https://github.com/yourusername/mvi-movie-app.git
   ```
2. Open in **Android Studio**  
3. Add your OMDb API key to `local.properties`:  
   ```
   OMDB_API_KEY=your_api_key_here
   ```
4. Build and run the app  

---

## Screenshots

### Movie List Screen
![Movie List](screenshots/movie_list.png)

### Movie Detail Screen
![Movie Detail](screenshots/movie_detail.png)

---

## 📝 License

MIT License – see the [LICENSE](LICENSE) file for details.