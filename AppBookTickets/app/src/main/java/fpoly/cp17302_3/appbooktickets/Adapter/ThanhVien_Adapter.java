package fpoly.cp17302_3.appbooktickets.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.cp17302_3.appbooktickets.DAO.ThanhVienDAO;
import fpoly.cp17302_3.appbooktickets.Model.ThanhVien;
import fpoly.cp17302_3.appbooktickets.R;

public class ThanhVien_Adapter extends RecyclerView.Adapter<ThanhVien_Adapter.ViewHolder>{
    private Context context;
    private ArrayList<ThanhVien> list;
    private ThanhVienDAO thanhVienDAO;
    SharedPreferences sharedPreferences;
    public ThanhVien_Adapter(Context context, ArrayList<ThanhVien> list, ThanhVienDAO thanhVienDAO) {
        this.context = context;
        this.list = list;
        this.thanhVienDAO = thanhVienDAO;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.admin_item_thanhvien, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*sharedPreferences = context.getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matv = sharedPreferences.getString("matv","");
        String sdt = sharedPreferences.getString("sodienthoai", "");
        String email = sharedPreferences.getString("email","");
        String hoten = sharedPreferences.getString("hoten", "");
        String matkhau = sharedPreferences.getString("matkhau","");
        String loaitaikhoan = sharedPreferences.getString("loaitaikhoan", "");*/

        holder.txtMaTV.setText("Mã Thành Viên: " + list.get(position).getMatv());
        holder.txtSDT.setText("Số Điện Thoại: " + list.get(position).getSdt());
        holder.txtEmail.setText("Email: " + list.get(position).getEmail());
        holder.txtHoTen.setText("Họ Tên: " +list.get(position).getTentv());
        holder.txtMatKhau.setText("Mật Khẩu: " + list.get(position).getMatkhau());
        holder.txtLoaiTK.setText("Loại Tài Khoản: " + list.get(position).getLoaitaikhoan());
        list = thanhVienDAO.getDSThanhVien();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txtMaTV, txtSDT, txtEmail, txtHoTen, txtMatKhau, txtLoaiTK;
        ImageView ivEdit, ivDel;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtSDT = itemView.findViewById(R.id.txtSDT);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtMatKhau = itemView.findViewById(R.id.txtMatKhau);
            txtLoaiTK = itemView.findViewById(R.id.txtLoaiTaiKhoan);


        }
    }
}
