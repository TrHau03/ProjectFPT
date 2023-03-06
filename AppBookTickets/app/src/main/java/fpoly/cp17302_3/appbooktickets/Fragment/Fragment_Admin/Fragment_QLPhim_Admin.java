package fpoly.cp17302_3.appbooktickets.Fragment.Fragment_Admin;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import fpoly.cp17302_3.appbooktickets.Adapter.Admin_QLPhim_Adapter;
import fpoly.cp17302_3.appbooktickets.DAO.PhimDAO;
import fpoly.cp17302_3.appbooktickets.DAO.TheLoaiDAO;
import fpoly.cp17302_3.appbooktickets.Model.Phim;
import fpoly.cp17302_3.appbooktickets.Model.TheLoai;
import fpoly.cp17302_3.appbooktickets.R;

public class Fragment_QLPhim_Admin extends Fragment {

    PhimDAO phimDAO;
    RecyclerView recyclerPhim;
    FloatingActionButton floatAdd;
    ImageView ivHinhAnh;
    Spinner spinnerTheLoai;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef, mountainsRef;
    String link;
    String hinhAnh = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_qlphim, container, false);

        storage = FirebaseStorage.getInstance("gs://appbooktickets.appspot.com");
        recyclerPhim = view.findViewById(R.id.recyclerDangChieu);
        storageRef = storage.getReference();
        phimDAO = new PhimDAO(getContext());
        floatAdd = view.findViewById(R.id.floatAdd);
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();
            }
        });
        phimDAO = new PhimDAO(getContext());
        loadData();


        return view;
    }


    private ArrayList<HashMap<String, Object>> getDS() {
        ArrayList<Phim> list = phimDAO.getDSPhim();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Phim phim : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maphim", phim.getMaphim());
            hs.put("tenphim", phim.getTenphim());
            hs.put("hinhanh", phim.getHinhanh());
            listHM.add(hs);
        }
        return listHM;
    }
    private ArrayList<HashMap<String, Object>> getDSLoai(){
        TheLoaiDAO theLoaiDAO = new TheLoaiDAO(getContext());
        ArrayList<TheLoai> list = theLoaiDAO.getDSTheLoai();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (TheLoai loai : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", loai.getMaloai());
            hs.put("tenloai", loai.getTenloai());
            listHM.add(hs);
        }
        return listHM;
    }

    private void xincapquyen() {

        Boolean allowed = ActivityCompat
                .checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
        if (allowed) {
            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");
            upLoadImg.launch(pickIntent);
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
        }
    }
    ActivityResultLauncher<Intent> upLoadImg= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null) {
                        Uri imageUri = result.getData().getData();
                        Glide.with(getContext())
                                .load(imageUri)
                                .into(ivHinhAnh);
                        link=imageUri.toString();
                    }
                }
            }
    );
    private void loadData() {
        ArrayList<Phim> list = phimDAO.getDSPhim();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext() , 1);
        recyclerPhim.setLayoutManager(gridLayoutManager);
        recyclerPhim.setFocusable(false);
        recyclerPhim.setNestedScrollingEnabled(false);

        Admin_QLPhim_Adapter adapter = new Admin_QLPhim_Adapter(list, getContext(), phimDAO, getDS());
        recyclerPhim.setAdapter(adapter);

    }

    private void showdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themphim, null);
        builder.setView(view);

        EditText edtTenPhim = view.findViewById(R.id.edtTenPhim);
        ivHinhAnh = view.findViewById(R.id.ivHinhAnh);
        spinnerTheLoai = view.findViewById(R.id.spnTheLoai);
        SimpleAdapter simpleAdapterLoai = new SimpleAdapter(
                getContext(),
                getDSLoai(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spinnerTheLoai.setAdapter(simpleAdapterLoai);

        ivHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xincapquyen();
            }
        });
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // Get the data from an ImageView as bytes
                Calendar calendar = Calendar.getInstance();

                mountainsRef = storageRef.child("ImagePhim" + calendar.getTimeInMillis() + ".png");
                ivHinhAnh.setDrawingCacheEnabled(true);
                ivHinhAnh.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable)ivHinhAnh.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...
                        Toast.makeText(getContext(), "app thành công", Toast.LENGTH_SHORT).show();
                        Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();
                        Log.d("Tag", "onSuccess: " + downloadUri);
                        mountainsRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String tenphim = edtTenPhim.getText().toString();
                                HashMap<String, Object> hs = (HashMap<String, Object>) spinnerTheLoai.getSelectedItem();
                                int maloai = (int) hs.get("maloai");
                                hinhAnh = uri + "";
                                boolean check = phimDAO.themPhim(tenphim, hinhAnh, maloai );
                                if (check == true) {
                                    Toast.makeText(getContext(), "Thêm ảnh thành công", Toast.LENGTH_SHORT).show();
                                    loadData();
                                } else {
                                    Toast.makeText(getContext(), "Thêm ảnh thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });



            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
