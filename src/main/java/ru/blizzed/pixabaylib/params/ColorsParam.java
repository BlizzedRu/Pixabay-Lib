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

public class ColorsParam extends EnumParam<ColorsParam.Color> {

    public enum Color {
        GRAY_SCALE, TRANSPARENT, RED, ORANGE, YELLOW, GREEN,
        TURQUOISE, BLUE, LILAC, PINK, WHITE, GRAY, BLACK, BROWN
    }

    public ColorsParam() {
        super("colors");
    }
}
