package com.techwiz2.carpoolr.connectnetwork;

import com.techwiz2.carpoolr.model.AccessToken;
import com.techwiz2.carpoolr.model.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServer {
    String SERVER_URL = "https://techwiz-api.test.vieted.net/";


    @POST("api/auth/register")
    Call<Integer> getRegister(@Query("email") String email,
                               @Query("password") String password);

    @POST("api/auth/login")
    Call<AccessToken> getLogin(@Query("email") String email,
                               @Query("password") String password);



    

//    @GET("maps/api/directions/json")
//    Call<Result> getDirection(@Query("mode") String mode,
//                              @Query("transit_routing_preferance") String preferance,
//                              @Query("origin") String origin,
//                              @Query("destination") String destination,
//                              @Query("key") String key);
}
