package org.first.foodbrand.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.first.foodbrand.R;
import org.first.foodbrand.viewpager.ViewPagerCategories;

public class CategoriesActivity extends AppCompatActivity {
    private TabLayout tabLayoutContries;
    private ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        tabLayoutContries = findViewById(R.id.tabLayoutCategories);
        viewPager2 = findViewById(R.id.viewPagerCategories);

        ViewPagerCategories adapter = new ViewPagerCategories(getSupportFragmentManager() , getLifecycle());
        viewPager2.setAdapter(adapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayoutContries,viewPager2 , true , true,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position){
                            case 0:
                                tab.setText("Beef");
                                break;
                            case 1:
                                tab.setText("Chicken");
                                break;
                            case 2:
                                tab.setText("Dessert");
                                break;
                            case 3:
                                tab.setText("Lamb");
                                break;
                            case 4:
                                tab.setText("Miscellaneous");
                                break;
                            case 5:
                                tab.setText("Pasta");
                                break;
                            case 6:
                                tab.setText("Pork");
                                break;
                            case 7:
                                tab.setText("SeaFood");
                                break;
                            case 8:
                                tab.setText("Side");
                                break;
                            case 9:
                                tab.setText("Starter");
                                break;
                            case 10:
                                tab.setText("Vegan");
                                break;
                            case 11:
                                tab.setText("Vegetarian");
                                break;
                            case 12:
                                tab.setText("BreakFast");
                                break;
                            case 13:
                                tab.setText("Goat");
                                break;
                        }


                    }
                });
        tabLayoutMediator.attach();

    }
}