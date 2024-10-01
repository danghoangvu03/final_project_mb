package com.example.electricbillmanagement;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;
import com.example.electricbillmanagement.DatabaseHelper;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo lớp DatabaseHelper
        myDb = new DatabaseHelper(this);

        // Ví dụ: Thêm một khách hàng mới
        boolean isInserted = myDb.insertCustomer("Ramesh", "202401", "Ahmedabad", 2000.0, 1);
        if (isInserted)
            Toast.makeText(MainActivity.this, "Thêm khách hàng thành công", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Thêm khách hàng thất bại", Toast.LENGTH_LONG).show();
    }
}
