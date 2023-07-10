# **NewsBreeze** 

NewsBreeze is an unofficial news app that uses the News API to fetch the latest breaking news articles and presents them to the user in a user-friendly interface. The app allows users to view article details, save articles for later reading, search for articles by title, and sort articles by date and publication. It also includes offline caching of news articles.

# Application Install

***You can Install and test latest Newsbreeze app from below 👇***

[![NewsBreeze](https://img.shields.io/badge/Newsbreeze✅-APK-red.svg?style=for-the-badge&logo=android)](https://drive.google.com/file/d/1zT3zHbZm7ne77H6M0YKHcnQBLSVyt76C/view?usp=sharing)


### App Features
1. Fetching Breaking News
The app fetches the latest breaking news articles from the News API and displays them in a list format. Each article in the list includes the following details:

- Image: The article's thumbnail or representative image.
- Title: The headline or title of the article.
- Date: The publication date of the article.
- Description: A short description or summary of the article's content.

2. Viewing Full Article
When the user clicks on an article in the list, the app displays the full article, including the complete text, images, and any additional details available. This allows users to read the entire article within the app.

3. Save to Read Later
Each article in the list has a "Save to Read Later" button. When clicked, the article is saved for later reading in a persistent format. This means that even if the user closes the app and reopens it later, the saved articles will still be accessible.

4. Read Later List
The app provides a separate section or screen where users can view all the articles they have saved for later reading. Clicking on any article in the "Read Later" list displays the full article, allowing users to read it without searching for it again.

5. Search by Title
The app includes a search feature that allows users to search for articles by title. Users can enter keywords or phrases related to the article's title, and the app filters the list of articles to display only those matching the search query.

6. Sorting by Date
To enhance user experience, the app offers the ability to sort the breaking news articles by date and publication. Users can choose to view articles in ascending or descending order based on their preference.

7. Offline Caching
In case of no internet connection, the app caches news articles locally on the user's device. This allows users to continue browsing and reading articles even when they are offline.


## 📸 Screenshots
![Group 2](https://github.com/priyanshuborole/Greedy-Game-Assignment/assets/69722542/ef994545-e1d2-4add-96f7-5657f4ae1473)

### Technical details 
- Newsbreeze is made using Kotlin and following Modern Android Development practices.
- Newsbreeze uses all Jetpack libraries and follows Clean + MVVM architecture.
- Newsbreeze uses coroutines and flows for asynchronous programming



## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Room](https://developer.android.com/training/data-storage/room) - Room is an android library which is an ORM which wraps android's native SQLite database
- [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
  - [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
  - [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack) - DI for injecting `ViewModel`.
- [Retrofit](https://github.com/square/retrofit) - An Http Client for android.
- [GSON Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) - A Converter which uses Moshi for serialization to and from JSON.
- [Glide](https://github.com/bumptech/glide) - An image loading library for Android \
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

# Package Structure
   
    ├── data                    # For data handling.
    |   ├── local               # Room DB and its related classes
    |   ├── remote              # Retrofit and its related classes
    │   └── repo impl           # Implementation of repository interface of domain layer
    |
    ├── di                      # Dependency Injection             
    │   └── module              # DI Modules
    |
    ├──domain                   # Enterprise logic and types
    │   ├── model               # Model data classes and mapper classes, both remote and local entities
    |   ├── repo                # Repository Interface decoupling
    |   └── usecases            # Control the flow to and from entities
    |
    ├── presentation            # UI/View layer
    |   ├── viewmodel           # To persist UI state
    |   ├── fragment            # All Screens in app.    
    │   ├── adapter             # Adapters for recyclerview
    |
    └── common                  # Utility Classes / Kotlin extensions


## Architecture
This app uses [***CLean Architecture + MVVM***] architecture

## Next plans
- Pagination
- Shimmer effect
- Test cases
Due to time constraint, was not able to implement all things
  
---
  

