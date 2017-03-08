package com.asyraf.frilo.data;

import android.app.Application;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Asyraf Duyshart on 2/23/17.
 */

@Singleton
public class FirebaseManager {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Inject
    public FirebaseManager() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public Task<AuthResult> createUserWithEmail(String email, String password) {
        return firebaseAuth.createUserWithEmailAndPassword(email, password);
    }

    public Task<Void> verifiedEmail(){
        return firebaseUser.sendEmailVerification();
    }

    public boolean checkUser(){
        return firebaseUser!=null;
    }

}
