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

package ru.blizzed.pixabaylib.params;

public final class PixabayParams {

    public static final Param<String> KEY = new Param<>("key");
    public static final LangParam LANG = new LangParam();
    public static final ImageTypeParam IMAGE_TYPE = new ImageTypeParam();
    public static final OrientationParam ORIENTATION = new OrientationParam();
    public static final CategoryParam CATEGORY = new CategoryParam();
    public static final Param<Integer> MIN_WIDTH = new Param<>("min_width");
    public static final Param<Integer> MIN_HEIGHT = new Param<>("min_height");
    public static final ColorsParam COLORS = new ColorsParam();
    public static final BooleanParam EDITORS_CHOICE = new BooleanParam("editors_choice");
    public static final BooleanParam SAFE_SEARCH = new BooleanParam("safesearch");
    public static final OrderParam ORDER = new OrderParam();
    public static final Param<Integer> PAGE = new Param<>("page");
    public static final Param<Integer> PER_PAGE = new Param<>("per_page");
    public static final BooleanParam PRETTY = new BooleanParam("pretty");

}
