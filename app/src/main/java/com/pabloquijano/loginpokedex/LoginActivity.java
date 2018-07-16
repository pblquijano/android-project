package com.pabloquijano.loginpokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        if (inputEmail.getText().toString().isEmpty() || inputPassword.getText().toString().isEmpty()){

        }else{
            User user = new User(inputEmail.getText().toString(), inputPassword.getText().toString());
            apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<User> call = apiService.signUp(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }


            });
        }

    }
}
