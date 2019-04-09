package aravinda.atlaslabs.hotel_booking.network;

import java.util.List;

import aravinda.atlaslabs.hotel_booking.model.Data;
import aravinda.atlaslabs.hotel_booking.util.Constant;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;

public interface ApiService {

    /**
     * Get the list of the hotels from the API
     */
    @GET("api/test/")
    Call<Data> getHotels();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static ApiService newApiService() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();

            return retrofit.create(ApiService.class);
        }
    }
}
