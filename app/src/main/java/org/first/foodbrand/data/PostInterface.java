package org.first.foodbrand.data;

import org.first.foodbrand.models.BeefModel;


import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {

    @GET("api/json/v1/1/filter.php?c=lamb")
    public Call<BeefModel> getBeef();
}
