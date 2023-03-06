package fpoly.cp17302_3.appbooktickets.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.cp17302_3.appbooktickets.DaTaBase.DBHelper;
import fpoly.cp17302_3.appbooktickets.Model.Phim;

public class PhimDAO {
    DBHelper dbHelper;
    public PhimDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<Phim> getDSPhim(){
        ArrayList<Phim> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT p.maphim ,p.tenphim, p.hinhanh, tl.tenloai FROM PHIM p , THELOAI tl WHERE p.maloai = tl.maloai ", null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                list.add(new Phim(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themPhim(String tenphim, String hinhanh, int maloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT p.tenphim, p.hinhanh, tl.maloai FROM PHIM p , THELOAI tl WHERE p.maloai = tl.maloai", null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("tenphim", tenphim);
        contentValues.put("hinhanh", hinhanh);
        contentValues.put("maloai", maloai );

        long check = sqLiteDatabase.insert("PHIM", null, contentValues);
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean xoaPhim(int maphim){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        long check = sqLiteDatabase.delete("PHIM", "maphim = ?", new String[]{String.valueOf(maphim)});
        if (check == -1){
            return false;
            //0: Xóa thất bại
        }else {
            return true;
            //1: Xóa thành công
        }
    }
}
