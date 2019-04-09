package aravinda.atlaslabs.hotel_booking.injection.module;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import aravinda.atlaslabs.hotel_booking.injection.ApplicationContext;
import aravinda.atlaslabs.hotel_booking.network.ApiService;
import javax.inject.Singleton;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {

  protected final Application mApplication;

  public ApplicationModule(Application application) {
    mApplication = application;
  }

  @Provides
  Application provideApplication() {
    return mApplication;
  }

  @Provides
  @ApplicationContext
  Context provideContext() {
    return mApplication;
  }

  @Provides
  @Singleton
  ApiService provideApiService() {
    return ApiService.Creator.newApiService();
  }
}
