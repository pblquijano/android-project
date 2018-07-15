package com.pabloquijano.loginpokedex;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
                        finish();
                    }
                }, 1100);

    }
}
