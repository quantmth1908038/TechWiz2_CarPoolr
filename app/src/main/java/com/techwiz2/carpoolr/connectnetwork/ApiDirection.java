package com.techwiz2.carpoolr.connectnetwork;

import com.techwiz2.carpoolr.model.direction.DirectionResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiDirection {
    String MAP_URL = "https://maps.googleapis.com/";

    @GET("maps/api/directions/json")
    Call<DirectionResults> getDirection(@Query("mode") String mode,
                                        @Query("transit_routing_preferance") String preferance,
                                        @Query("origin") String origin,
                                        @Query("destination") String destination,
                                        @Query("key") String key);
}
