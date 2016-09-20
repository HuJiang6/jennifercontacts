package view.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.contacts.lhj.jennifercontacts.R;

import presenter.main.MainPresenter;
import presenter.main.MainStatusPresenter;
import view.main.MainViewPagerAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Id to identify a contacts permission request.
     */
    public static final int REQUESTCODE = 1;

    /**
     * Id to identify a contacts permission request.
     */
    private static final String TAG = "MainActivity";

    /**
     * Permissions required to read and write contacts.
     */
    private static String[] PERMISSIONS = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.READ_SMS,
            Manifest.permission.CALL_PHONE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 获取运行该app能获得的最大内存
         */
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d(TAG, "Max memory is " + maxMemory + "KB");
        MainStatusPresenter.mainStart(this);
//        frameWorkPermission();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerInit(viewPager);
        tableLayoutInit(viewPager);
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUESTCODE) {
            Log.i(TAG, "Received response for contact permissions request.");

            // We have requested multiple permissions for contacts, so all of them need to be
            // checked.
            if (verifyPermissions(grantResults)) {
                // All required permissions have been granted, display contacts fragment.
//                Snackbar.make(mLayout, R.string.permision_available_contacts,
//                        Snackbar.LENGTH_SHORT)
//                        .show();
                Log.i(TAG, "Received response is yes for contact permissions request.");
            } else {
                Log.i(TAG, "Contacts permissions were NOT granted.");
//                Snackbar.make(mLayout, R.string.permissions_not_granted,
//                        Snackbar.LENGTH_SHORT)
//                        .show();
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    private boolean verifyPermissions(int[] grantResults) {
        if (grantResults.length < 1) {
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;

    }

    /**
     * 权限检测 动态获取
     */
    private void frameWorkPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_CONTACTS) !=
                        PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_CALL_LOG) !=
                        PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_CALL_LOG) !=
                        PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_SMS) !=
                        PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {
            // Contacts permissions have not been granted.
            requestPermissions();

        }

    }

    private void requestPermissions() {
        // BEGIN_INCLUDE(contacts_permission_request)
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_CONTACTS) &&
                !ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_CONTACTS) &&
                !ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CALL_LOG) &&
                !ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_CALL_LOG) &&
                !ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_SMS) &&
                !ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CALL_PHONE)) {
            // Contact permissions have not been granted yet. Request them directly.
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUESTCODE);
        }
        // END_INCLUDE(contacts_permission_request)

    }

    private void viewPagerInit(ViewPager viewPager) {

        MainViewPagerAdapter mainViewPagerAdapter =
                new MainViewPagerAdapter(getSupportFragmentManager());
        assert viewPager != null;
        viewPager.setAdapter(mainViewPagerAdapter);

    }

    private void tableLayoutInit(ViewPager viewPager) {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        assert tabLayout != null;
        tabLayout.setupWithViewPager(viewPager);
//        tabLayoutSelectedListener(tabLayout);
        MainPresenter.mainTableLayoutPresenter(tabLayout, this);

    }

    private void tabLayoutSelectedListener(TabLayout tabLayout) {
        final String Tag = "tableLayoutInit";
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            /**
             * 选中tab时候回调 2
             */
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(Tag, "onTabSelected");
            }

            /**
             * 没选中时候回调 比onTabSelected先回调
             */
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(Tag, "onTabUnselected");
            }

            /**
             * 重复选中时回调
             */
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(Tag, "onTabReselected");
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
