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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.blizzed.pixabaylib.model.PixabayError;

import java.io.IOException;

public final class PixabayCaller<ResultType> {

    public interface Listener<ResultType> {
        default void onComplete(ResultType result, PixabayCaller<ResultType> apiCaller) {
            // Handle success result
        }
        default void onError(PixabayError error, PixabayCaller<ResultType> apiCaller) {
            /* This method triggers you when API has been called but response contains an error */
            // Handle Api Error
        }
        default void onFailure(PixabayCallException e, PixabayCaller<ResultType> apiCaller) {
            /* This method triggers you when call to API cannot be established E.g. no internet connection */
            // Handle Failure
        }
    }

    private Call<ResultType> call;

    public PixabayCaller(Call<ResultType> call) {
        this.call = call;
    }

    public ResultType execute() throws PixabayCallException, PixabayErrorException {
        try {
            System.out.println(call.request().toString());
            Response<ResultType> response = call.execute();
            if (response.isSuccessful())
                return response.body();
            else throw new PixabayErrorException(parseError(response));
        } catch (IOException e) {
            throw new PixabayCallException(e);
        }
    }

    public void execute(Listener<ResultType> listener) {
        call.enqueue(new Callback<ResultType>() {
            @Override
            public void onResponse(Call<ResultType> call, Response<ResultType> response) {
                if (response.isSuccessful()) listener.onComplete(response.body(), PixabayCaller.this);
                else  {
                    try {
                        listener.onError(parseError(response), PixabayCaller.this);
                    } catch (IOException e) {
                        listener.onFailure(new PixabayCallException(e), PixabayCaller.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultType> call, Throwable throwable) {
                listener.onFailure(new PixabayCallException(throwable), PixabayCaller.this);
            }
        });
    }

    public void cancel() {
        if (!call.isCanceled() & !call.isExecuted())
            call.cancel();
    }

    private PixabayError parseError(Response response) throws IOException {
        ResponseBody body = response.errorBody();
        return new PixabayError(body == null ? "unknown" : body.string());
    }

}
