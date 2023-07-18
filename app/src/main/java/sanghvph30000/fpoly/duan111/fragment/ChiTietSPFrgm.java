package sanghvph30000.fpoly.duan111.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import sanghvph30000.fpoly.duan111.DAO.DAOGioHang;
import sanghvph30000.fpoly.duan111.MainActivity;
import sanghvph30000.fpoly.duan111.Model.GioHang;
import sanghvph30000.fpoly.duan111.Model.SanPham;
import sanghvph30000.fpoly.duan111.R;
import sanghvph30000.fpoly.duan111.fragment.StoreFrgm;


public class ChiTietSPFrgm extends Fragment {

    SanPham sanPham;
    String sizeCheck;
    TextView txtChiTietTenSp, txtChiTietGiaSP, txtChiTietMoTaSP, txtChiTietTongTien, txtChiTietSL;
    ImageView img_sp, img_sp1, btnSoLuongTang, btnSoLuongGiam;
    double donGia = 0;
    int soLuong;
    double tongTien;
    DAOGioHang daoGioHang;

    public ChiTietSPFrgm(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_tiet_s_p_frgm, container, false);
        txtChiTietTenSp = view.findViewById(R.id.txtChiTietTenSp);
        txtChiTietGiaSP = view.findViewById(R.id.txtChiTietGiaSP);
        txtChiTietMoTaSP = view.findViewById(R.id.txtChiTietMoTaSP);
        txtChiTietSL = view.findViewById(R.id.txtChiTietSL);
        img_sp = view.findViewById(R.id.imgCTSanPham);
        img_sp1 = view.findViewById(R.id.imgCTSanPham1);
        txtChiTietTongTien = view.findViewById(R.id.txtChiTietTongTien);
        btnSoLuongTang = view.findViewById(R.id.btnSoLuongTang);
        btnSoLuongGiam = view.findViewById(R.id.btnSoLuongGiam);

        daoGioHang = new DAOGioHang(getContext());


//        Set kích thước Size


//        Set số lượng
        soLuong = 1;
        txtChiTietSL.setText("0" + soLuong);
        btnSoLuongGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soLuong > 1){
                    soLuong --;
                    if (soLuong < 10){
                        txtChiTietSL.setText("0" + soLuong);
                    }
                    else {
                        txtChiTietSL.setText(soLuong + "");
                    }
                    tongTien = tinhTien(soLuong, donGia);
                    String mTinhTien = String.format("%,.0f", tongTien);
                    txtChiTietTongTien.setText(mTinhTien + " VNĐ");
                }
            }
        });

        btnSoLuongTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong++;
                if (soLuong < 10){
                    txtChiTietSL.setText("0" + soLuong);
                }
                else {
                    txtChiTietSL.setText(soLuong + "");
                }
                tongTien = tinhTien(soLuong, donGia);
                String mTinhTien = String.format("%,.0f", tongTien);
                txtChiTietTongTien.setText(mTinhTien + " VNĐ");
            }
        });

//        Set Data cho các View
        txtChiTietTenSp.setText(sanPham.getTenSanPham());
        double giaSP = sanPham.getPrice();
        String mGiaSP = String.format("%,.0f", giaSP);
        txtChiTietGiaSP.setText(mGiaSP + " VNĐ");
        txtChiTietMoTaSP.setText(sanPham.getMota());
        byte[] productsImage = sanPham.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(productsImage, 0, productsImage.length);
        img_sp.setImageBitmap(bitmap);
        img_sp1.setImageBitmap(bitmap);

        tongTien = tinhTien(soLuong, donGia);
        String mTinhTien = String.format("%,.0f", tongTien);
        txtChiTietTongTien.setText(mTinhTien + " VNĐ");

        EditText btnChiTietAddToCart = view.findViewById(R.id.btnChiTietAddToCart);

//        Thêm sự kiện Button Add
        btnChiTietAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHang gioHang = new GioHang(1, sanPham.getId(), soLuong, donGia );
                ArrayList<GioHang> outList = daoGioHang.checkValidGioHang(gioHang);
                if (outList.size() != 0){
//                - Có: Update số lượng
                    GioHang gioHang1 = outList.get(0);
                    int newSL = gioHang1.getSoLuong() + gioHang.getSoLuong();
                    gioHang.setSoLuong(newSL);
                    boolean kiemtra = daoGioHang.updateGioHang(gioHang);
                    if (kiemtra){
                        Toast.makeText(getContext(), "Thêm sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "Update SL Fail!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
//                - Không: Thêm sản phẩm
                    boolean check = daoGioHang.addGiohang(gioHang);
                    if (check){
                        Toast.makeText(getContext(), "Thêm sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "Fail!", Toast.LENGTH_SHORT).show();
                    }
                }
                loadFragment(new StoreFrgm());
                MainActivity.bottomNavigationView.setSelectedItemId(R.id.pageBanHang);
            }
        });

        return view;
    }

    //    Tính tổng tiền
    public double tinhTien(int mSoLuong, double mDonGia){
        double tongTien = 0;
        tongTien = mSoLuong * mDonGia ;
        return tongTien;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}