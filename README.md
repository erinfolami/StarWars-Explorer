### StarWars-Explorer 👽


StarWars-Explorer is a modern Android application built using Jetpack Compose. It serves as a client for the Star Wars API (SWAPI), allowing users to explore data from the Star Wars universe. The app fetches and displays all available results based on the user's search queries, providing a smooth and responsive UI experience.


### 🛠️ Technologies Used


* 🛠️ Technologies Used

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



### Screenshots

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
data/<br>
├── model/
├── repository/
├── local/
├── remote/

domain/<br>
├── usecase/

presentation/
├── ui/
├── components/
├── screens/
	├── ViewModel

di/
├── NetworkModule.kt/
```


### Prerequisites

- Android Studio Giraffe or later
- Kotlin 1.9+
- Gradle 8.0+
- Min SDK 24+



