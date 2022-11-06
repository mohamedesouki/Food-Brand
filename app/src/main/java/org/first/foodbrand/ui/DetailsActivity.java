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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.first.foodbrand.R;
import org.first.foodbrand.adapters.DishAdapter;
import org.first.foodbrand.adapters.FragmentAdapter;
import org.first.foodbrand.models.BeefModel;
import org.first.foodbrand.models.DishModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.function.Consumer;

public class DetailsActivity extends AppCompatActivity {
 TextView textViewName,textViewDescription,textViewCategory,textViewCountry;
 ImageView imageView;
 TextView textViewIngredient1,textViewIngredient2,textViewIngredient3,textViewIngredient4,textViewIngredient5,textViewIngredient6;
 TextView textViewMeasure1,textViewMeasure2,textViewMeasure3,textViewMeasure4,textViewMeasure5,textViewMeasure6,textViewYoutube;
 String youtubeLink;
 Button buttonYoutube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        textViewName = findViewById(R.id.mealname);
        textViewCategory = findViewById(R.id.categoryDetails);
        textViewCountry = findViewById(R.id.countryDetails);
        textViewDescription = findViewById(R.id.instructionsDetails);
        imageView = findViewById(R.id.imabgviewDetails);
        textViewIngredient1 = findViewById(R.id.ingredient1);
        textViewIngredient2 = findViewById(R.id.ingredient2);
        textViewIngredient3 = findViewById(R.id.ingredient3);
        textViewIngredient4 = findViewById(R.id.ingredient4);
        textViewIngredient5 = findViewById(R.id.ingredient5);
        textViewIngredient6 = findViewById(R.id.ingredient6);
        buttonYoutube = findViewById(R.id.btnyoutube);


        textViewMeasure1 = findViewById(R.id.measure1);
        textViewMeasure2 = findViewById(R.id.measure2);
        textViewMeasure3 = findViewById(R.id.measure3);
        textViewMeasure4 = findViewById(R.id.measure4);
        textViewMeasure5 = findViewById(R.id.measure5);
        textViewMeasure6 = findViewById(R.id.measure7);



        int id = getIntent().getIntExtra("id",-1);
        StringRequest stringRequest2 = new StringRequest(0, "https://www.themealdb.com/api/json/v1/1/lookup.php?i="+id, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray meals = jsonObject.getJSONArray("meals");
                    for(int i = 0;i<meals.length();i++){
                        JSONObject meal = meals.getJSONObject(i);
                        String name = meal.getString("strMeal");
                        String category = meal.getString("strCategory");
                        String country = meal.getString("strArea");
                        String instructions = meal.getString("strInstructions");
                        String image = meal.getString("strMealThumb");
                        String ingredient1 = meal.getString("strIngredient1");
                        String ingredient2 = meal.getString("strIngredient2");
                        String ingredient3 = meal.getString("strIngredient3");
                        String ingredient4 = meal.getString("strIngredient4");
                        String ingredient5 = meal.getString("strIngredient5");
                        String ingredient6 = meal.getString("strIngredient6");
                        String measure1 =  meal.getString("strMeasure1");
                        String measure2 =  meal.getString("strMeasure2");
                        String measure3 =  meal.getString("strMeasure3");
                        String measure4 =  meal.getString("strMeasure4");
                        String measure5 =  meal.getString("strMeasure5");
                        String measure6 =  meal.getString("strMeasure6");
                         youtubeLink = meal.getString("strYoutube");



                        textViewName.setText(name);
                        textViewDescription.setText(instructions);
                        textViewCategory.setText(category);
                        textViewCountry.setText(country);
                        Glide.with(DetailsActivity.this).load(image).into(imageView);
                        textViewIngredient1.setText("."+ingredient1);
                        textViewIngredient2.setText("."+ingredient2);
                        textViewIngredient3.setText("."+ingredient3);
                        textViewIngredient4.setText("."+ingredient4);
                        textViewIngredient5.setText("."+ingredient5);
                        textViewIngredient6.setText("."+ingredient6);
                        textViewMeasure1.setText("."+measure1);
                        textViewMeasure2.setText("."+measure2);
                        textViewMeasure3.setText("."+measure3);
                        textViewMeasure4.setText("."+measure4);
                        textViewMeasure5.setText("."+measure5);
                        textViewMeasure6.setText("."+measure6);


                    }



                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue2 = Volley.newRequestQueue(this);
        queue2.add(stringRequest2);

        buttonYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this,YoutubeActivity.class);
                intent.putExtra("youtube",youtubeLink);
                startActivity(intent);
            }
        });
    }
}