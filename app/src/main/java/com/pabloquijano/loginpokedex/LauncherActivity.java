package com.pabloquijano.loginpokedex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("PKXPref", 0);
        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if (pref.getString("pokedex_token", null)==null){
                            startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
                            finish();
                        }else{
                            startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                            finish();
                        }

                    }
                }, 1100);

    }
}
