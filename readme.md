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

4. **UI Updates**

- Compose observes `state` via `collectAsState()`
- Re-composes automatically when state changes
- Shows loading, error, or data UI

#### Example: Loading Movies on Screen with State

```kotlin
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    // Load default movies when screen appears
    LaunchedEffect(key1 = true) {
        if (state.movies.isEmpty()) {
            viewModel.handleIntent(MovieListIntent.LoadDefaultMovies)
        }
    }

    when {
        state.isLoading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { CircularProgressIndicator() }

        state.error != null -> Text("Error: ${state.error}")

        else -> LazyColumn {
            items(state.movies) { movie ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Navigate to movie detail */ }
                        .padding(8.dp)
                ) {
                    AsyncImage(
                        model = movie.poster,
                        contentDescription = movie.title,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(movie.title, style = MaterialTheme.typography.titleMedium)
                        Text(movie.year, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
```
#### Key Points:

- state is observed reactively using collectAsState()
- LaunchedEffect(key1 = true) ensures one-time execution on composition
- Repository decides whether to fetch from cache or network
- DTOs are mapped to Domain Models to keep UI layer decoupled

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

| Movie List | Movie Detail |
|------------|--------------|
| <img src="app/screenshots/movie_list.png" alt="Movie List" width="300"/> | <img src="app/screenshots/movie_detail.png" alt="Movie Detail" width="300"/> |


---

## 📝 License

MIT License – see the [LICENSE](LICENSE) file for details.
