package sanghvph30000.fpoly.duan111.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import sanghvph30000.fpoly.duan111.Model.LuuHoaDon;
import sanghvph30000.fpoly.duan111.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.HoaDonViewHolder> {
    private List<LuuHoaDon> listHoaDon;
    private List<String> trangThaiList;
    private List<String> tempTrangThaiList = new ArrayList<>();
    //private List<Boolean> daGiaoHangList = new ArrayList<>(); // Danh sách trạng thái đã giao hàng


    public DonHangAdapter(List<LuuHoaDon> listHoaDon, List<String> trangThaiList,List<String>tempTrangThaiList) {
        this.listHoaDon = listHoaDon;
        this.trangThaiList = trangThaiList;
        this.tempTrangThaiList = tempTrangThaiList;
        //initDaGiaoHangList();
    }

//    private void initDaGiaoHangList() {
//        daGiaoHangList.clear();
//        for (LuuHoaDon hoaDon : listHoaDon) {
//            boolean isDaGiaoHang = hoaDon.getTrangThai().equals("Đã giao hàng") || hoaDon.getTrangThai().equals("Đã thanh toán");
//            daGiaoHangList.add(isDaGiaoHang);
//        }
//    }

    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang, parent, false);
        return new HoaDonViewHolder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
        LuuHoaDon luuHoaDon = listHoaDon.get(position);
        holder.txtTenKhachHang.setText(luuHoaDon.getTenKhachHang());
        holder.txtNgayBan.setText(luuHoaDon.getNgayLapHD());

        holder.txtTongTien.setText(String.format("%,.0fđ", luuHoaDon.getThanhTien()));


//        boolean isDaGiaoHang = daGiaoHangList.get(position);
//        if (isDaGiaoHang) {
//            holder.txtTrangThai.setTextColor(Color.RED);
//        } else {
//            holder.txtTrangThai.setTextColor(Color.GREEN);
//        }


    }

    @Override
    public int getItemCount() {
        return listHoaDon.size();
    }

    public static class HoaDonViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenKhachHang, txtNgayBan, txtSDT, txtDiaChi, txtTongTien, txtTrangThai;
        Button btnXacNhan;
        RecyclerView recycle_donhang;

        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenKhachHang = itemView.findViewById(R.id.txtHDTenKH1);
            txtNgayBan = itemView.findViewById(R.id.txtHDNgayBan1);
            txtSDT = itemView.findViewById(R.id.txtHDSDT1);
            txtDiaChi = itemView.findViewById(R.id.txtHDDiaChi1);
            txtTongTien = itemView.findViewById(R.id.txtHDTongTien1);
            txtTrangThai = itemView.findViewById(R.id.txtHDTrangThai1);
            btnXacNhan = itemView.findViewById(R.id.btnHoaDonXN1);
            recycle_donhang = itemView.findViewById(R.id.recycle_donHang);
            // Không cần sử dụng LinearLayoutManager vì nó đã được sử dụng trong XML của item_lichsu
        }
    }
    public void setTrangThaiList(List<String> trangThaiList) {
        this.trangThaiList = trangThaiList;
    }
    public List<String> getTempTrangThaiList() {
        return tempTrangThaiList;
    }
    // Setter cho danh sách trạng thái tạm thời
    public void setTempTrangThaiList(List<String> tempTrangThaiList) {
        this.tempTrangThaiList = tempTrangThaiList;
    }
}
