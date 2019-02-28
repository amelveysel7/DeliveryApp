package com.deliveryapp.login.services;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthenticationService {
    private static final String TAG = AuthenticationService.class.getSimpleName();
    private static final AuthenticationService ourInstance = new AuthenticationService();
    private FirebaseAuth mAuth;



    public static AuthenticationService getInstance() {
        return ourInstance;
    }

    private AuthenticationService() {
        mAuth = FirebaseAuth.getInstance();
    }
    public void register (String email, String password, final AuthenticationListener authenticationListener){
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                authenticationListener.onSuccess(null);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                authenticationListener.onFailure(e.getMessage());
            }
        });
    }
    public void login(String email, String password, final AuthenticationListener authenticationListener ){
        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                //TODO: Get user from database
                authenticationListener.onSuccess(null);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                authenticationListener.onFailure(e.getMessage());
            }
        });
    }
}
