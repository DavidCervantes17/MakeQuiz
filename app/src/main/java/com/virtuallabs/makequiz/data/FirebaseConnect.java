package com.virtuallabs.makequiz.data;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseConnect {
    public static FirebaseFirestore connect (){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db;
    }

}
