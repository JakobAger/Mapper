package com.example.mappertest;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




public class LoginFragment extends Fragment {

    Button btnLogin,btnRegister;
    EditText etEmail,etPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);

        btnLogin = view.findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    Toast toast = Toast.makeText(getContext(), "Please enter your email and password", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if(user.isEmailVerified()){

                            Toast.makeText(getContext(), "Login Successful All Data will be saved", Toast.LENGTH_LONG).show();

                        }
                    }
                    else {
                        Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



        return view;
    }




}