package com.github.MakMoinee.library.models;

import java.util.Map;

import lombok.Data;

@Data
public class FirestoreRequestBody {
    private String collectionName;
    private String email;
    private String documentID;
    private String whereFromField;
    private Object whereValueField;
    private Map<String, Object> params;

    public FirestoreRequestBody() {
    }

    public FirestoreRequestBody(FirestoreRequestBodyBuilder builder) {
        this.collectionName = builder.collectionName;
        this.email = builder.collectionName;
        this.documentID = builder.documentID;
        this.whereFromField = builder.whereFromField;
        this.whereValueField = builder.whereValueField;
        this.params = builder.params;
    }


    public static class FirestoreRequestBodyBuilder {
        private String collectionName;
        private String email;
        private String documentID;
        private String whereFromField;
        private Object whereValueField;

        private Map<String, Object> params;

        public FirestoreRequestBodyBuilder() {
        }

        public FirestoreRequestBodyBuilder setCollectionName(String collectionName) {
            this.collectionName = collectionName;
            return this;
        }

        public FirestoreRequestBodyBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public FirestoreRequestBodyBuilder setDocumentID(String documentID) {
            this.documentID = documentID;
            return this;
        }

        public FirestoreRequestBodyBuilder setWhereFromField(String whereFromField) {
            this.whereFromField = whereFromField;
            return this;
        }

        public FirestoreRequestBodyBuilder setWhereValueField(Object whereValueField) {
            this.whereValueField = whereValueField;
            return this;
        }

        public FirestoreRequestBodyBuilder setParams(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public FirestoreRequestBody build() {
            return new FirestoreRequestBody(this);
        }
    }
}
