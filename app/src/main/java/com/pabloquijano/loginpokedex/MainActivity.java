package com.pabloquijano.loginpokedex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.pabloquijano.loginpokedex.adapters.PokemonsAdapter;
import com.pabloquijano.loginpokedex.models.Pokemon_response;
import com.pabloquijano.loginpokedex.utils.ApiClient;
import com.pabloquijano.loginpokedex.utils.ApiInterface;
import com.pabloquijano.loginpokedex.utils.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvItems)
    RecyclerView rvItems;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;
    MaterialDialog progressDialog;
    private ApiClient apiClient;
    private PokemonsAdapter pokemonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("POKEDEX");
        ButterKnife.bind(this);
        rvItems.setHasFixedSize(true);
        //Shows Progress View
        progressDialog = Singleton.getInstance(getApplicationContext()).getProgressDialog(getString(R.string.loading_data), MainActivity.this);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Runs refresh the list.
                rvItems.setAlpha(0.3f);
                rvItems.setClickable(false);
                loadList(false);
            }
        });
        apiClient = new ApiClient(this);
        //Calls the api to get a pokemon list
        loadList(true);


    }

    private void loadList(final boolean firstTime){
        ApiInterface apiService = apiClient.getClient().create(ApiInterface.class);
        Call<Pokemon_response> call = apiService.getListPokemon();
        call.enqueue(new Callback<Pokemon_response>() {

            @Override
            public void onResponse(Call<Pokemon_response> call, Response<Pokemon_response> response) {
                Log.e("list", ""+response.toString());
                if (response.code()==200){
                    if (firstTime || pokemonsAdapter==null){
                        //Creates the recycler view adapter
                        pokemonsAdapter = new PokemonsAdapter(response.body().getResults(), MainActivity.this);
                        //Sets adapter to the recycler view
                        rvItems.setAdapter(pokemonsAdapter);
                        rvItems.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL,false));
                        if (swipeContainer.isRefreshing()){
                            //Reset the swipe refresh status.
                            swipeContainer.setRefreshing(false);
                            rvItems.setAlpha(1f);
                            rvItems.setClickable(true);
                        }else{
                            //Hides Progress View
                            progressDialog.dismiss();
                        }

                    }else{
                        //Reload the poemon list
                        pokemonsAdapter.clear();
                        pokemonsAdapter.addAll(response.body().getResults());
                        //Reset the swipe refresh status.
                        swipeContainer.setRefreshing(false);
                        rvItems.setAlpha(1f);
                        rvItems.setClickable(true);
                    }

                }else{
                    if (firstTime){
                        Singleton.getInstance(MainActivity.this).getErrorDialog(response.message(), MainActivity.this);
                        //Hides Progress View
                        progressDialog.dismiss();
                    }else{
                        Singleton.getInstance(MainActivity.this).getErrorDialog(response.message(), MainActivity.this);
                        //Reset the swipe refresh status.
                        swipeContainer.setRefreshing(false);
                        rvItems.setAlpha(1f);
                        rvItems.setClickable(true);
                    }

                }


            }

            @Override
            public void onFailure(Call<Pokemon_response> call, Throwable t) {
                Log.e("error", "err: "+call.toString());
                if (firstTime){
                    Singleton.getInstance(MainActivity.this).getErrorDialog(t.getMessage(), MainActivity.this);
                    progressDialog.dismiss();
                }else{
                    Singleton.getInstance(MainActivity.this).getErrorDialog(t.getMessage(), MainActivity.this);
                    swipeContainer.setRefreshing(false);
                    rvItems.setAlpha(1f);
                    rvItems.setClickable(true);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflates menu to show the log out button.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                Singleton.getInstance(MainActivity.this).getQuestionDialog(getString(R.string.logout), getString(R.string.logout_question), MainActivity.this)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("PKXPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();
                                editor.remove("pokedex_token"); // will delete key email
                                editor.apply();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                dialog.dismiss();
                                finish();
                            }
                        })
                        .show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
