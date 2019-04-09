package aravinda.atlaslabs.hotel_booking.ui.base;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindString;
import aravinda.atlaslabs.hotel_booking.BaseApplication;
import aravinda.atlaslabs.hotel_booking.R;
import aravinda.atlaslabs.hotel_booking.injection.component.ActivityComponent;
import aravinda.atlaslabs.hotel_booking.injection.component.ConfigPersistentComponent;
import aravinda.atlaslabs.hotel_booking.injection.component.DaggerConfigPersistentComponent;
import aravinda.atlaslabs.hotel_booking.injection.module.ActivityModule;
import aravinda.atlaslabs.hotel_booking.util.AppDialog;
import aravinda.atlaslabs.hotel_booking.util.AppToast;
import java.util.concurrent.atomic.AtomicLong;

public class BaseActivity extends AppCompatActivity {
  private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
  private static final AtomicLong NEXT_ID = new AtomicLong(0);
  private AlertDialog progressDialog;
  private static final LongSparseArray<ConfigPersistentComponent>
      sComponentsMap = new LongSparseArray<>();

  private ActivityComponent mActivityComponent;

  private long mActivityId;

  @BindString(R.string.msg_1) String msgLoading;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivityId = savedInstanceState != null ?
        savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();
    progressDialog = AppDialog.initProgressDialog(this, msgLoading);
    ConfigPersistentComponent configPersistentComponent = sComponentsMap.get(mActivityId, null);

    if (configPersistentComponent == null) {
      configPersistentComponent = DaggerConfigPersistentComponent.builder()
          .applicationComponents(BaseApplication.get(this).getComponent())
          .build();

      sComponentsMap.put(mActivityId, configPersistentComponent);
    }

    mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));
  }
  @CallSuper
  protected void showToast(String msg) {
    AppToast.showToast(this, msg);
  }

  @CallSuper
  protected void showErrorMessage(String msg) {
    AppDialog.showMsgDialog(this, msg, null);
  }

  @CallSuper
  protected void showProgressDialog() {
    if (!progressDialog.isShowing()) {
      progressDialog.show();
    }
  }

  @CallSuper
  protected void hideProgressDialog() {
    if (progressDialog.isShowing()) {
      progressDialog.dismiss();
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putLong(KEY_ACTIVITY_ID, mActivityId);
  }

  @Override
  protected void onDestroy() {
    if (!isChangingConfigurations()) {
      sComponentsMap.remove(mActivityId);
    }
    super.onDestroy();
  }

  public ActivityComponent activityComponent() {
    return mActivityComponent;
  }

}
