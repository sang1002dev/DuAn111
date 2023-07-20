package sanghvph30000.fpoly.duan111.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import sanghvph30000.fpoly.duan111.Database.DbHelper;
import sanghvph30000.fpoly.duan111.Model.User;

public class DAOUser {
    private SQLiteDatabase database;
    private DbHelper dbHelper;
    public DAOUser(Context context) {
        dbHelper = new DbHelper(context); // Khởi tạo DbHelper
        database = dbHelper.getWritableDatabase();
    }

//    Add User
    public long insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put("FullName",user.getFullName());
        values.put("TenDN", user.getTenDN());
        values.put("MaChucVu", user.getMaChucVu());
        values.put("Password", user.getPassword());
        values.put("SDT", user.getSDT());
        return database.insert("User", null, values);
    }

    // update User
    public boolean updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put("TenDN", user.getTenDN());
        if (!user.getPassword().isEmpty()){
            values.put("Password", user.getPassword());
        }
        values.put("SDT", user.getSDT());

        long check = database.update("User", values, "MaUser=?", new String[]{String.valueOf(user.getID_User())});
        if (check == -1){
            return false;
        }
        else {
            return true;
        }
    }

    //    Check Đăng nhập tài khoản
    public ArrayList<User> checkLogin(String TenDN, String password) {
        String sql = "SELECT * FROM User WHERE TenDN=? AND Password=?";
        ArrayList<User> list = getData(sql, TenDN, password);
        return list;
    }

//    Check tồn tại userName;
    public int checkValid(String TenDN) {
        String sql = "SELECT * FROM User WHERE TenDN=?";
        ArrayList<User> list = getData(sql, TenDN);
        return list.size();
    }

    //    Lấy thông tin User theo ID
    public User getUser(int inputId) {
        User user = null;
        Cursor cursor = database.rawQuery("SELECT User.mauser,User.fullName, User.TenDN, ChucVu.MaChucVu, User.password, User.sdt FROM User, ChucVu WHERE User.MaChucVu = ChucVu.MaChucVu and User.MaUser = ?", new String[]{String.valueOf(inputId)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                int maUser = cursor.getInt(0);
                String fullName = cursor.getString(1);
                String TenDN = cursor.getString(2);
                int MaChucVu = cursor.getInt(3);
                String passWord = cursor.getString(4);
                String soDT = cursor.getString(5);
                user = new User(maUser,fullName, TenDN, MaChucVu,passWord,  soDT );
            } while (cursor.moveToNext());
        }
        return user;
    }

    //    Lấy danh sách User
    public ArrayList<User> getAllUser() {
        ArrayList<User> listUser = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT User.mauser,User.fullName, User.TenDN, ChucVu.MaChucVu,User.password, User.sdt FROM User, ChucVu WHERE User.MaChucVu = ChucVu.MaChucVu", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                int maUser = cursor.getInt(0);
                String fullName = cursor.getString(1);
                String TenDN = cursor.getString(2);
                int MaChucVu = cursor.getInt(3);
                String passWord = cursor.getString(4);
                String soDT = cursor.getString(5);
                listUser.add(new User(maUser,fullName, TenDN, MaChucVu,passWord,  soDT ));
            } while (cursor.moveToNext());
        }
        return listUser;
    }

//    Xóa User
    public boolean deleteUser(int maUser){
        long check = database.delete("User", "mauser = ?", new String[]{String.valueOf(maUser)});
        if (check == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<User> getData(String sql, String... selectionAGrs) {
        ArrayList<User> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionAGrs);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                int maUser = cursor.getInt(0);
                String fullName = cursor.getString(1);
                String TenDN = cursor.getString(2);
                int MaChucVu = cursor.getInt(3);
                String passWord = cursor.getString(4);
                String SDT = cursor.getString(5);
                list.add(new User(maUser,fullName, TenDN, MaChucVu,passWord,  SDT ));
            } while (cursor.moveToNext());
        }
        return list;
    }

}
