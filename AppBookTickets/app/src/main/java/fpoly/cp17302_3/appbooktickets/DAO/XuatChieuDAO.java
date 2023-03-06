package fpoly.cp17302_3.appbooktickets.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.cp17302_3.appbooktickets.DaTaBase.DBHelper;
import fpoly.cp17302_3.appbooktickets.Model.XuatChieu;

public class XuatChieuDAO {
    DBHelper dbHelper;
    public XuatChieuDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<XuatChieu> getDSXuatChieu(){
        ArrayList<XuatChieu> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT xc.maxuatchieu, xc.ngaychieu, xc.thoigianchieu, tl.tenloai, p.tenphim, p.hinhanh, p.maphim FROM XUATCHIEU xc, PHIM p, THELOAI tl WHERE xc.maphim = p.maphim AND p.maloai = tl.maloai", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new XuatChieu(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4),cursor.getString(5), cursor.getInt(6)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themXuatChieu(String ngaychieu, String thoigianchieu, int maphim){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ngaychieu", ngaychieu);
        contentValues.put("thoigianchieu", thoigianchieu);
        contentValues.put("maphim", maphim);
        long check = sqLiteDatabase.insert("XUATCHIEU", null, contentValues);
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
    public boolean xoaXuatChieu(int maxuatchieu){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("XUATCHIEU", "maxuatchieu = ?", new String[]{String.valueOf(maxuatchieu)});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
}
