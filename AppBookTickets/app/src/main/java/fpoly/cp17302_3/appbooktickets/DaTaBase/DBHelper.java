package fpoly.cp17302_3.appbooktickets.DaTaBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "CINEMA", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String dbTheLoai = "create table THELOAI (maloai integer primary key autoincrement, " +
                "tenloai text" +
                ")";
        db.execSQL(dbTheLoai);


        String dbPhim = "create table PHIM(maphim integer primary key autoincrement," +
                " tenphim text," +
                " hinhanh text, " +
                "maloai integer references THELOAI(maloai)" +
                ")";
        db.execSQL(dbPhim);


        String dbXuatChieu = "create table XUATCHIEU(maxuatchieu integer primary key autoincrement, " +
                "ngaychieu text, " +
                "thoigianchieu text," +
                "maphim integer references PHIM(maphim)" +
                ")";
        db.execSQL(dbXuatChieu);


        String dbThanhVien = "create table THANHVIEN(matv integer primary key autoincrement," +
                "sodienthoai text, " +
                "email text, " +
                "tentv text, " +
                "matkhau text, " +
                "loaitaikhoan text" +
                ")";
        db.execSQL(dbThanhVien);


        String dbVe = "create table VE(mave integer primary key autoincrement," +
                "soghe text," +
                "soluong text," +
                "giave text, " +
                "matv integer references THANHVIEN(matv)," +
                "maxuatchieu integer references XUATCHIEU(maxuatchieu) )";
        db.execSQL(dbVe);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS THELOAI");
            db.execSQL("DROP TABLE IF EXISTS PHIM");
            db.execSQL("DROP TABLE IF EXISTS XUATCHIEU");
            db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            db.execSQL("DROP TABLE IF EXISTS VE");
            onCreate(db);
        }
    }
}
