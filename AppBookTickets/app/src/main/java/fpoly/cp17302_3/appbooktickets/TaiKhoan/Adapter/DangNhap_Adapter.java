package fpoly.cp17302_3.appbooktickets.TaiKhoan.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fpoly.cp17302_3.appbooktickets.TaiKhoan.Fragment.Register_Fragment;
import fpoly.cp17302_3.appbooktickets.TaiKhoan.Fragment.Login_Fragment;

public class DangNhap_Adapter extends FragmentStateAdapter {
    public DangNhap_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Login_Fragment();
            case 1:
                return new Register_Fragment();
            default:
                return new Login_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
