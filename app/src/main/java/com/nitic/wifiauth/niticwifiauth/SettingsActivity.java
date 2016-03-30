package com.nitic.wifiauth.niticwifiauth;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    private EditText userText,passText;
    private Button set;
    private String user,pass;

    private SharedPreferences userpref,passpref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userpref = getSharedPreferences("User", Context.MODE_PRIVATE);
        passpref = getSharedPreferences("Pass", Context.MODE_PRIVATE);

        userText=(EditText)findViewById(R.id.editText);
        passText=(EditText)findViewById(R.id.editText2);
        set=(Button)findViewById(R.id.button);

        user=userpref.getString("User","user");
        pass=passpref.getString("Pass","password");
        userText.setText(user);
        passText.setText(pass);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=String.valueOf(userText.getText());
                pass=String.valueOf(passText.getText());
                userpref.edit().putString("User",user).commit();
                passpref.edit().putString("Pass",pass).commit();
                finish();
            }
        });
    }
}
