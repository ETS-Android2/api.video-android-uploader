[![badge](https://img.shields.io/twitter/follow/api_video?style=social)](https://twitter.com/intent/follow?screen_name=api_video) &nbsp; [![badge](https://img.shields.io/github/stars/apivideo/android-video-uploader?style=social)](https://github.com/apivideo/android-video-uploader) &nbsp; [![badge](https://img.shields.io/discourse/topics?server=https%3A%2F%2Fcommunity.api.video)](https://community.api.video)
![](https://github.com/apivideo/API_OAS_file/blob/master/apivideo_banner.png)
<h1 align="center">api.video Android uploader</h1>

[api.video](https://api.video) is the video infrastructure for product builders. Lightning fast video APIs for integrating, scaling, and managing on-demand & low latency live streaming features in your app.

# Table of contents

- [Project description](#project-description)
- [Getting started](#getting-started)
  - [Requirements](#requirements)
  - [Installation](#installation)
    - [Maven users](#maven-users)
    - [Gradle users](#gradle-users)
    - [Others](#others)
  - [Code sample](#code-sample)
- [Documentation](#documentation)
  - [API Endpoints](#api-endpoints)
    - [VideosApi](#VideosApi)
  - [Models](#models)
  - [Authorization](#documentation-for-authorization)
    - [API token](#api-token)
    - [Public endpoints](#public-endpoints)
  - [Recommendation](#recommendation)
- [Have you gotten use from this API client?](#have-you-gotten-use-from-this-api-client-)
- [Contribution](#contribution)

# Project description

api.video's Android uploader streamlines the coding process. Chunking files is handled for you, as is pagination and refreshing your tokens.

# Getting started

## Requirements

Building the API client library requires:
1. Java 1.8+
2. Maven/Gradle

## Installation

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>video.api</groupId>
  <artifactId>android-video-uploader</artifactId>
  <version>0.1.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
implementation "video.api:android-video-uploader:0.1.0"
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/android-video-uploader-0.1.0.jar`
* `target/lib/*.jar`

## Code sample

Please follow the [installation](#installation) instruction and execute the following Kotlin code:

```kotlin
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import video.api.uploader.api.ApiException
import video.api.uploader.api.models.*
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        
        // "YOUR_API_TOKEN" is only required for upload (not for uploadWithUploadToken)
        val videoApi = VideosApi(ApiClient("YOUR_API_TOKEN", Environment.PRODUCTION))
        // if you rather like to use the sandbox environment:
        // val videoApi = VideosApi(ApiClient("YOUR_API_TOKEN", Environment.SANDBOX))
        // if your rather like to use video token:
        // val videoApi = VideosApi(ApiClient(Environment.PRODUCTION))
        
        val myVideoFile = File("my-video.mp4")

        /**
         * Notice: you must not call API from the UI/main thread. Dispatch with Thread, Executors or Kotlin coroutines.
         */
        executor.execute {
            try {
                video = videoApi.upload("MY_VIDEO_ID", myVideoFile)
                // if your rather like to use video token:
                // video = videoApi.uploadWithUploadToken("MY_VIDEO_TOKEN", myVideoFile)
                Log.i("Example", "$video")
            } catch (e: ApiException) {
                Log.e("Example", "Exception when calling VideoApi")
                e.message?.let {
                    Log.e("Example", "Reason: ${it}")
                }
            }
        }
    }
}

```

## Example

An example that demonstrates how to use the API is provided in folder `example/`.

# Documentation

## API Endpoints

All URIs are relative to *https://ws.api.video*


### VideosApi


#### Retrieve an instance of VideosApi:
```kotlin
val videosApi = VideosApi(ApiClient("YOUR_API_TOKEN", Environment.PRODUCTION))
```



#### Endpoints

Method | HTTP request | Description
------------- | ------------- | -------------
[**uploadWithUploadToken**](docs/VideosApi.md#uploadWithUploadToken) | **POST** /upload | Upload with an upload token
[**upload**](docs/VideosApi.md#upload) | **POST** /videos/{videoId}/source | Upload a video



## Documentation for Models

 - [AccessToken](docs/AccessToken.md)
 - [AuthenticatePayload](docs/AuthenticatePayload.md)
 - [BadRequest](docs/BadRequest.md)
 - [Metadata](docs/Metadata.md)
 - [NotFound](docs/NotFound.md)
 - [RefreshTokenPayload](docs/RefreshTokenPayload.md)
 - [Video](docs/Video.md)
 - [VideoAssets](docs/VideoAssets.md)
 - [VideoSource](docs/VideoSource.md)
 - [VideoSourceLiveStream](docs/VideoSourceLiveStream.md)
 - [VideoSourceLiveStreamLink](docs/VideoSourceLiveStreamLink.md)


## Documentation for Authorization

### API token

Most endpoints required to be authenticated using the API token mechanism described in our [documentation](https://docs.api.video/reference#authentication).
The access token generation mechanism is automatically handled by the client. All you have to do is provide an API token when instantiating the ApiVideoClient:
```kotlin
val videosApi = VideosApi(ApiClient("YOUR_API_TOKEN", Environment.PRODUCTION))
```

### Public endpoints

Some endpoints don't require authentication. These one can be called with a client instantiated without API token:
```kotlin
val videosApi = VideosApi(ApiClient(Environment.PRODUCTION))
```

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.
Do not call API from the main thread, otherwise you will get a android.os.NetworkOnMainThreadException. Dispatch API calls with Thread, Executors or Kotlin coroutine to avoid this.

## Have you gotten use from this API client?

Please take a moment to leave a star on the client ⭐

This helps other users to find the clients and also helps us understand which clients are most popular. Thank you!

# Contribution

Since this API client is generated from an OpenAPI description, we cannot accept pull requests made directly to the repository. If you want to contribute, you can open a pull request on the repository of our [client generator](https://github.com/apivideo/api-client-generator). Otherwise, you can also simply open an issue detailing your need on this repository.