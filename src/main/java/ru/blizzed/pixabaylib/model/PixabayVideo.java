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
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.internal.Nullable;

public class PixabayVideo implements Serializable {

    public enum VideoSize {
        LARGE, MEDIUM, SMALL, TINY;

        static VideoSize getByName(String name) {
            return valueOf(name.toUpperCase());
        }
    }

    private final static long serialVersionUID = 666445498641390494L;

    private int id;

    private String pageURL;

    private String type;

    private String tags;

    private int duration;

    @SerializedName("picture_id")
    private String pictureId;

    private Videos videos;

    private int views;

    private int downloads;

    private int favorites;

    private int likes;

    private int comments;

    @SerializedName("user_id")
    private long userId;

    private String user;

    private String userImageURL;

    public int getId() {
        return id;
    }

    public String getPageURL() {
        return pageURL;
    }

    public String getType() {
        return type;
    }

    public String getTags() {
        return tags;
    }

    public int getDuration() {
        return duration;
    }

    public String getPictureId() {
        return pictureId;
    }

    public Videos getVideos() {
        return videos;
    }

    public int getViews() {
        return views;
    }

    public int getDownloads() {
        return downloads;
    }

    public int getFavorites() {
        return favorites;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
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

    @JsonAdapter(Videos.Deserializer.class)
    public static class Videos implements Serializable {

        Map<VideoSize, Video> videoMap;

        Videos() {
            videoMap = new HashMap<>();
        }

        @Nullable
        public Video get(VideoSize size) {
            if (videoMap.containsKey(size))
                return videoMap.get(size);
            return null;
        }

        private static class Deserializer implements JsonDeserializer<Videos> {
            @Override
            public Videos deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                Videos videos = new Videos();
                JsonObject videosObject = jsonElement.getAsJsonObject();

                Arrays.stream(VideoSize.values())
                        .map(Enum::name)
                        .map(String::toLowerCase)
                        .forEach(size -> {
                            if (videosObject.has(size)) {
                                videos.videoMap.put(VideoSize.getByName(size), new Video(videosObject.getAsJsonObject(size)));
                            }
                        });

                return videos;
            }

        }

    }

    public static class Video implements Serializable {

        private final static long serialVersionUID = 4008774849141743745L;

        private String url;
        private int width;
        private int height;
        private long size;

        Video(JsonObject jVideo) {
            url = jVideo.getAsJsonPrimitive("url").getAsString();
            width = jVideo.getAsJsonPrimitive("width").getAsInt();
            height = jVideo.getAsJsonPrimitive("height").getAsInt();
            size = jVideo.getAsJsonPrimitive("size").getAsLong();
        }

        public String getUrl() {
            return url;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public long getSize() {
            return size;
        }

    }


}