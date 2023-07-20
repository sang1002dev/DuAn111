package sanghvph30000.fpoly.duan111.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DatabaseName";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
// Bảng thể loại
        String createTableTheLoai = "CREATE TABLE THELOAI(MaLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenLoai TEXT);";
        db.execSQL(createTableTheLoai);
        db.execSQL("INSERT INTO THELOAI VALUES(1, 'Iphone'), (2, 'SamSung'), (3, 'Oppo'), (4, 'Ipad'), (5, 'Xiao Me');");
//Bảng sản phẩm
        String createTableSanPham = ("CREATE TABLE SanPham(\n" +
                "MaSanPham INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "image BLOG,\n" +
                "TenSanPham TEXT,\n" +
                "donGia double,\n" +
                "MaLoai INTEGER REFERENCES THELOAI(MaLoai),\n" +
                "MoTa TEXT\n" +
                ");");
        db.execSQL(createTableSanPham);
// Bảng chức vụ
        String createTableChucVu = "CREATE Table ChucVu(\n" +
                "MaChucVu INTEGER PRIMARY KEY,\n" +
                "TenChucVu TEXT\n" +
                ");";
        db.execSQL(createTableChucVu);
        db.execSQL(InsertInto.insert_chucvu);
// Bảng User
        String tableUser = "CREATE Table User (\n" +
                "maUser INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "fullName TEXT,\n" +
                "TenDN TEXT,\n" +
                "MaChucVu INTEGER REFERENCES ChucVu(Machucvu),\n" +
                "Password TEXT,\n" +
                "SDT TEXT\n" +
                ");";
        db.execSQL(tableUser);
        db.execSQL(InsertInto.insert_user);
// Bảng hóa đơn
        String tableHoaDon = "CREATE Table HoaDon (\n" +
                "MaHoaDon INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "maUser INTEGER REFERENCES User(maUser),\n" +
                "TenKhachHang TEXT,\n" +
                "NgayLapHD TEXT,\n" +
                "MaGioHang INTEGER\n" +
                ");";
        db.execSQL(tableHoaDon);
// Bảng giỏ hàng
        String tableGioHang = "CREATE Table GioHang (\n" +
                "MaGioHang INTEGER,\n" +
                "MaSanPham INTEGER REFERENCES SanPham(MaSanPham),\n" +
                "soLuong INTEGER,\n" +
                "DonGia DOUBLE\n" +
                ");";
        db.execSQL(tableGioHang);
// Bảng lưu hóa đơn
        String tableLuuHoaDon = "CREATE Table LuuHoaDon (\n" +
                "maLuu INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "MaHoaDon INTEGER REFERENCES HoaDon(MaHoaDon),\n" +
                "maUser INTEGER REFERENCES User(MaUser),\n" +
                "soLuong INTEGER,\n" +
                "donGia DOUBLE\n," +
                "thanhTien DOUBLE\n" +
                ");";
        db.execSQL(tableLuuHoaDon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropLoaiSP = "drop table if exists THELOAI";
        db.execSQL(dropLoaiSP);
        String dropSanPham = "drop table if exists SanPham";
        db.execSQL(dropSanPham);
        String dropChucVu = "drop table if exists ChucVu";
        db.execSQL(dropChucVu);
        String dropUser = "drop table if exists User";
        db.execSQL(dropUser);
        String dropHoaDon = "drop table if exists HoaDon";
        db.execSQL(dropHoaDon);
        String dropLuuHoaDon = "drop table if exists LuuHoaDon";
        db.execSQL(dropLuuHoaDon);
        String dropGioHang = "drop table if exists GioHang";
        db.execSQL(dropGioHang);
    }

}
