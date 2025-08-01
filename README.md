### StarWars-Explorer 👽


StarWars-Explorer is a modern Android application built using Jetpack Compose. It serves as a client for the Star Wars API (SWAPI), allowing users to explore data from the Star Wars universe. The app fetches and displays all available results based on the user's search queries (films, starships, vehicles), providing a smooth and responsive UI experience.


## ✨ Key Features

- ✔️ Search Star Wars data  
- ✔️ Display categorized results  
- ✔️ Cache results locally  
- ✔️ Offline access  
- ✔️ Clear local cache  

## 🛠️ Technologies Used


| Layer         | Libraries                                              |
|---------------|--------------------------------------------------------|
| UI            | Jetpack Compose, Material3, Accompanist               |
| DI            | Hilt                                                  |
| Database      | Room                                                  |
| Network       | Retrofit, OkHttp,  Gson                               |
| Async         | Kotlin Coroutines, Flow                               |
| Navigation    | Jetpack Navigation-Compose                            |
| Testing       | JUnit, MockK, Mock webserver, Compose UI Test         |
|Architecture  | Clean Architecture, MVVM Pattern                       |



## Screenshots

<table style="padding:10px">
	<tr>
		<td align="center">
			<h2>Search Screen</h2>
		</td>
		<td align="center">
			<h2>Result Screen</h2>
		</td>
  	</tr>
	<tr>
    	<td align="center">
			<img src="https://github.com/user-attachments/assets/c3515335-ac2c-4462-bd5f-4585b5aa3042" alt="Trending Screen" width="200"/>
    	</td>
		<td align="center">
			<img src="https://github.com/user-attachments/assets/8d5b9ffb-264c-4224-b908-2b9dd7459699" alt="Upcoming Screen" width="200"/>
    	</td>
  	</tr>
</table>


## 🧱 Architecture

The app follows a clean MVVM architecture.

```
data/
├── model/
├── repository/
├── local/
├── remote/

domain/
├── usecase/

presentation/
├── ui/
├── components/
├── screens/
├── ViewModel

di/
├── NetworkModule.kt
```

## 🚀 Getting Started

### Prerequisites

- Android Studio Meerkat or later
- Kotlin 2.0.21+
- Gradle 8.11.1+
- Min SDK 24+


### Clone the repo

```bash
git clone https://github.com/erinfolami/StarWars-Explorer.git
cd StarWars-Explorer
```

### 🔐 Secrets & API Keys
No secrets/ API Keys included in the project

### 🧪 Testing
- **Unit tests:**  
  ```bash
  ./gradlew testDebugUnitTest

- **UI tests:**  
  ```bash
  ./gradlew connectedDebugAndroidTest

### 📄 License
This project is licensed under the [MIT License](./LICENSE). See the LICENSE file for details.

### 🙋 Contributing
Pull requests are welcome! For major changes, open an issue first to discuss what you’d like to change.
