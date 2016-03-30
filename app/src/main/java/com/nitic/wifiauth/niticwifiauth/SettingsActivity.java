/*
The MIT License (MIT)

Copyright (c) 2016 Kadoshita

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
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
