/*
The MIT License (MIT)

Copyright (c) 2016 Kadoshita

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package com.nitic.wifiauth.niticwifiauth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView AuthWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences userpref = getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences passpref = getSharedPreferences("Pass", Context.MODE_PRIVATE);

        String user = userpref.getString("User","user");
        String pass = passpref.getString("Pass","password");
        Log.i("debug","user:"+user+" pass:"+pass);
        if(user.equals("user") || pass.equals("password") || user.equals("") || pass.equals("")){
            Intent intent = new Intent();
            intent.setClassName("com.nitic.wifiauth.niticwifiauth", "com.nitic.wifiauth.niticwifiauth.SettingsActivity");
            startActivity(intent);
        }
        final String script = "javascript: document.getElementById('user').value = '" + user + "';"
                + "document.getElementById('pass').value = '" + pass + "';"
                + "document.getElementById('sb').click();";

        AuthWebView=(WebView)findViewById(R.id.webView);
        AuthWebView.getSettings().setJavaScriptEnabled(true);
        AuthWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                AuthWebView.loadUrl(script);
            }
        });

        AuthWebView.loadUrl("http://192.168.4.1/login.gsp");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0, "設定");
        menu.add(0,1,1, "再読み込み");
        menu.add(0,2,2, "終了");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Intent intent = new Intent();
                intent.setClassName("com.nitic.wifiauth.niticwifiauth", "com.nitic.wifiauth.niticwifiauth.SettingsActivity");
                startActivity(intent);
                return true;
            case 1:
                AuthWebView.reload();
                return true;
            case 2:
                finish();
                return true;
            default:
                return false;
        }
    }
}
