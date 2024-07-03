# Articles Headlines App
The Articles Headlines App Android app is constructed entirely using Kotlin and Jetpack architecure components. All business logics are covered with unit tests.
# Features
This application is designed to display the latest headlines and news from selected sources. There is three tabs in the application to show latest list of headlines, sources and saved articles. On click of headlines, User will navigate to webview to read full detail article.
# Screenshots
# Architecture
The Articles Headlines App app followed MVVM architecture. The two major layers are:
* UI layer
* Data layer

![app_diagram](https://github.com/swatishrivastava/newsApp/assets/11307086/c7ba6a6f-b8b0-45c3-94bf-31a76c270b58)

# Modularization
Articles Headlines App fully modularized application. Please find all modules details below: 



![modules drawio](https://github.com/swatishrivastava/newsApp/assets/11307086/59635dc2-c8c9-4f4c-b4a5-e01e0efaee8d)




| Names        | Responsibilities           | Key classes  |
| ------------- |:-------------:| -----:|
| app      | Responsible to bring together all features  | NewsActivity |
| feature: headlines      | Responsible for showing list of headlines as per selected sources and showing detail article on webview        |   $12 |
| feature: sources | showing list of sources and allow user to select multiple sources at once      |    $1 |
| feature: saved_articles | showing list of saved articles to read later.      |    $1 |

# API Usage
#### Source API Endpoint
##### HTTP Method: GET
URL: https://newsapi.org/v2/top-headlines/sources?apiKey=API_KEY

#### Headlines API Endpoint
##### HTTP Method: GET
URL: https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY

# Testing
Junit4 and mockito used to write unit tests for all business logics . All Viewmodel codes are covered with the Unit tests.
