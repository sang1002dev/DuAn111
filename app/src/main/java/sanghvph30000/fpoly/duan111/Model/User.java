package sanghvph30000.fpoly.duan111.Model;

public class User {
    int ID_User;
    String fullName;
    String TenDN;
    int MaChucVu;
    String password;
    String SDT;


    public User() {
    }

    public User(int ID_User, String fullName, String tenDN, int maChucVu, String password, String SDT) {
        this.ID_User = ID_User;
        this.fullName = fullName;
        TenDN = tenDN;
        MaChucVu = maChucVu;
        this.password = password;
        this.SDT = SDT;
    }

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTenDN() {
        return TenDN;
    }

    public void setTenDN(String tenDN) {
        TenDN = tenDN;
    }

    public int getMaChucVu() {
        return MaChucVu;
    }

    public void setMaChucVu(int maChucVu) {
        MaChucVu = maChucVu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
