package fpoly.cp17302_3.appbooktickets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import fpoly.cp17302_3.appbooktickets.Fragment.Fragment_KhachHang.Fragment_Phim_KhachHang;

public class Main_KhachHang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_khach_hang);
    }

    public void ChuyenFGDangChieu(){
        Fragment_Phim_KhachHang fragmentDangChieuKhachHang = new Fragment_Phim_KhachHang();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.frameLayout, fragmentDangChieuKhachHang)
                .commit();
    }
}