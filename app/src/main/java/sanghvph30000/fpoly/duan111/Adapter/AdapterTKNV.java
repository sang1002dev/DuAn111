package sanghvph30000.fpoly.duan111.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import sanghvph30000.fpoly.duan111.DAO.DAOLuuHD;
import sanghvph30000.fpoly.duan111.DAO.DAOUser;
import sanghvph30000.fpoly.duan111.Model.LuuHoaDon;
import sanghvph30000.fpoly.duan111.Model.User;
import sanghvph30000.fpoly.duan111.R;
import sanghvph30000.fpoly.duan111.fragment.ViewUserInforFrgm;

public class AdapterTKNV extends RecyclerView.Adapter<AdapterTKNV.ViewHolder>{

    ArrayList<LuuHoaDon> list;
    Context context;
    DAOLuuHD daoLuuHD;
    DAOUser daoUser;

    public AdapterTKNV(Context context, ArrayList<LuuHoaDon> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_thongkeuser, parent, false);
        daoLuuHD = new DAOLuuHD(view.getContext());
        daoUser = new DAOUser(view.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        Settext

        LuuHoaDon luuHoaDon = list.get(position);
        String index = "";
        if (position < 9){
            index = "0" + (position + 1);
        }
        else{
            index = String.valueOf(position + 1);
        }
        holder.txtTknvSTT.setText(index);
        holder.txtTknvTenNv.setText(luuHoaDon.getTenUser());

//        if (String.valueOf(luuHoaDon.getThanhTien()) != null){
//            double doanhThu = luuHoaDon.getThanhTien();
//            String outTongTien = String.format("%,.0f", doanhThu);
//            if (outTongTien.length() > 4){
//                String subDoanhThu = outTongTien.substring(0, (outTongTien.length() - 4));
//                holder.txtTknvDoanhThu.setText(subDoanhThu + "K VNĐ");
//            }
//            else {
//                holder.txtTknvDoanhThu.setText(outTongTien + "K VNĐ");
//            }
//
//        }
//        else {
//            holder.txtTknvDoanhThu.setText("0 VNĐ");
//        }
//        if (position == (list.size() - 1)){
//            holder.bottomViewTknv.setVisibility(View.GONE);
//        }

        User user = daoUser.getUser(luuHoaDon.getMaUser());

//        Item Click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Load Fragment hiển thị thông tin nhân viên
                loadFragment(new ViewUserInforFrgm(user));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTknvSTT, txtTknvTenNv;
        View bottomViewTknv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTknvSTT = itemView.findViewById(R.id.txtTknvSTT);
            txtTknvTenNv = itemView.findViewById(R.id.txtTknvTenNv);
            bottomViewTknv = itemView.findViewById(R.id.bottomViewTknv);
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
