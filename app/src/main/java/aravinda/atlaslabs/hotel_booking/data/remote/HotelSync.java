package aravinda.atlaslabs.hotel_booking.data.remote;

import android.content.Context;

import javax.inject.Inject;

import aravinda.atlaslabs.hotel_booking.data.remote.response.ErrorHandle;
import aravinda.atlaslabs.hotel_booking.injection.ApplicationContext;
import aravinda.atlaslabs.hotel_booking.model.Data;
import aravinda.atlaslabs.hotel_booking.network.ApiService;
import aravinda.atlaslabs.hotel_booking.util.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelSync {
    private final ApiService mApiService;
    private final Context mContext;

    @Inject
    public HotelSync(@ApplicationContext Context context, ApiService apiService) {
        this.mContext = context;
        this.mApiService = apiService;
    }
    public void getHotels(final HotelSyncIn.OnHotelListener listener){
        ApiService.Creator.newApiService()
                .getHotels()
               .enqueue(new Callback<Data>() {
                   @Override
                   public void onResponse(Call<Data> call, Response<Data> response) {
                       if(response.isSuccessful()){
                           listener.onHotelReceivedSuccess(response.body());
                       }else {
                           listener.onHotelReceivedFailed(ErrorHandle.getApiErrorMessage(response));
                       }
                   }

                   @Override
                   public void onFailure(Call<Data> call, Throwable t) {
                        listener.onHotelReceivedFailed(Constant.REQUEST_FAILED);
                   }
               });
    }

}
