package fpoly.cp17302_3.appbooktickets.TaiKhoan.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fpoly.cp17302_3.appbooktickets.DAO.ThanhVienDAO;
import fpoly.cp17302_3.appbooktickets.R;

public class Register_Fragment extends Fragment {
    ThanhVienDAO thanhVienDAO;
    EditText edtHoTen, edtSDT, edtEmail, edtMatKhau, edtLoaiTaiKhoan;
    Button btnDangKy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangky, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtHoTen = view.findViewById(R.id.edtHoTen);
        edtSDT = view.findViewById(R.id.edtSDT);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtMatKhau = view.findViewById(R.id.edtMatKhau);
        btnDangKy = view.findViewById(R.id.btnDangKy);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhVienDAO = new ThanhVienDAO(getContext());
                String SDT = edtSDT.getText().toString();
                String Email = edtEmail.getText().toString();
                String HoTen = edtHoTen.getText().toString();
                String MatKhau = edtMatKhau.getText().toString();

                boolean check = thanhVienDAO.themThanhVien(SDT, Email, HoTen, MatKhau , "khach");

                if (SDT.length() == 0 || Email.length() == 0 || HoTen.length() == 0 || MatKhau.length() == 0){
                    Toast.makeText(getContext(), "Vui Lòng Không Để Thông Tin Trống", Toast.LENGTH_SHORT).show();
                }else {
                    if (check == true) {
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
