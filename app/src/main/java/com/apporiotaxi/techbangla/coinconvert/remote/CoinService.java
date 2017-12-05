package com.apporiotaxi.techbangla.coinconvert.remote;

import com.apporiotaxi.techbangla.coinconvert.model.Coin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by emon on 11/27/2017.
 */

public interface CoinService {
    @GET("data/price")
    Call<Coin> calculateValue(@Query("fsym") String from,@Query("tsyms") String to);
}
