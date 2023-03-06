package fpoly.cp17302_3.appbooktickets.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fpoly.cp17302_3.appbooktickets.DAO.PhimDAO;
import fpoly.cp17302_3.appbooktickets.DAO.XuatChieuDAO;
import fpoly.cp17302_3.appbooktickets.Main_Admin;
import fpoly.cp17302_3.appbooktickets.Model.Phim;
import fpoly.cp17302_3.appbooktickets.Model.XuatChieu;
import fpoly.cp17302_3.appbooktickets.R;

public class KhachHang_Phim_Adapter extends RecyclerView.Adapter<KhachHang_Phim_Adapter.ViewHolder>{
    Context context;
    XuatChieuDAO xuatChieuDAO;
    ArrayList<XuatChieu> list;

    public KhachHang_Phim_Adapter(Context context, XuatChieuDAO xuatChieuDAO, ArrayList<XuatChieu> list) {
        this.context = context;
        this.xuatChieuDAO = xuatChieuDAO;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.khachhang_phim_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getHinhanh());
        Glide.with(context).load(uri).into(holder.ivHinhAnh);

        holder.txtTenPhim.setText(list.get(position).getTenphim());
        holder.txtTheLoai.setText(list.get(position).getTenloai());
        holder.txtGio.setText(list.get(position).getThoigianchieu());
        holder.txtNgay.setText(list.get(position).getNgaychieu());

        holder.btn_DatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maxc = String.valueOf(list.get(position).getMaxuatchieu());
                Intent intent = new Intent("Get Total");
                intent.putExtra("maxc", maxc);
                intent.putExtra("hinhanh", list.get(position).getHinhanh());
                intent.putExtra("ten",list.get(position).getTenphim());
                intent.putExtra("loai",list.get(position).getTenloai());
                intent.putExtra("ngay", list.get(position).getNgaychieu());
                intent.putExtra("gio", list.get(position).getThoigianchieu());

                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                Log.d("TAGDATA", "TAGDATA"+list.get(position).getMaxuatchieu());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenPhim,txtTheLoai,txtGio,txtNgay;
        ImageView ivHinhAnh;
        Button btn_DatVe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTheLoai = itemView.findViewById(R.id.txtTheLoai);
            txtGio = itemView.findViewById(R.id.txtGio);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTenPhim = itemView.findViewById(R.id.txtTenPhim);
            ivHinhAnh = itemView.findViewById(R.id.ivHinhAnh);
            btn_DatVe = itemView.findViewById(R.id.btnDatVe);
        }
    }
}
