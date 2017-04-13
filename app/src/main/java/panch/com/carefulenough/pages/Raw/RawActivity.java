package panch.com.carefulenough.pages.Raw;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import panch.com.carefulenough.R;
import panch.com.carefulenough.pages.Base.BaseActivity;
import panch.com.carefulenough.pages.MainMenu.Fragment.MainMenuFragment;
import panch.com.carefulenough.utils.FragmentUtils;
import panch.com.carefulenough.utils.PermissionUtils;

public class RawActivity extends BaseActivity {

    private static int PERMISSIONS_STATUS_CODE = 151;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handlePermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_STATUS_CODE) {
            handlePermissions();
        }
    }

    private void handlePermissions() {
        String[] Perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (!PermissionUtils.hasPermissions(this, Perms)) {
            PermissionUtils.askForPermissions(this, Perms, PERMISSIONS_STATUS_CODE);
        } else {
            FragmentUtils futils = new FragmentUtils(this);
            FragmentUtils.fragmentToAdd = MainMenuFragment.newInstance();
            futils.setFragment(R.id.container,true);
        }

    }
}
