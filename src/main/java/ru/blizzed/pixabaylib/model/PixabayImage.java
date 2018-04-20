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

package ru.blizzed.pixabaylib.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PixabayImage implements Serializable {

    private final static long serialVersionUID = 7938472977259493620L;

    private long id;

    private String pageURL;

    private String type;

    private String tags;

    private String previewURL;

    private long previewWidth;

    private long previewHeight;

    private String webformatURL;

    private int webformatWidth;

    private int webformatHeight;

    private String largeImageURL;

    private String fullHDURL;

    private String imageURL;

    private int imageWidth;

    private int imageHeight;

    private int imageSize;

    private long views;

    private long downloads;

    private long favorites;

    private long likes;

    private long comments;

    @SerializedName("user_id")
    private long userId;

    private String user;

    private String userImageURL;

    public long getId() {
        return id;
    }

    public String getPageURL() {
        return pageURL;
    }

    public String getType() {
        return type;
    }

    public List<String> getTags() {
        return Arrays.asList(tags.split(", "));
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public long getPreviewWidth() {
        return previewWidth;
    }

    public long getPreviewHeight() {
        return previewHeight;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public long getWebformatWidth() {
        return webformatWidth;
    }

    public long getWebformatHeight() {
        return webformatHeight;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public String getFullHDURL() {
        return fullHDURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public long getImageWidth() {
        return imageWidth;
    }

    public long getImageHeight() {
        return imageHeight;
    }

    public long getImageSize() {
        return imageSize;
    }

    public long getViews() {
        return views;
    }

    public long getDownloads() {
        return downloads;
    }

    public long getFavorites() {
        return favorites;
    }

    public long getLikes() {
        return likes;
    }

    public long getComments() {
        return comments;
    }

    public long getUserId() {
        return userId;
    }

    public String getUser() {
        return user;
    }

    public String getUserImageURL() {
        return userImageURL;
    }
    
}
