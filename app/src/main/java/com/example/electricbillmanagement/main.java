package com.example.electricbillmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

    public class main extends AppCompatActivity {

    DatabaseHelper myDb; // Khởi tạo DatabaseHelper
    ListView listViewCustomers;
    Button buttonAddCustomer, buttonSearchCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo DatabaseHelper
        myDb = new DatabaseHelper(this);

        // Liên kết các thành phần giao diện
        listViewCustomers = findViewById(R.id.listViewCustomers);
        buttonAddCustomer = findViewById(R.id.buttonAddCustomer);
        //buttonSearchCustomer = findViewById(R.id.buttonSearchCustomer);

        // Hiển thị danh sách khách hàng
        displayCustomerList();

        // Thiết lập sự kiện cho nút "Thêm Khách Hàng"
            buttonAddCustomer.setOnClickListener(v -> {
            Intent intent = new Intent(main.this, AddCustomerActivity.class);
            startActivity(intent);
        });

        // Thiết lập sự kiện cho nút "Tìm Kiếm Khách Hàng"
       // buttonSearchCustomer.setOnClickListener(v -> {
        //    Intent intent = new Intent(main.this, SearchActivity.class);
       //     startActivity(intent);
      //  });

        // Thiết lập sự kiện khi nhấn vào một khách hàng trong danh sách
     //   listViewCustomers.setOnItemClickListener((parent, view, position, id) -> {
       //     Intent intent = new Intent(main.this, CustomerDetailsActivity.class);
       //     intent.putExtra("CUSTOMER_ID", id); // Truyền ID khách hàng qua Intent
       //     startActivity(intent);
       // });
    }

    private void displayCustomerList() {
        // Lấy danh sách khách hàng từ cơ sở dữ liệu
        Cursor cursor = myDb.getAllCustomers();
        ArrayList<String> customerList = new ArrayList<>();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Không có khách hàng", Toast.LENGTH_SHORT).show();
            return;
        }

        // Duyệt qua con trỏ và thêm tên khách hàng vào danh sách
        while (cursor.moveToNext()) {
            String name = cursor.getString(1); // Lấy tên khách hàng từ cột 1
            customerList.add(name);
        }

        // Sử dụng ArrayAdapter để hiển thị danh sách khách hàng
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, customerList);
        listViewCustomers.setAdapter(adapter);
    }
}

