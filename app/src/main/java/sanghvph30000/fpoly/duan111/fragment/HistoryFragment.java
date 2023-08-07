package sanghvph30000.fpoly.duan111.fragment;

import android.os.Bundle;
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

import sanghvph30000.fpoly.duan111.Adapter.LichSuAdapter;
import sanghvph30000.fpoly.duan111.DAO.DAOLuuHD;
import sanghvph30000.fpoly.duan111.Model.LuuHoaDon;
import sanghvph30000.fpoly.duan111.R;


public class HistoryFragment extends Fragment {

    private RecyclerView recyclerLichSu;
    private LichSuAdapter lichSuAdapter;
    private List<LuuHoaDon> listLuuHoaDon = new ArrayList<>();

    ImageView btnBackLichSu;



    private void loadHoaDonData() {
        DAOLuuHD daoLuuHD = new DAOLuuHD(getContext());

        lichSuAdapter = new LichSuAdapter(listLuuHoaDon,getContext());
        lichSuAdapter.notifyDataSetChanged();
        recyclerLichSu.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerLichSu.setAdapter(lichSuAdapter);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
