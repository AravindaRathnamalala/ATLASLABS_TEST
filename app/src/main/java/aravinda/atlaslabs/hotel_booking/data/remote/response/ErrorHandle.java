package aravinda.atlaslabs.hotel_booking.data.remote.response;

import aravinda.atlaslabs.hotel_booking.util.Constant;
import org.json.JSONObject;
import retrofit2.Response;

public class ErrorHandle {

  public static String getApiErrorMessage(Response<?> response) {
    String error = null;
    try {
      JSONObject jObjError = new JSONObject(response.errorBody().string());
      error = jObjError.getString("error");
    } catch (Exception e) {
      error = Constant.REQUEST_FAILED;
    }
    return error;
  }
}
