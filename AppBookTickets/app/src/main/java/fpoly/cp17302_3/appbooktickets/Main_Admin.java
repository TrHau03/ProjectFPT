package fpoly.cp17302_3.appbooktickets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

import fpoly.cp17302_3.appbooktickets.Fragment.Fragment_Admin.DoiMatKhau_Fragment;
import fpoly.cp17302_3.appbooktickets.Fragment.Fragment_Admin.Fragment_QLPhim_Admin;
import fpoly.cp17302_3.appbooktickets.Fragment.Fragment_Admin.Fragment_QLTaiKhoan_Admin;
import fpoly.cp17302_3.appbooktickets.Fragment.Fragment_Admin.Fragment_QLTheLoai_Admin;
import fpoly.cp17302_3.appbooktickets.Fragment.Fragment_Admin.Fragment_QLXuatChieu_Admin;
import fpoly.cp17302_3.appbooktickets.Fragment.Fragment_Admin.Fragment_TrangChu_Admin;
import fpoly.cp17302_3.appbooktickets.Fragment.Fragment_KhachHang.Fragment_DatVe_KhachHang;
import fpoly.cp17302_3.appbooktickets.Fragment.Fragment_KhachHang.Fragment_Phim_KhachHang;
import fpoly.cp17302_3.appbooktickets.Fragment.Fragment_KhachHang.Fragment_VeCuaToi_KhachHang;
import fpoly.cp17302_3.appbooktickets.TaiKhoan.DangNhap_Activity;

public class Main_Admin extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    String hinhanh, ten, loai, ngay, gio, maxuatchieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        LocalBroadcastManager.getInstance(Main_Admin.this).registerReceiver(mMessageReceiver,
                new IntentFilter("Get Total"));
        toolbar = findViewById(R.id.toolbar);
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.framelayout, new Fragment_TrangChu_Admin())
                .commit();*/
        FrameLayout frameLayout = findViewById(R.id.framelayout);
        NavigationView navigationView = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drawerlayout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.mHome:
                        fragment = new Fragment_TrangChu_Admin();
                        break;
                    case R.id.mDangChieu:
                        fragment = new Fragment_QLPhim_Admin();
                        break;
                    case R.id.mQLTaiKhoan:
                        fragment = new Fragment_QLTaiKhoan_Admin();
                        break;
                    case R.id.mQLXuatChieu:
                        fragment = new Fragment_QLXuatChieu_Admin();
                        break;
                    case R.id.mTheLoai:
                        fragment = new Fragment_QLTheLoai_Admin();
                        break;
                    case R.id.mKH_Phim:
                        fragment = new Fragment_Phim_KhachHang();
                        break;
                    case  R.id.mDoiMK:
                        fragment = new DoiMatKhau_Fragment();
                        break;
                    case R.id.mKH_VeCuaToi:
                        fragment = new Fragment_VeCuaToi_KhachHang();
                        break;
                    case R.id.mThoat:
                        Intent intent = new Intent(Main_Admin.this, DangNhap_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    default:
                        fragment = new Fragment_TrangChu_Admin();
                        break;
                }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.framelayout, fragment)
                            .commit();
                    toolbar.setTitle(item.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    public void ChuyenFG(String maxuatchieu,String hinhanh,String tenphim, String theloai, String gio, String ngay) {
        Bundle bundle = new Bundle();
        bundle.putString("maxc", maxuatchieu);
        bundle.putString("hinhanh", hinhanh);
        bundle.putString("ten", tenphim);
        bundle.putString("loai", theloai);
        bundle.putString("gio", gio);
        bundle.putString("ngay", ngay);

        Fragment_DatVe_KhachHang datVeFragment = new Fragment_DatVe_KhachHang();
        datVeFragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.framelayout, datVeFragment)
                .commit();
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            maxuatchieu = intent.getStringExtra("maxc");
            hinhanh = intent.getStringExtra("hinhanh");
            ten=intent.getStringExtra("ten");
            loai=intent.getStringExtra("loai");
            gio = intent.getStringExtra("gio");
            ngay = intent.getStringExtra("ngay");


            Log.d("TAG-Main","TAGMAIN"+ maxuatchieu + ten);
            ChuyenFG(maxuatchieu,hinhanh, ten,loai, gio, ngay);
        }
    };
}