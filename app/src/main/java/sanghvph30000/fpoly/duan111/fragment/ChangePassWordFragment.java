package sanghvph30000.fpoly.duan111.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sanghvph30000.fpoly.duan111.R;


public class ChangePassWordFragment extends Fragment {

    public ChangePassWordFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ChangePassWordFragment newInstance() {
        ChangePassWordFragment fragment = new ChangePassWordFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_pass_word, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}