package com.example.electricbillmanagement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddCustomerActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTextName, editTextAddress, editTextUsedNumElectric;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        // Khởi tạo DatabaseHelper
        myDb = new DatabaseHelper(this);

        // Liên kết các thành phần giao diện
        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextUsedNumElectric = findViewById(R.id.editTextUsedNumElectric);
        buttonSave = findViewById(R.id.buttonSave);

        // Thiết lập sự kiện cho nút "Lưu"
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String address = editTextAddress.getText().toString();
                double usedNumElectric = Double.parseDouble(editTextUsedNumElectric.getText().toString());

                boolean isInserted = myDb.insertCustomer(name, "202401", address, usedNumElectric, 1);
                if (isInserted) {
                    Toast.makeText(AddCustomerActivity.this, "Thêm khách hàng thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddCustomerActivity.this, "Thêm khách hàng thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

