package org.first.foodbrand.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.first.foodbrand.R;
import org.first.foodbrand.adapters.CategoriesAdapter;
import org.first.foodbrand.adapters.DishAdapter;
import org.first.foodbrand.models.DishModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private CategoriesAdapter adapter ;
    private DishAdapter adapter2 ;
    ArrayList<DishModel> categoryList;
    ArrayList<DishModel> categoryList2;
    TextView textView,textView2 ;
    ImageView imageViewDish;
    EditText editTextSearch;
    ShimmerFrameLayout shimmerFrameLayout;
    ShimmerFrameLayout shimmerFrameLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tvintent);
        textView2 = findViewById(R.id.searchMain);
        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout2 = findViewById(R.id.shimmer2);
        shimmerFrameLayout.startShimmer();
        shimmerFrameLayout2.startShimmer();
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });

        categoryList= new ArrayList<>();
        StringRequest stringRequest = new StringRequest(0, "https://www.themealdb.com/api/json/v1/1/categories.php", new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray meals = jsonObject.getJSONArray("categories");
                    for(int i = 0;i<meals.length();i++){
                        JSONObject meal = meals.getJSONObject(i);
                        String test = meal.getString("strCategory");

                        Log.e("meal",test);
                        categoryList.add(new DishModel(
                                        meal.getString("strCategory"),
                                        meal.getString("strCategoryThumb"),
                                        meal.getInt("idCategory")
                                )
                        );

                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
                adapter = new CategoriesAdapter(categoryList,MainActivity.this);
                recyclerView = findViewById(R.id.rv_categories);
                recyclerView.setAdapter(adapter);
                StaggeredGridLayoutManager staggeredGridLayoutManager = new  StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.setOnConsumer(
                        new Consumer<DishModel>() {
                            @Override
                            public void accept(DishModel dishModel) {
                                Intent intent = new Intent(MainActivity.this,CategoriesActivity.class);
                                startActivity(intent);
                            }

                        }
                );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
        //======================================================================
        categoryList2= new ArrayList<>();
        StringRequest stringRequest2 = new StringRequest(0, "https://www.themealdb.com/api/json/v2/9973533/latest.php", new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray meals = jsonObject.getJSONArray("meals");
                    for(int i = 0;i<meals.length();i++){
                        JSONObject meal = meals.getJSONObject(i);
                        String test = meal.getString("strMeal");

                        Log.e("meal",test);
                        categoryList2.add(new DishModel(
                                        meal.getString("strMeal"),
                                        meal.getString("strMealThumb"),
                                        meal.getInt("idMeal")
                                )
                        );

                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
                adapter2 = new DishAdapter(categoryList2,MainActivity.this);
                recyclerView2 = findViewById(R.id.rv_dishes);
                RecyclerView.LayoutManager layoutManager  = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
                recyclerView2.setLayoutManager(layoutManager);
                recyclerView2.setAdapter(adapter2);
                shimmerFrameLayout2.stopShimmer();
                shimmerFrameLayout2.setVisibility(View.GONE);
                recyclerView2.setVisibility(View.VISIBLE);
                adapter2.setOnConsumer(
                        new Consumer<DishModel>() {
                            @Override
                            public void accept(DishModel dishModel) {
                                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                                intent.putExtra("id",dishModel.getId());
                                startActivity(intent);
                            }

                        }
                );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue2 = Volley.newRequestQueue(this);
        queue2.add(stringRequest2);



    }
}