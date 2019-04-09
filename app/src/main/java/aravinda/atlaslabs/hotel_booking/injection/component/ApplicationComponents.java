package aravinda.atlaslabs.hotel_booking.injection.component;

import android.app.Application;
import android.content.Context;
import dagger.Component;
import aravinda.atlaslabs.hotel_booking.injection.ApplicationContext;
import aravinda.atlaslabs.hotel_booking.injection.module.ApplicationModule;
import aravinda.atlaslabs.hotel_booking.network.ApiService;
import javax.inject.Singleton;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponents {
  @ApplicationContext
  Context context();

  Application application();

  ApiService apiService();

}
