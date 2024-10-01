package com.example.electricbillmanagement;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Final_Mobile.db"; // Tên cơ sở dữ liệu
    private static final int DATABASE_VERSION = 1; //

    // Constructor cho DatabaseHelper
    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Phương thức này được gọi khi cơ sở dữ liệu lần đầu tiên được tạo
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng customer (khách hàng)
        String CREATE_CUSTOMER_TABLE = "CREATE TABLE customer ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT NOT NULL, "
                + "YYYYMM TEXT NOT NULL, "
                + "ADDRESS TEXT NOT NULL, "
                + "USED_NUM_ELECTRIC REAL NOT NULL, "
                + "ELEC_USER_TYPE_ID INTEGER NOT NULL)";
        db.execSQL(CREATE_CUSTOMER_TABLE);

        // Tạo bảng electric_user_type (loại khách hàng sử dụng điện)
        String CREATE_USER_TYPE_TABLE = "CREATE TABLE electric_user_type ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "ELEC_USER_TYPE_NAME TEXT NOT NULL, "
                + "UNIT_PRICE REAL NOT NULL)";
        db.execSQL(CREATE_USER_TYPE_TABLE);
    }

    // Phương thức này được gọi khi phiên bản cơ sở dữ liệu thay đổi
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại và tạo lại bảng mới
        db.execSQL("DROP TABLE IF EXISTS customer");
        db.execSQL("DROP TABLE IF EXISTS electric_user_type");
        onCreate(db);
    }

    //Thêm khách hàng (Insert Customer)
    public boolean insertCustomer(String name, String yyyymm, String address, double usedNumElectric, int elecUserTypeId) {
        // Mở cơ sở dữ liệu để ghi dữ liệu
        SQLiteDatabase db = this.getWritableDatabase();

        // Đối tượng ContentValues lưu các cặp khóa - giá trị
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("YYYYMM", yyyymm);
        contentValues.put("ADDRESS", address);
        contentValues.put("USED_NUM_ELECTRIC", usedNumElectric);
        contentValues.put("ELEC_USER_TYPE_ID", elecUserTypeId);

        // Thêm dữ liệu vào bảng customer
        long result = db.insert("customer", null, contentValues);

        // Nếu thêm thành công sẽ trả về true, ngược lại false
        return result != -1;
    }

    //lấy danh sách khách hàng
    public Cursor getAllCustomers() {
        SQLiteDatabase db = this.getReadableDatabase();
        // Truy vấn lấy tất cả dữ liệu từ bảng customer
        return db.rawQuery("SELECT * FROM customer", null);
    }

    //Cập nhật thông tin khách hàng (Update Customer)
    public boolean updateCustomer(int id, String name, String yyyymm, String address, double usedNumElectric, int elecUserTypeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("YYYYMM", yyyymm);
        contentValues.put("ADDRESS", address);
        contentValues.put("USED_NUM_ELECTRIC", usedNumElectric);
        contentValues.put("ELEC_USER_TYPE_ID", elecUserTypeId);

        // Cập nhật dữ liệu dựa trên ID
        int result = db.update("customer", contentValues, "ID = ?", new String[]{String.valueOf(id)});

        // Kiểm tra nếu cập nhật thành công
        return result > 0;
    }


    //Xóa khách hàng (Delete Customer)
    public boolean deleteCustomer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("customer", "ID = ?", new String[]{String.valueOf(id)});

        // Trả về true nếu xóa thành công
        return result > 0;
    }


}
