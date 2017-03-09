package com.asyraf.frilo.data;

import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.asyraf.frilo.data.model.AuthResponse;
import com.asyraf.frilo.data.model.ParkLocationResponse;
import com.asyraf.frilo.data.model.Pokemon;
import com.asyraf.frilo.data.model.Response;
import com.asyraf.frilo.data.model.Statistic;
import com.asyraf.frilo.data.remote.MvpStarterService;
import io.reactivex.Single;

@Singleton
public class DataManager {

    private final MvpStarterService mMvpStarterService;

    @Inject
    public DataManager(MvpStarterService mvpStarterService) {
        mMvpStarterService = mvpStarterService;
    }

    public Single<List<String>> getPokemonList(int limit) {
        return mMvpStarterService.getPokemonList(limit)
                .toObservable()
                .flatMapIterable(namedResources -> namedResources.results)
                .map(namedResource -> namedResource.name)
                .toList();
    }

    public Single<Pokemon> getPokemon(String name) {
        return mMvpStarterService.getPokemon(name);
    }

    public Single<Response> registerUser(String full_name,String email,String license,String device_token,String kit_token){
        return mMvpStarterService.registerUser(ServerStatic.ACTION_REGISTER,full_name,email,license,ServerStatic.DEVICE_TYPE,device_token,kit_token);
    }

    public Single<AuthResponse> authServer(String kit_token){
        return mMvpStarterService.authServer(ServerStatic.ACTION_AUTH_LOGIN, kit_token);
    }

    public Single<ParkLocationResponse> getParkLocation(String access_token,double latitude,double longitude,int vehicle){
        return mMvpStarterService.getOpenParkingLocation(access_token,ServerStatic.ACTION_GET_PARKING_LOCATION,ServerStatic.TYPE_DRIVER,latitude,longitude,vehicle);
    }



}