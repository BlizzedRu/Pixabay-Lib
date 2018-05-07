# Pixabay-Lib
Simple Java library for [Pixabay](https://pixabay.com) API

**Pixabay-Lib** provides easy access to all available methods of [Pixabay API][doc]. 
Can be useful for grabbing some pictures and videos from different categories.

* Contains Java object wrappers for any API response
* Supports RxJava Observable responses
* Convenient request builders

Full list of methods and its params available in [Pixabay API Documentation][doc]

## Installing

#### Maven

In your pom.xml inside the *\<dependencies>* tag
```xml
<dependencies>
    ...
    <dependency>
        <groupId>ru.blizzed</groupId>
        <artifactId>pixabay-lib</artifactId>
        <version>1.0.3</version>
    </dependency>
</dependencies>
```

#### Gradle

In your build.gradle file inside the *dependencies* section

* Gradle 3.0 and above
``` 
dependencies {
   ...
   implementation 'ru.blizzed:pixabay-lib:1.0.3'
}
```
  
* Below 3.0
``` 
dependencies {
    ...
    compile 'ru.blizzed:pixabay-lib:1.0.3'
}
```
  
## Usage

#### Initialization

* You don't care about language (`EN` is default)
```java 
Pixabay.initialize("your-api-key"));
```

* You need a specific language from available lang list
```java 
Pixabay.initialize("your-api-key", LangParam.Lang.RU));
```
_Russian language as example, you can choose any other_

#### Building and executing requests

All methods of Pixabay API are available in `Pixabay` class after initialization. Use `Pixabay.search()` for 
usual case and `Pixabay.rxSearch()` if you prefer to deal with RxJava and its Observables.

You can pass any params to the call – just take a look at static class `PixabayParams` that 
contains completed instances of all parameters.

Imagine that you need to find some pics with animals

```java 
Pixabay.search().image(PixabayParams.CATEGORY.of(Category.ANIMALS))
        .execute()
        .getHits()
        .stream()
        .map(PixabayImage::getLargeImageURL)
        .forEach(System.out::println);
```

or the same but with a set of params (now you are looking for red pics with dog in cat. animals and horizontal orientation)

```java 
Pixabay.search().image(
             "dog",
             PixabayParams.CATEGORY.of(Category.ANIMALS),
             PixabayParams.COLORS.of(Color.RED),
             PixabayParams.ORIENTATION.of(Orientation.HORIZONTAL)
        )
        .execute()
        .getHits()
        .stream()
        .map(PixabayImage::getLargeImageURL)
        .forEach(System.out::println);
```

#### Callbacks 
You can receive callbacks in two ways:

* Catching exceptions
```java 
try {
    Pixabay.search().image().execute().getHits().forEach(System.out::println);
} catch (PixabayCallException | PixabayErrorException e) {
    // Handle error
} 
```
* With listener
```java 
Pixabay.search().image().execute(new PixabayCaller.Listener<PixabayResult<PixabayImage>>() {
    @Override
    public void onComplete(PixabayResult<PixabayImage> result, PixabayCaller<PixabayResult<PixabayImage>> apiCaller) {
        // Handle result
    }

    @Override
    public void onError(PixabayError error, PixabayCaller<PixabayResult<PixabayImage>> apiCaller) {
        /* This method triggers you when API has been called but response contains an error */
        // Handle Api Error
    }

    @Override
    public void onFailure(PixabayCallException e, PixabayCaller<PixabayResult<PixabayImage>> apiCaller) {
        /* This method triggers you when call to API cannot be established. E.g. no internet connection */
        // Handle Failure
    }
});
```
**Tip:** you can override not all callback methods

* Or by using RxJava Observables

```java 
Pixabay.rxSearch().image(PixabayParams.CATEGORY.of(Category.ANIMALS));
```

#### Requests cancelling
It goes without saying that you can also cancel request immediately if needs
```java 
caller.cancel();
```

## License

```
Copyright (c) 2018 BlizzedRu (Ivan Vlasov)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[doc]: https://pixabay.com/api/docs/