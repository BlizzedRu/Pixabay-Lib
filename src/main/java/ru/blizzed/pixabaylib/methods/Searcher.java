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

package ru.blizzed.pixabaylib.methods;

import ru.blizzed.pixabaylib.PixabayCaller;
import ru.blizzed.pixabaylib.model.PixabayImage;
import ru.blizzed.pixabaylib.model.PixabayResult;
import ru.blizzed.pixabaylib.model.PixabayVideo;
import ru.blizzed.pixabaylib.params.Param;
import ru.blizzed.pixabaylib.params.ParamsConverter;

public class Searcher {

    private SearchCaller searchCaller;

    public Searcher(SearchCaller searchCaller) {
        this.searchCaller = searchCaller;
    }

    public PixabayCaller<PixabayResult<PixabayImage>> image(Param... params) {
        return new PixabayCaller<>(searchCaller.image(ParamsConverter.asMap(params)));
    }

    public PixabayCaller<PixabayResult<PixabayVideo>> video(Param... params) {
        return new PixabayCaller<>(searchCaller.video(ParamsConverter.asMap(params)));
    }

}
