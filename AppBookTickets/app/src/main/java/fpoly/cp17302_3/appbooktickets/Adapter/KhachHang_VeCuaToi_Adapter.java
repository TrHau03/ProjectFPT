package fpoly.cp17302_3.appbooktickets.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fpoly.cp17302_3.appbooktickets.DAO.VeDAO;
import fpoly.cp17302_3.appbooktickets.Model.Ve;
import fpoly.cp17302_3.appbooktickets.R;

public class KhachHang_VeCuaToi_Adapter extends RecyclerView.Adapter<KhachHang_VeCuaToi_Adapter.ViewHolder>{
Context context;
ArrayList<Ve> list;
VeDAO veDAO;

    public KhachHang_VeCuaToi_Adapter(Context context, ArrayList<Ve> list, VeDAO veDAO) {
        this.context = context;
        this.list = list;
        this.veDAO = veDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.khachhang_item_vecuatoi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getHinhanh());
        Glide.with(context).load(uri).into(holder.ivIMG);

        holder.txtSoGhe.setText("Số Ghê : " + list.get(position).getSoghe());
        holder.txtGia.setText("Gía : " + list.get(position).getGiave());
        holder.txtSoLuong.setText("Số Lượng" + list.get(position).getSoluong());
        holder.txtThoiGianDat.setText("Giờ : " + list.get(position).getThoigiandat());
        holder.txtNgayChieu.setText("Ngày : " + list.get(position).getNgaychieu());
        holder.txtTenPhim.setText("Tên : " + list.get(position).getTenphim());
        holder.txtTheLoai.setText("Loai : " + list.get(position).getTheloai());

        holder.btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mave = list.get(holder.getAdapterPosition()).getMave();

                VeDAO veDAO = new VeDAO(context);

                boolean check = veDAO.xoave(mave);
                if (check == true){
                    Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        TextView txtSoGhe, txtSoLuong, txtGia, txtThoiGianDat, txtNgayChieu, txtTenPhim, txtTheLoai;
        Button btn_huy;
        ImageView ivIMG;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSoGhe = itemView.findViewById(R.id.txtSoGhe);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtGia = itemView.findViewById(R.id.txtGia);
            txtThoiGianDat = itemView.findViewById(R.id.txtThoiGianDat);
            txtNgayChieu = itemView.findViewById(R.id.txtNgayChieu);
            ivIMG = itemView.findViewById(R.id.ivIMG);
            linearLayout = itemView.findViewById(R.id.linearLayout);

            txtTenPhim = itemView.findViewById(R.id.txtTenPhim);
            txtTheLoai = itemView.findViewById(R.id.txtTheLoai);

            btn_huy = itemView.findViewById(R.id.btn_huy);
        }
    }

    public void loadData(){
        list.clear();
        list = veDAO.getDSVe();
        notifyDataSetChanged();
    }
}
