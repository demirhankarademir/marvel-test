package com.theappbusiness.marvel.network;

/**
 * Created by demir on 5.04.2017.
 */

import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class AuthInterceptor implements Interceptor {
    private static final String TIMESTAMP_KEY = "ts";
    private static final String HASH_KEY = "hash";
    private static final String APIKEY_KEY = "apikey";

    private final String publicKey;
    private final String privateKey;
    private final AuthHashGenerator authHashGenerator = new AuthHashGenerator();

    AuthInterceptor(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash = null;
            hash = authHashGenerator.generateHash(timestamp, publicKey, privateKey);

        Request request = chain.request();
        HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter(TIMESTAMP_KEY, timestamp)
                .addQueryParameter(APIKEY_KEY, publicKey)
                .addQueryParameter(HASH_KEY, hash)
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
