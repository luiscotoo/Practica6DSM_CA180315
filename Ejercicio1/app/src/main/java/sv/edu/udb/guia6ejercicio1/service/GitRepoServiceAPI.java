package sv.edu.udb.guia6ejercicio1.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sv.edu.udb.guia6ejercicio1.models.GitRepo;
import sv.edu.udb.guia6ejercicio1.models.GitUsersResponse;

public interface GitRepoServiceAPI {
    @GET("search/users")
    public Call<GitUsersResponse> searchUsers(@Query("q") String query);

    @GET("users/{u}/repos")
    public Call<List<GitRepo>> userRepos(@Path("u") String username);
}
