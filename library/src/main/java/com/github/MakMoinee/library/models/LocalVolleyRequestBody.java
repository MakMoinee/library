package com.github.MakMoinee.library.models;

import org.json.JSONObject;

import lombok.Data;

@Data
public class LocalVolleyRequestBody {
    private String url;
    private JSONObject body;

    public LocalVolleyRequestBody() {

    }

    public LocalVolleyRequestBody(LocalVolleyRequestBodyBuilder builder) {
        this.url = builder.url;
        this.body = builder.body;
    }

    public static class LocalVolleyRequestBodyBuilder {
        private String url;
        private JSONObject body;

        public LocalVolleyRequestBodyBuilder() {
        }

        public LocalVolleyRequestBodyBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public LocalVolleyRequestBodyBuilder setBody(JSONObject body) {
            this.body = body;
            return this;
        }

        public LocalVolleyRequestBody build() {
            return new LocalVolleyRequestBody(this);
        }
    }
}
