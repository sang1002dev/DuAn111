package sanghvph30000.fpoly.duan111.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import sanghvph30000.fpoly.duan111.Adapter.DonHangAdapter;
import sanghvph30000.fpoly.duan111.DAO.DAOLuuHD;
import sanghvph30000.fpoly.duan111.Model.LuuHoaDon;
import sanghvph30000.fpoly.duan111.R;

public class DonHangFragment extends Fragment {
    private RecyclerView recyclerDonHang;
    private DonHangAdapter donHangAdapter;
    private List<LuuHoaDon> listLuuHoaDon = new ArrayList<>();
    private  List<String> temTrangThaiList = new ArrayList<>();
    public static final String TAG = "DonHangFragment";
    ImageView btnBackDonHang;


    }




