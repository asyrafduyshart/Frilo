package com.asyraf.frilo.data.remote;

import android.app.Application;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Asyraf Duyshart on 2/22/17.
 */

public class FirebaseUserService {

    private Application application;
    private FirebaseAuth firebaseAuth;

    public FirebaseUserService(Application application) {
        this.application = application;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> createUserWithEmail(String email, String password) {
        return firebaseAuth.createUserWithEmailAndPassword(email, password);
    }
}
