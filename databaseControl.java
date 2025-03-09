package com.example.myapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class databaseControl {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> email_adress = new ArrayList<String>();
    ArrayList<String> wordsDataDB = new ArrayList<String>();


    public void addData(String email,String en_word, String synonyms,String tr_word, String tr_word2, String ex_sentence,String ex_sentence_tr,String photo_adress, String voice_adress,String word_type,String word_level,String dates)
    {
        Map<String, Object> user = new HashMap<>();
        user.put( "A"+"en_word", en_word);user.put("B"+"tr_word", tr_word);user.put("C"+"tr_word2", tr_word2);user.put("D"+"synonyms", synonyms);user.put("E"+"voice_ad", voice_adress);
        user.put("F"+"photo_ad", photo_adress);user.put("G"+"exa_sent", ex_sentence);user.put("X"+"word_typ", word_type);
        user.put("K"+ "exa_sent_tr", ex_sentence_tr);user.put("L" + "date", dates);user.put("M" + "wordLvl",word_level);

        db.collection("omaronaltrs@gmail.com")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }


    public void addUser(String email,String password,String username){
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);
        user.put("username", username);

        db.collection("email_collection")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
    public void changedPasword(String password,String id,String email, String username){
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);
        user.put("username", username);

        db.collection("email_collection")
                .document(id)  // <== Specify the Doc ID here
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }


    public ArrayList<String> getUser(){

        db.collection("email_collection")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String dataDB = document.getData().toString() + "," + document.getId().toString();
                                //Log.e(TAG, document.getId() + " => " + document.getData());
                                email_adress.add(dataDB);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents.", task.getException());
                        }
                    }

                });
        return email_adress;
    }

    public ArrayList<String> getData() {
        db.collection("omaronaltrs@gmail.com")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String dataDB = document.getData().toString();
                                wordsDataDB.add(dataDB);
                                //System.out.println( document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("mesaj1>>>>>>",Integer.toString(wordsDataDB.size()));}
        }, 1000);
        return wordsDataDB;
    }


}
