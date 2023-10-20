package com.github.MakMoinee.library.services;

import com.github.MakMoinee.library.interfaces.FirestoreListener;
import com.github.MakMoinee.library.models.FirestoreRequestBody;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

public class FirestoreRequest {
    public static final String USERS_COLLECTION = "users";
    public static final String EMAIL_STRING = "email";

    FirebaseFirestore fs;

    public FirestoreRequest() {
        fs = FirebaseFirestore.getInstance();
    }

    /**
     * @param body     - pass the FirebaseRequestBody
     * @param listener - handle results using FirebaseListener
     */
    public void findAll(FirestoreRequestBody body, FirestoreListener listener) {
        CollectionReference collectionReference = fs.collection(body.getCollectionName());
        Query query = null;
        if (body.getWhereFromField() != null && body.getWhereValueField() != null) {
            query = collectionReference.whereEqualTo(body.getWhereFromField(), body.getWhereValueField());
        } else {
            if (body.getDocumentID() != null) {
                collectionReference.document(body.getDocumentID())
                        .get()
                        .addOnSuccessListener(documentSnapshot -> {
                            if (documentSnapshot.exists()) {
                                listener.onSuccess(documentSnapshot);
                            } else {
                                listener.onError(new Error("empty result"));
                            }
                        })
                        .addOnFailureListener(e -> {
                            try {
                                listener.onError(new Error(e.getMessage()));
                            } catch (Exception e1) {
                                listener.onError(null);
                            }
                        });
            }
        }


        if (query != null) {
            query.get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (queryDocumentSnapshots.isEmpty()) {
                            listener.onError(new Error("empty result"));
                        } else {
                            listener.onSuccess(queryDocumentSnapshots);
                        }
                    })
                    .addOnFailureListener(e -> {
                        try {
                            listener.onError(new Error(e.getMessage()));
                        } catch (Exception e1) {
                            listener.onError(null);
                        }
                    });
        } else {
            collectionReference.get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (queryDocumentSnapshots.isEmpty()) {
                            listener.onError(new Error("empty result"));
                        } else {
                            listener.onSuccess(queryDocumentSnapshots);
                        }
                    })
                    .addOnFailureListener(e -> {
                        try {
                            listener.onError(new Error(e.getMessage()));
                        } catch (Exception e1) {
                            listener.onError(null);
                        }
                    });
        }
    }

    /**
     * @param body
     * @param listener
     */
    public void insertUniqueData(FirestoreRequestBody body, FirestoreListener listener) {
        this.findAll(body, new FirestoreListener() {
            @Override
            public <T> void onSuccess(T any) {
                //existent data
                listener.onError(new Error("data exist"));
            }

            @Override
            public void onError(Error error) {
                String id = fs.collection(body.getCollectionName())
                        .document().getId();
                fs.collection(body.getCollectionName())
                        .document(id)
                        .set(body.getParams())
                        .addOnSuccessListener(unused -> listener.onSuccess(id))
                        .addOnFailureListener(e -> {
                            try {
                                listener.onError(new Error(e.getMessage()));
                            } catch (Exception e1) {
                                listener.onError(null);
                            }
                        });
            }
        });

    }

    /**
     * @param body     - build your FirebaseRequestBody
     * @param listener - Returns null on listener.onSuccess() if success,
     *                 otherwise it will return listener.onError(error) passing the error
     */
    public void upsert(FirestoreRequestBody body, FirestoreListener listener) {
        //check for required fields
        if (body.getCollectionName() == null) {
            listener.onError(new Error("collection name is required"));
            return;
        }

        if (body.getDocumentID() == null) {
            listener.onError(new Error("documentID is required"));
            return;
        }

        if (body.getParams() == null) {
            listener.onError(new Error("params is required"));
            return;
        }

        fs.collection(body.getCollectionName())
                .document(body.getDocumentID())
                .set(body.getParams(), SetOptions.merge())
                .addOnSuccessListener(unused -> listener.onSuccess(null))
                .addOnFailureListener(e -> {
                    try {
                        listener.onError(new Error(e.getMessage()));
                    } catch (Exception e1) {
                        listener.onError(null);
                    }
                });

    }

    /**
     * @param body     - build your FirebaseRequestBody
     * @param listener - Returns null on listener.onSuccess() if success,
     *                 otherwise it will return listener.onError(error) passing the error
     */
    private void delete(FirestoreRequestBody body, FirestoreListener listener) {
        //check for required fields
        if (body.getCollectionName() == null) {
            listener.onError(new Error("collection name is required"));
            return;
        }

        //check for required fields
        if (body.getDocumentID() == null) {
            listener.onError(new Error("documentID is required"));
            return;
        }

        fs.collection(body.getCollectionName())
                .document(body.getDocumentID())
                .delete()
                .addOnSuccessListener(unused -> listener.onSuccess(null))
                .addOnFailureListener(e -> {
                    try {
                        listener.onError(new Error(e.getMessage()));
                    } catch (Exception e1) {
                        listener.onError(null);
                    }
                });
    }
}
