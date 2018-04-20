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

public class LangParam extends EnumParam<LangParam.Lang> {

    public enum Lang {
        CS, DA, DE, EN, ES, FR, ID, IT, HU, NL, NO, PL, PT, RO, SK, FI, SV, TR, VI, TH, BG, RU, EL, JA, KO, ZH
    }

    public LangParam() {
        super("lang");
    }
}
