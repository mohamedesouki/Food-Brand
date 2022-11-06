package org.first.foodbrand.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import org.first.foodbrand.models.BeefModel;

import java.util.List;

public class FragmentViewModel extends ViewModel {
    MutableLiveData<List<BeefModel>> beefMutableLiveData = new MutableLiveData<>();

    public void getBeef(){};

}
