package com.pabloquijano.loginpokedex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pabloquijano.loginpokedex.models.User;
import com.pabloquijano.loginpokedex.utils.ApiClient;
import com.pabloquijano.loginpokedex.utils.ApiInterface;
import com.pabloquijano.loginpokedex.utils.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ApiInterface apiService;
    MaterialDialog progressDialog;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        title.setTypeface(Singleton.getInstance(this).getFontSBold());
    }

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked(final View view) {
        String textError = "";
        boolean isValid = true;
        if (inputEmail.getText().toString().isEmpty() ){
            textError += getString(R.string.email_field_required)+".\n";
            isValid = false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString()).matches()){
            textError += getString(R.string.invalid_email)+".\n";
            isValid = false;
        }
        if (inputPassword.getText().toString().isEmpty()){
            textError += getString(R.string.password_field_required)+".\n";
            isValid = false;
        }else if(inputPassword.getText().toString().length()<8){
            textError += getString(R.string.error_password_length)+".\n";
            isValid = false;
        }

        if (!isValid) {
            Singleton.getInstance(LoginActivity.this).getErrorDialog(textError, view.getContext());
        } else {
            progressDialog = Singleton.getInstance(getApplicationContext()).getProgressDialog(getString(R.string.logging_in), view.getContext());
            User user = new User(inputEmail.getText().toString(), inputPassword.getText().toString());
            apiService = ApiClient.getClient().create(ApiInterface.class);
            SharedPreferences pref = getApplicationContext().getSharedPreferences("PKXPref", 0); // 0 - for private mode
            final SharedPreferences.Editor editor = pref.edit();
            Call<User> call = apiService.signUp(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    editor.putString("pokedex_token",inputEmail.getText().toString());
                    editor.apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    progressDialog.dismiss();
                    finish();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    editor.putString("pokedex_token",inputEmail.getText().toString());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    progressDialog.dismiss();
                    finish();
                }


            });
        }

    }
}
