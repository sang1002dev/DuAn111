package sanghvph30000.fpoly.duan111;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import sanghvph30000.fpoly.duan111.Database.DbHelper;

public class DangKiActivity extends AppCompatActivity {
    private EditText edtTenDangNhap, edtSDT, edtMatKhau, edtMatKhau1;
    private CheckBox chkNhoMK;
    private Button btnDangki;
    private TextView textView;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        dbHelper = new DbHelper(this);


        // Ánh xạ các thành phần giao diện từ layout XML
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtSDT = findViewById(R.id.edtSDT);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtMatKhau1 = findViewById(R.id.edtMatKhau1);
        chkNhoMK = findViewById(R.id.chkNhoMK1);
        textView = findViewById(R.id.tvDaco);
        btnDangki = findViewById(R.id.btnDangki);

        // Đăng ký sự kiện click cho button Đăng kí
        btnDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý logic khi người dùng nhấn vào button Đăng kí
                // Lấy giá trị từ các EditText
                String tenDangNhap = edtTenDangNhap.getText().toString();
                String sdt = edtSDT.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                String nhapLaiMatKhau = edtMatKhau1.getText().toString();
                boolean luuMatKhau = chkNhoMK.isChecked();

                // Tiến hành kiểm tra và xử lý dữ liệu

                // Kiểm tra xem các trường đã được nhập đầy đủ hay không
                if (tenDangNhap.isEmpty() || sdt.isEmpty() || matKhau.isEmpty() || nhapLaiMatKhau.isEmpty()) {
                    // Hiển thị thông báo lỗi
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!matKhau.equals(nhapLaiMatKhau)) {
                    // Hiển thị thông báo lỗi nếu mật khẩu không khớp
                    Toast.makeText(getApplicationContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    // Các trường dữ liệu đã được nhập đầy đủ và mật khẩu khớp, tiến hành xử lý đăng ký tài khoản
                    long userId = addUserToDatabase(tenDangNhap, sdt, matKhau);
                    if (userId != -1) {
                        // Đăng ký thành công
                        Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), DangNhapAct.class);
                        startActivity(intent);
                    } else {
                        // Đăng ký thất bại
                        Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DangNhapAct.class);
                startActivity(intent);
            }
        });
    }

//    // Thêm người dùng vào cơ sở dữ liệu
    private long addUserToDatabase(String tenDangNhap, String sdt, String matKhau) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("TenDN", tenDangNhap);
        values.put("Password", matKhau);
        values.put("SDT", sdt);

        long userId = db.insert("User", null, values);

        db.close();

        return userId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}
