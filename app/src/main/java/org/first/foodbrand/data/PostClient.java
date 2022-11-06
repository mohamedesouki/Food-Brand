package org.first.foodbrand.data;

import org.first.foodbrand.models.BeefModel;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {
    private static final String BASE_URL = "https://www.themealdb.com/";
    private PostInterface postInterface;
    private static PostClient Instance;

    public PostClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);
    }
    public static PostClient getInstance(){
        if (null == Instance){
            Instance = new PostClient();
        }
        return Instance;
    }
    public Call<BeefModel> getBeef(){
        return postInterface.getBeef();
    }
}
