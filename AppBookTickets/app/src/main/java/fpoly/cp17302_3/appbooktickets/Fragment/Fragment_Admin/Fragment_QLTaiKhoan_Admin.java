package fpoly.cp17302_3.appbooktickets.Fragment.Fragment_Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.cp17302_3.appbooktickets.Adapter.ThanhVien_Adapter;
import fpoly.cp17302_3.appbooktickets.DAO.ThanhVienDAO;
import fpoly.cp17302_3.appbooktickets.Model.ThanhVien;
import fpoly.cp17302_3.appbooktickets.R;

public class Fragment_QLTaiKhoan_Admin extends Fragment {
    ThanhVienDAO thanhVienDAO;
    RecyclerView recyclerViewTaiKhoan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qltaikhoan, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewTaiKhoan = view.findViewById(R.id.recyclerThanhVien);
        thanhVienDAO = new ThanhVienDAO(getContext());


        loadData();
    }


    private void loadData() {
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewTaiKhoan.setLayoutManager(linearLayoutManager);
        ThanhVien_Adapter adapter = new ThanhVien_Adapter(getContext(), list, thanhVienDAO);
        recyclerViewTaiKhoan.setAdapter(adapter);
    }
}
