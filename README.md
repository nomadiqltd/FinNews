# FinNews

FinNews is a Jetpack Compose app using Material 3 design elements. This Android app sample is the leading FinTech and finance news app which allows the user to display a list of articles, view the details and has visually decorative actions such as: Search, Favourite and Bookmark.

# Prerequisites: 

Before running the app, ensure you have the following installed on your system:

- [Android Studio](https://developer.android.com/studio) - [Minimum Android SDK Version: **24** | Target Android SDK Version: **34**]
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - [JDK Version: **17**]

**Build System**
- Gradle
- Kotlin

Latest App version : **1.0.0**

<img src="https://github.com/nomadiqltd/FinNews/assets/14942202/8c9f9d15-f179-43ff-b872-44eee97bb17e" width="30%" /> | 
<img src="https://github.com/nomadiqltd/FinNews/assets/14942202/dba9814a-1227-4376-a90c-200626b0e948" width="30%" /> |
<img src="https://github.com/nomadiqltd/FinNews/assets/14942202/83f743c6-42fa-42d8-854a-3a2d408da19a" width="30%" />

# Running the App

Follow these steps to run the app:

1. **Clone the Repository**: -> Clone this GitHub repository to your local machine using Git:

   ```bash
   git clone https://github.com/nomadiqltd/FinNews.git

2. - On Github, check out the 'main' branch.

3. - Request a new Guardian News API key from [here](https://open-platform.theguardian.com/documentation/).

4. - Navigate to the **_build.gradle_** file within the **_app_** module (not the build.gradle at the root of the directory).

- Replace the current Guardian News Api key inside the _buildTypes{}_ closure (within the _build.gradle_ file) with your new Guarduan News Api Key.
  ```bash
  val apiKey = "\"XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX\""
5. - Select _Build_ -> _Rebuild Project_ and then select _Run_ after the project successfully builds.

# Architecture
The chosen architecture is MVVM (Model View ViewModel) with a Clean-ish approach. 

The main 3 layers are: 

**Presentation Layer**
- This layer displays the application data on the screen. This includes the Jetpack Compose functions, ViewModels and UI State.

**Domain Layer**
- The domain layer is the mediator between the data layer and the presentation layer.
- This app is a pretty small sample but for future scalability it will become more complex and all that logic that would have lived in the ViewModel would now reside in smaller UseCases instead (optional layer).

**Data Layer**
- The data layer contains repositories that abstract away the complexity of the data's origins. In this case, it's all remote data but it has the capability to extend to a form of local storage i.e. a Database.

**Project Dependencies**

Below is a brief summary of the main dependencies below:

# **Jetpack libraries**
Compose
- Declarative UI with composable functions as opposed to the older Android View system (old school XML layouts)
  
Arch Core
- Helper for other arch dependencies, including JUnit test rules

Hilt
- Dependency injection library to allow the various app components to best utlisie their dependencies and improve testability

Navigation
- Default navigation library for Compose

Kotlinx Coroutines
- Asynchronous programming to obtain data from the network and leverage concurrency concepts for a more performant app

Retrofit
- HTTP client that supports coroutines
  
Moshi
- JSON Parser, used to parse requests on the data layer for Entities (coupled with okhhtp interceptors)

JUnit
- This was used for unit testing the use cases, ViewModels, and the Repositories
  
Mockk / Mockito
 - Used to provide test fakes / doubles to aid in testing other components by reducing side effects of their intrinsic dependencies

Truth
 - Readable Assertions Library to aid test fidelity

Espresso
 - Used for writing Android UI tests (that require the Android system components i.e. Activities, some Composables)

Coil
 - Image loading for Android backed by Kotlin Coroutines.

# **Testing**

Coverage can always be improved. Included a small sample of various test types to cover all three layers. Mainly unit, integrated, instrumented tests.
- @Composables, ViewModels, Usecases, Repositories have automated tests.

# **Known Improvements:**
- Offline support - use of a snackbar to inform the user there is poor or no internet connectivity. Currently just a basic message.

# **Out of Scope**
- Offline mode
- Minor testing in obfuscated mode / Proguard mode only
- Persist the Articles to local storage so the user always has access even when offline
