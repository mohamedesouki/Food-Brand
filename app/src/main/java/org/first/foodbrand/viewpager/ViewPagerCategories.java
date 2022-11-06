package org.first.foodbrand.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.first.foodbrand.fragments.FragmentBeef;
import org.first.foodbrand.fragments.FragmentBreakFast;
import org.first.foodbrand.fragments.FragmentChicken;
import org.first.foodbrand.fragments.FragmentDessert;
import org.first.foodbrand.fragments.FragmentGoat;
import org.first.foodbrand.fragments.FragmentLamb;
import org.first.foodbrand.fragments.FragmentMiscellaneous;
import org.first.foodbrand.fragments.FragmentPasta;
import org.first.foodbrand.fragments.FragmentPork;
import org.first.foodbrand.fragments.FragmentSeaFood;
import org.first.foodbrand.fragments.FragmentSide;
import org.first.foodbrand.fragments.FragmentStarter;
import org.first.foodbrand.fragments.FragmentVegan;
import org.first.foodbrand.fragments.FragmentVegetarian;


public class ViewPagerCategories extends FragmentStateAdapter {
    public ViewPagerCategories(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment = FragmentBeef.newInstance();
                break;
            case 1:
                fragment = FragmentChicken.newInstance();
                break;
            case 2:
                fragment = FragmentDessert.newInstance();
                break;
            case 3:
                fragment = FragmentLamb.newInstance();
                break;
            case 4:
                fragment = FragmentMiscellaneous.newInstance();
                break;
            case 5:
                fragment = FragmentPasta.newInstance();
                break;
            case 6:
                fragment = FragmentPork.newInstance();
                break;
            case 7:
                fragment = FragmentSeaFood.newInstance();
                break;
            case 8:
                fragment = FragmentSide.newInstance();
                break;
            case 9:
                fragment = FragmentStarter.newInstance();
                break;
            case 10:
                fragment = FragmentVegan.newInstance();
                break;
            case 11:
                fragment = FragmentVegetarian.newInstance();
                break;
            case 12:
                fragment = FragmentBreakFast.newInstance();
                break;
            case 13:
                fragment = FragmentGoat.newInstance();
                break;
            default:
                return null;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 14;
    }
}
