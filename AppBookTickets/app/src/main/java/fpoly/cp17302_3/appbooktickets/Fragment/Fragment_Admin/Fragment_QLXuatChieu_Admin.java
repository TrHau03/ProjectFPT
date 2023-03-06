package fpoly.cp17302_3.appbooktickets.Fragment.Fragment_Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.cp17302_3.appbooktickets.Adapter.Admin_QLXuatChieu_Adapter;
import fpoly.cp17302_3.appbooktickets.Adapter.ThanhVien_Adapter;
import fpoly.cp17302_3.appbooktickets.DAO.PhimDAO;
import fpoly.cp17302_3.appbooktickets.DAO.XuatChieuDAO;
import fpoly.cp17302_3.appbooktickets.Model.Phim;
import fpoly.cp17302_3.appbooktickets.Model.XuatChieu;
import fpoly.cp17302_3.appbooktickets.R;

public class Fragment_QLXuatChieu_Admin extends Fragment {
RecyclerView recyclerXuatChieu;
FloatingActionButton floatAdd;
XuatChieuDAO xuatChieuDAO;
Spinner spnTenPhim;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_qlxuatchieu, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerXuatChieu = view.findViewById(R.id.recyclerXuatChieu);
        floatAdd = view.findViewById(R.id.floatAdd);

        xuatChieuDAO = new XuatChieuDAO(getContext());
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showwDiaLog();
            }
        });
        loadData();
    }

    private ArrayList<HashMap<String, Object>> getDSPhim(){
        PhimDAO phimDAO = new PhimDAO(getContext());
        ArrayList<Phim> list = phimDAO.getDSPhim();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Phim phim : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maphim", phim.getMaphim());
            hs.put("hinhanh", phim.getHinhanh());
            hs.put("tenphim", phim.getTenphim());
            hs.put("maloai", phim.getMaloai());
            listHM.add(hs);
        }
        return listHM;
    };
    private void loadData(){
        ArrayList<XuatChieu> list = xuatChieuDAO.getDSXuatChieu();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerXuatChieu.setLayoutManager(linearLayoutManager);
        Admin_QLXuatChieu_Adapter adapter = new Admin_QLXuatChieu_Adapter( list,getContext(), xuatChieuDAO);
        recyclerXuatChieu.setAdapter(adapter);
    }
    private void showwDiaLog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themxuatchieu, null);
        builder.setView(view);

        spnTenPhim = view.findViewById(R.id.spnTenPhim);
        EditText edtGio = view.findViewById(R.id.edtGio);
        EditText edtNgay = view.findViewById(R.id.edtNgay);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSPhim(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenphim"},
                new int[]{android.R.id.text1}
        );
        spnTenPhim.setAdapter(simpleAdapter);


        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Gio = edtGio.getText().toString();
                String Ngay = edtNgay.getText().toString();

                HashMap<String, Object> hs = (HashMap<String, Object>) spnTenPhim.getSelectedItem();
                int maphim = (int) hs.get("maphim");
                boolean check = xuatChieuDAO.themXuatChieu(Ngay, Gio, maphim);

                if (check == true){
                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    loadData();
                } else {
                    Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
