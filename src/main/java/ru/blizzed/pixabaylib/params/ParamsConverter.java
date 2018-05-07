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

import io.reactivex.Observable;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ParamsConverter {

    public static Map<String, String> asMap(Param... params) {
        if (params == null) return Collections.emptyMap();
        Map<String, String> map = new LinkedHashMap<>();
        Observable.fromArray(params).forEach(p -> map.put(p.name(), p.toString().toLowerCase()));
        return map;
    }

    public static Map<String, String> asMap(Param param, Param... params) {
        Map<String, String> map = asMap(new Param[]{param});
        map.putAll(asMap(params));
        return map;
    }

}
