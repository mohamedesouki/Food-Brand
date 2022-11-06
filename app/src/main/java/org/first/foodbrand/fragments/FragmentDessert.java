package org.first.foodbrand.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.first.foodbrand.R;
import org.first.foodbrand.adapters.CategoriesAdapter;
import org.first.foodbrand.adapters.FragmentAdapter;
import org.first.foodbrand.models.BeefModel;
import org.first.foodbrand.models.DishModel;
import org.first.foodbrand.ui.DetailsActivity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.function.Consumer;

public class FragmentDessert extends Fragment {
    TextView textViewDessert;
    RecyclerView recyclerView;
    private FragmentAdapter adapter ;
    ArrayList<BeefModel> beefList;
    public static FragmentDessert newInstance(){
        return new FragmentDessert();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_dessert , container , false);
        textViewDessert = view.findViewById(R.id.textViewdessert);

        StringRequest request = new StringRequest(0, "https://www.themealdb.com/api/json/v1/1/categories.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =new JSONObject(response);
                    JSONArray category = jsonObject.getJSONArray("categories");
                        JSONObject meal = category.getJSONObject(2);
                        String test = meal.getString("strCategoryDescription");
                        Log.e("test",test);

                    textViewDessert.setText(test);



                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(request);
        //===============================================================
        beefList = new ArrayList<>();
        StringRequest request1 = new StringRequest(0, "https://www.themealdb.com/api/json/v1/1/filter.php?c=dessert", new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject2 =new JSONObject(response);
                    JSONArray meals = jsonObject2.getJSONArray("meals");
                    for(int i = 0;i<meals.length();i++){
                        JSONObject meal = meals.getJSONObject(i);
                        //String test = meal.getString("strCategory");
                        //Log.e("meal",test);

                        beefList.add(new BeefModel(
                                        meal.getString("strMeal"),
                                        meal.getString("strMealThumb"),
                                meal.getInt("idMeal")
                                )
                        );

                    }


                }catch (Exception e){
                    e.printStackTrace();
                }

                recyclerView = view.findViewById(R.id.rv_dessert);
                adapter = new FragmentAdapter(beefList, getActivity().getApplicationContext());
                recyclerView.setAdapter(adapter);
                StaggeredGridLayoutManager staggeredGridLayoutManager = new  StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                adapter.setOnConsumer(new Consumer<BeefModel>() {
                    @Override
                    public void accept(BeefModel beefModel) {
                        //goToAttract(view);
                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtra("id",beefModel.getId());
                        startActivity(intent);
                    }
                });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue2 = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue2.add(request1);

        return view;

    }
    public void goToAttract(View v)
    {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        startActivity(intent);
    }
    }


