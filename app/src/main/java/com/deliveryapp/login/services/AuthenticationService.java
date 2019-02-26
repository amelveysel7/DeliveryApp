package com.deliveryapp.login.services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

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
}
