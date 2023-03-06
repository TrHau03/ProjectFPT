package fpoly.cp17302_3.appbooktickets.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.cp17302_3.appbooktickets.DaTaBase.DBHelper;
import fpoly.cp17302_3.appbooktickets.Model.Ve;

public class VeDAO {
    DBHelper dbHelper;
    ArrayList<Ve> list;
    public VeDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<Ve> getDSVe(){
        list= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT v.mave, xc.thoigianchieu, v.soghe, v.giave, xc.ngaychieu, v.soluong, p.tenphim, tl.tenloai, p.hinhanh,  xc.maxuatchieu, tv.matv FROM VE v, XUATCHIEU xc, PHIM p, THELOAI tl, THANHVIEN tv WHERE v.maxuatchieu = xc.maxuatchieu AND v.matv = tv.matv AND xc.maphim = p.maphim AND p.maloai = tl.maloai", null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                list.add(new Ve(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4) , cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),cursor.getString(9), cursor.getInt(10)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themve( String Ghe, String SoLuong, String Gia, String Matv, String Maxuatchieu){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("soghe", Ghe);
        contentValues.put("soluong", SoLuong);
        contentValues.put("giave", Gia);
        contentValues.put("matv", Matv);
        contentValues.put("maxuatchieu", Maxuatchieu);

        long check = sqLiteDatabase.insert("VE", null, contentValues);
        if(check==-1)
            return false;
        return true;
    }

    public boolean xoave(int mave){

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        long check = sqLiteDatabase.delete("VE", "mave = ? ", new String[]{String.valueOf(mave)});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
}
