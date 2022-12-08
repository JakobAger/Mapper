package com.example.mappertest;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegisterFragment extends Fragment {

    Button btnLogin,btnRegister;
    EditText etFirstName,etPassword,etEmail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        etFirstName = view.findViewById(R.id.etFirstName);
        etPassword = view.findViewById(R.id.etPassword);
        etEmail = view.findViewById(R.id.etEmail);


        btnRegister = view.findViewById(R.id.btnRegister);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String firstName = etFirstName.getText().toString();

                if(userName.isEmpty() || password.isEmpty() || firstName.isEmpty()){
                    Toast toast = Toast.makeText(getContext(), "Please enter your firstname , email and password", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(userName,password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String uid = user.getUid();

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("id",uid);
                        hashMap.put("username",userName);
                        hashMap.put("firstName",firstName);
                        hashMap.put("imageURL","default");

                        reference.setValue(hashMap).addOnCompleteListener(task1 -> {
                            if(task1.isSuccessful()){
//                                Print Thanks for registering
                                Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(getContext(), "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });

        return view;
    }
}