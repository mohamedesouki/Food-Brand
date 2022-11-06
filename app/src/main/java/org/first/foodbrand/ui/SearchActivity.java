package org.first.foodbrand.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.first.foodbrand.R;
import org.first.foodbrand.adapters.FragmentAdapter;
import org.first.foodbrand.models.BeefModel;
import org.first.foodbrand.models.DishModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.function.Consumer;

public class SearchActivity extends AppCompatActivity {
        SearchView searchView;
    RecyclerView recyclerView;
    private FragmentAdapter adapter ;
    ArrayList<BeefModel> beefList;
    LottieAnimationView notFound;
    boolean boolSearch = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.searchView);
        notFound = findViewById(R.id.notFound);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchPlace(s);
                return true;
            }
        });

    }
    void searchPlace(String word) {
        beefList = new ArrayList<>();
        StringRequest request = new StringRequest(0, "https://www.themealdb.com/api/json/v1/1/search.php?s="
                +word,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray meals = jsonObject.getJSONArray("meals");
                            for (int i = 0; i < meals.length(); i++) {

                                JSONObject meal = meals.getJSONObject(i);

                                beefList.add(new BeefModel(
                                        meal.getString("strMeal"),
                                        meal.getString("strMealThumb"),
                                        meal.getInt("idMeal")
                                ));
                            }

                        } catch (Exception e) {
                        }
                        if (beefList.size()== 0){
                            //if (boolSearch){
                                recyclerView.setVisibility(View.INVISIBLE);
                                notFound.setVisibility(View.VISIBLE);
                                //boolSearch = false;
                            }else {
                            // boolSearch = true;
                            //notFound.setVisibility(View.INVISIBLE);
                            recyclerView = findViewById(R.id.rv_search);
                            recyclerView.setVisibility(View.VISIBLE);
                            adapter = new FragmentAdapter(beefList, SearchActivity.this);
                            recyclerView.setAdapter(adapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                        }






                                adapter.setOnConsumer(
                                        new Consumer<BeefModel>() {
                                            @Override
                                            public void accept(BeefModel beefModel) {
                                                Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                                                intent.putExtra("id",beefModel.getId());
                                                startActivity(intent);
                                            }


                                        }
                                );
                            }




                    //}
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });


        RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
        queue.add(request);
    }
}
