package sv.edu.udb.guia6ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

import sv.edu.udb.guia6ejercicio1.models.GitUser;
import sv.edu.udb.guia6ejercicio1.models.GitUsersResponse;
import sv.edu.udb.guia6ejercicio1.models.UsersListViewModel;
import sv.edu.udb.guia6ejercicio1.service.GitRepoServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<GitUser> data = new ArrayList<>();
    public static final String USERNAME_PARAM = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button searchButton = findViewById(R.id.buttonSearch);
        final EditText editTextUser = findViewById(R.id.editTextUser);
        ListView listViewUsers = findViewById(R.id.listViewUsers);

        // final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        UsersListViewModel listViewModel = new UsersListViewModel(this, R.layout.users_list_view_layout, data);
        listViewUsers.setAdapter(listViewModel);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = editTextUser.getText().toString();
                GitRepoServiceAPI service = retrofit.create(GitRepoServiceAPI.class);
                Call<GitUsersResponse> gitUsersResponseCall = service.searchUsers(query);
                gitUsersResponseCall.enqueue(
                        new Callback<GitUsersResponse>() {
                            @Override
                            public void onResponse(Call<GitUsersResponse> call, Response<GitUsersResponse> response) {
                                if(!response.isSuccessful()){
                                    Log.i("error", String.valueOf(response.code()));
                                    return ;
                                }
                                GitUsersResponse gitUsersResponse = response.body();
                                for (GitUser user: gitUsersResponse.users) {
                                    data.add(user);
                                }
                                listViewModel.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Seleccione un usuario", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<GitUsersResponse> call, Throwable t) {
                                Log.i("error", "Error");
                            }
                        }
                );



            }
        });
        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String username = data.get(i).username;

                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                intent.putExtra(USERNAME_PARAM,username);

                startActivity(intent);
            }
        });
    }
}

