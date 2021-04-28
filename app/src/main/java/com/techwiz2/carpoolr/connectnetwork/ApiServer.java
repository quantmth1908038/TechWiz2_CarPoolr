package com.techwiz2.carpoolr.connectnetwork;

import com.techwiz2.carpoolr.model.AccessToken;
import com.techwiz2.carpoolr.model.History;
import com.techwiz2.carpoolr.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServer {
    String SERVER_URL = "https://techwiz-api.test.vieted.net/";


    @POST("api/auth/register")
    Call<Integer> getRegister(@Query("email") String email,
                               @Query("password") String password);

    @POST("api/auth/login")
    Call<AccessToken> getLogin(@Query("email") String email,
                               @Query("password") String password,
                               @Query("role") String role,
                               @Query("user_id") String user_id);

    @POST("api/user/book-ride")
    Call<Integer> bookCar(@Header("Authorization") String token,
                          @Query("destinationX") double destinationX,
                          @Query("destinationY") double destinationY,
                          @Query("departureX") double departureX,
                          @Query("departureY") double departureY,
                          @Query("slot") int slot,
                          @Query("time") long time);

    @GET("api/ride")
    Call<List<History>> getHistory(@Header("Authorization") String token);



    

//    @GET("maps/api/directions/json")
//    Call<Result> getDirection(@Query("mode") String mode,
//                              @Query("transit_routing_preferance") String preferance,
//                              @Query("origin") String origin,
//                              @Query("destination") String destination,
//                              @Query("key") String key);
}
