package com.github.MakMoinee.library.models;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RealtimeDBBody {

    private String referenceName;
    private String childName;
    private String key;
    private Map<String, Object> params;

    public RealtimeDBBody(RealtimeDbBodyBuilder builder) {
        this.childName = builder.childName;
        this.key = builder.key;
        this.params = builder.params;
        this.referenceName = builder.referenceName;
    }

    public static class RealtimeDbBodyBuilder {

        private String referenceName;
        private String childName;
        private String key;
        private Map<String, Object> params;

        public RealtimeDbBodyBuilder setReferenceName(String referenceName) {
            this.referenceName = referenceName;
            return this;
        }

        public RealtimeDbBodyBuilder setChildName(String childName) {
            this.childName = childName;
            return this;
        }

        public RealtimeDbBodyBuilder setKey(String key) {
            this.key = key;
            return this;
        }

        public RealtimeDbBodyBuilder setParams(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public RealtimeDBBody build() {
            return new RealtimeDBBody(this);
        }


    }
}
