package com.asyraf.frilo.data.remote;


import com.asyraf.frilo.data.model.AuthResponse;
import com.asyraf.frilo.data.model.ParkLocationResponse;
import com.asyraf.frilo.data.model.Pokemon;
import com.asyraf.frilo.data.model.PokemonListResponse;
import com.asyraf.frilo.data.model.Response;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MvpStarterService {

    @GET("pokemon")
    Single<PokemonListResponse> getPokemonList(@Query("limit") int limit);

    @GET("pokemon/{name}")
    Single<Pokemon> getPokemon(@Path("name") String name);

    @FormUrlEncoded @POST ("globe/api/")
    Single<Response> registerUser(@Field("action") String action, @Field("full_name") String full_name, @Field("email") String email,@Field("license_driver") String license,@Field("device_type") int device_type, @Field("device_token") String device_token,@Field("kit_token") String kit_token);

    @FormUrlEncoded @POST ("globe/api/")
    Single<AuthResponse> authServer(@Field("action") String action, @Field("kit_token") String kit_token);

    @GET("park/api")
    Single<ParkLocationResponse> getOpenParkingLocation(@Query("access_token") String access_token,@Query("action") String action,@Query("app_type") String app_type,@Query("latitude") double latitude,@Query("longitude") double longitude,@Query("vehicle") int vehicle);


}
