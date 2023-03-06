package fpoly.cp17302_3.appbooktickets.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fpoly.cp17302_3.appbooktickets.DAO.PhimDAO;
import fpoly.cp17302_3.appbooktickets.DAO.XuatChieuDAO;
import fpoly.cp17302_3.appbooktickets.Model.Phim;
import fpoly.cp17302_3.appbooktickets.Model.XuatChieu;
import fpoly.cp17302_3.appbooktickets.R;

public class Admin_QLXuatChieu_Adapter extends RecyclerView.Adapter<Admin_QLXuatChieu_Adapter.ViewHolder>{
    ArrayList<XuatChieu> list;
    Context context;
    XuatChieuDAO xuatChieuDAO;

    public Admin_QLXuatChieu_Adapter(ArrayList<XuatChieu> list, Context context, XuatChieuDAO xuatChieuDAO) {
        this.list = list;
        this.context = context;
        this.xuatChieuDAO = xuatChieuDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.admin_item_qlxuatchieu, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getHinhanh());
        Glide.with(context).load(uri).into(holder.ivHinhAnh);
        holder.txtGio.setText(list.get(position).getThoigianchieu());
        holder.txtNgay.setText(list.get(position).getNgaychieu());
        holder.txtTenPhim.setText(list.get(position).getTenphim());
        holder.txtTenLoai.setText(list.get(position).getTenloai());

        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = xuatChieuDAO.xoaXuatChieu(list.get(holder.getAdapterPosition()).getMaxuatchieu());
                if (check == true){
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtGio, txtNgay, txtTenPhim, txtTenLoai;
        ImageView ivDel, ivHinhAnh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtGio = itemView.findViewById(R.id.txtGio);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTenLoai = itemView.findViewById(R.id.txtLoai);
            ivDel = itemView.findViewById(R.id.ivDel);
            txtTenPhim = itemView.findViewById(R.id.txtTen);
            ivHinhAnh = itemView.findViewById(R.id.ivHinhAnh);
        }
    }

    private void loadData(){
        list.clear();
        list = xuatChieuDAO.getDSXuatChieu();
        notifyDataSetChanged();
    }
}
