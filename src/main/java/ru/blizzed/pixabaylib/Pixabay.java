/*
 * Copyright (c) 2018 BlizzedRu (Ivan Vlasov)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.blizzed.pixabaylib;

import com.google.gson.*;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.blizzed.pixabaylib.methods.RxSearchCaller;
import ru.blizzed.pixabaylib.methods.RxSearcher;
import ru.blizzed.pixabaylib.methods.SearchCaller;
import ru.blizzed.pixabaylib.methods.Searcher;
import ru.blizzed.pixabaylib.params.LangParam;
import ru.blizzed.pixabaylib.params.PixabayParams;

import java.lang.reflect.Type;

public final class Pixabay {

    private static final String ROOT_URL = "https://pixabay.com/api/";

    private static volatile Pixabay instance;

    private String apiKey;
    private LangParam.Lang lang;

    private Retrofit retrofit;

    private Searcher searcher;
    private RxSearcher rxSearcher;

    private Pixabay(String apiKey) {
        this.apiKey = apiKey;
        initRetrofit();
    }

    private Pixabay(String apiKey, LangParam.Lang lang) {
        this.apiKey = apiKey;
        this.lang = lang;
        initRetrofit();
    }

    private void initRetrofit() {
        // Adding an API key and lang param (if present) to all requests
        if (lang == null) lang = LangParam.Lang.EN;

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor((chain -> {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder()
                            .addQueryParameter(PixabayParams.KEY.name(), apiKey)
                            .addQueryParameter(PixabayParams.LANG.name(), PixabayParams.LANG.of(lang).name())
                            .build();
                    request = request.newBuilder().url(url).build();
                    return chain.proceed(request);
                }))
                .build();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ROOT_URL)
                .client(httpClient)
                .build();
    }

    public static void initialize(String apiKey) {
        init(apiKey, null);
    }

    public static void initialize(String apiKey, LangParam.Lang lang) {
        init(apiKey, lang);
    }

    private static void init(String apiKey, LangParam.Lang lang) {
        if (instance == null) {
            synchronized (Pixabay.class) {
                if (lang == null) instance = new Pixabay(apiKey);
                else instance = new Pixabay(apiKey, lang);
            }
        }
    }

    public static Searcher search() {
        if (getInstance().searcher == null)
            instance.searcher = new Searcher(instance.retrofit.create(SearchCaller.class));
        return instance.searcher;
    }

    public static RxSearcher rxSearch() {
        if (getInstance().rxSearcher == null)
            instance.rxSearcher = new RxSearcher(instance.retrofit.create(RxSearchCaller.class));
        return instance.rxSearcher;
    }

    private static Pixabay getInstance() {
        checkInit();
        return instance;
    }

    /**
     * Checks if you (or someone else) initialized Pixabay
     *
     * @return true if Pixabay is initialized
     */
    public static boolean isInitialized() {
        return instance != null;
    }

    private static void checkInit() {
        if (!isInitialized()) throw new RuntimeException("Pixabay must be initialized first.");
    }

}
