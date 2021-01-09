package com.smallacademy.userroles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class add_station extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    boolean valid = true;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station);
        fStore = FirebaseFirestore.getInstance();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        final EditText email = findViewById(R.id.editTextEmail);
        final EditText uname = findViewById(R.id.editTextUnName);
        final EditText address = findViewById(R.id.editTextAddress);
        final EditText slots = findViewById(R.id.editTextSlots);
        final EditText phone = findViewById(R.id.editTextPhone);
        final EditText cost = findViewById(R.id.editTextCost);
        final EditText compat = findViewById(R.id.editTextCompat);
        final EditText info = findViewById(R.id.editTextInfo);
        Button submit = findViewById(R.id.button3);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_add);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validem(email);
                checkField(uname);
                validloc(address);
                checkField(slots);
                checkField(phone);
                checkField(cost);
                checkField(compat);

                if(valid) {

                    FirebaseUser user = fAuth.getCurrentUser();
                    Toast.makeText(add_station.this, "Charging station is added", Toast.LENGTH_SHORT).show();
                    DocumentReference df = fStore.collection("Charge_station").document(user.getUid());
                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("Email", email.getText().toString());
                    userInfo.put("Unique name", uname.getText().toString());
                    userInfo.put("Address", address.getText().toString());
                    userInfo.put("Charging Slots", slots.getText().toString());
                    userInfo.put("Phone", phone.getText().toString());
                    userInfo.put("Cost", cost.getText().toString());
                    userInfo.put("Charging point compatibilty", compat.getText().toString());
                    userInfo.put("Additional Information", info.getText().toString());

                    df.set(userInfo);

                    startActivity(new Intent(getApplicationContext(), profile.class));
                    finish();
                }



            }
        });


    }

    private void validem(EditText email) {

    }

    private void validloc(EditText address) {

    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
    @Override
    public void onBackPressed(){

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                Intent intent = new Intent(add_station.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                Intent intent1 = new Intent(add_station.this,profile.class);
                startActivity(intent1);
                break;
            case R.id.nav_add:
                break;
            case  R.id.nav_Logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}