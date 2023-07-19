package sanghvph30000.fpoly.duan111.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import sanghvph30000.fpoly.duan111.Adapter.AdapterTKNV;
import sanghvph30000.fpoly.duan111.DAO.DAOLuuHD;
import sanghvph30000.fpoly.duan111.Model.LuuHoaDon;
import sanghvph30000.fpoly.duan111.R;

public class TKUserFrgm extends Fragment {

    public RecyclerView recycler_TKNV;
    public DAOLuuHD daoLuuHD;
    ArrayList<LuuHoaDon> listNhanVien;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t_k_user_frgm, container, false);

//        Ánh xạ các View;
        ImageView btnBackTKNV = view.findViewById(R.id.btnBackTKNV);
        TextView txtTKNVSoLg = view.findViewById(R.id.txtTKNVSoLg);
        recycler_TKNV = view.findViewById(R.id.recycler_TKNV);
        daoLuuHD = new DAOLuuHD(getContext());

        listNhanVien = daoLuuHD.tkNhanVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_TKNV.setLayoutManager(linearLayoutManager);
        AdapterTKNV adapterTKNV = new AdapterTKNV(getContext(), listNhanVien);
        recycler_TKNV.setAdapter(adapterTKNV);

        txtTKNVSoLg.setText(listNhanVien.size() + " người");

        btnBackTKNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AccountFragment());
            }
        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}