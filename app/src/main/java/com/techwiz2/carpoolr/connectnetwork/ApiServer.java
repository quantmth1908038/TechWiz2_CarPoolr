package com.techwiz2.carpoolr.connectnetwork;

import retrofit2.http.GET;

public interface ApiServer {
    String SERVER_URL = "https://maps.googleapis.com/";

//    @GET("maps/api/directions/json")
//    Call<Result> getDirection(@Query("mode") String mode,
//                              @Query("transit_routing_preferance") String preferance,
//                              @Query("origin") String origin,
//                              @Query("destination") String destination,
//                              @Query("key") String key);
}
