package fpoly.cp17302_3.appbooktickets.Fragment.Fragment_KhachHang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.cp17302_3.appbooktickets.Adapter.KhachHang_Phim_Adapter;
import fpoly.cp17302_3.appbooktickets.DAO.PhimDAO;
import fpoly.cp17302_3.appbooktickets.DAO.XuatChieuDAO;
import fpoly.cp17302_3.appbooktickets.Model.Phim;
import fpoly.cp17302_3.appbooktickets.Model.XuatChieu;
import fpoly.cp17302_3.appbooktickets.R;

public class Fragment_Phim_KhachHang extends Fragment {
    RecyclerView recyclerPhim;
    XuatChieuDAO xuatChieuDAO;
    SearchView searchView;
    KhachHang_Phim_Adapter khachHang_phim_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.khachhang_fragment_phim, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerPhim = view.findViewById(R.id.recyclerPhim);
        searchView = view.findViewById(R.id.searchview);
        xuatChieuDAO = new XuatChieuDAO(getContext());

        loadData();
    }

    private void loadData() {
        ArrayList<XuatChieu> list = xuatChieuDAO.getDSXuatChieu();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerPhim.setLayoutManager(linearLayoutManager);


        KhachHang_Phim_Adapter adapter = new KhachHang_Phim_Adapter( getContext(), xuatChieuDAO, list);
        recyclerPhim.setAdapter(adapter);
    }
}
