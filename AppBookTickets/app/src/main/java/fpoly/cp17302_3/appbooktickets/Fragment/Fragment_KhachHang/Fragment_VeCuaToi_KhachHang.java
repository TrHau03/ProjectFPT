package fpoly.cp17302_3.appbooktickets.Fragment.Fragment_KhachHang;

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

import fpoly.cp17302_3.appbooktickets.Adapter.KhachHang_VeCuaToi_Adapter;
import fpoly.cp17302_3.appbooktickets.DAO.VeDAO;
import fpoly.cp17302_3.appbooktickets.Model.Ve;
import fpoly.cp17302_3.appbooktickets.R;

public class Fragment_VeCuaToi_KhachHang extends Fragment {
    RecyclerView recyclerVeCuaToi;
    VeDAO veDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vecuatoi_khachhang, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerVeCuaToi = view.findViewById(R.id.recyclerVeCuaToi);

        veDAO = new VeDAO(getContext());

        loadData();

    }

    private void loadData() {
        ArrayList<Ve> list = veDAO.getDSVe();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerVeCuaToi.setLayoutManager(linearLayoutManager);
        KhachHang_VeCuaToi_Adapter adapter = new KhachHang_VeCuaToi_Adapter(getContext(), list, veDAO);
        recyclerVeCuaToi.setAdapter(adapter);
    }
}
