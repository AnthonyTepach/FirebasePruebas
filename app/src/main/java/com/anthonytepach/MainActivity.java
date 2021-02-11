package com.anthonytepach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        database();
    }



    private void actualizarLayut(FirebaseUser currentUser) {
       String email = currentUser.getEmail();
    }

    private void iniciaSesion(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Correcto:", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            actualizarLayut(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Incorrecto", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            actualizarLayut(null);
                        }
                    }
                });
    }

    private void database(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        System.out.println("hola mundo");

        for (int i = 1; i<=9999999;i++){
            DatabaseReference myRef = database.getReference("message"+i);
            myRef.setValue("Hello, World! "+i);
        }
    }
}
