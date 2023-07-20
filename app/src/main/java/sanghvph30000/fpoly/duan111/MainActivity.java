package sanghvph30000.fpoly.duan111;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import sanghvph30000.fpoly.duan111.fragment.AccountFragment;
import sanghvph30000.fpoly.duan111.fragment.HomeFragment;
import sanghvph30000.fpoly.duan111.fragment.ProductFragment;
import sanghvph30000.fpoly.duan111.fragment.StoreFrgm;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    public static BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private LinearLayout linearLayout;
    BottomNavigationView btNavView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btNavView = findViewById(R.id.navigation);
        btNavView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
        btNavView.setSelectedItemId(R.id.pageTrangChu);
        loadFragment(new HomeFragment());
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.pageTrangChu:
                fragment = new HomeFragment();
                loadFragment(fragment);
                return true;

            case R.id.pageSanPham:
                fragment = new ProductFragment();
                loadFragment(fragment);
                return true;

            case R.id.pageBanHang:
                fragment = new StoreFrgm();
                loadFragment(fragment);
                return true;

            case R.id.pageTaiKhoan:
                loadFragment(new AccountFragment());
                return true;
        }
        return false;
    }
}