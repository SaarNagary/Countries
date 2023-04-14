package com.example.myfirebaseapphit.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myfirebaseapphit.R;
import com.example.myfirebaseapphit.activities.MainActivity2;
import com.example.myfirebaseapphit.adapters.CustomAdapter;
import com.example.myfirebaseapphit.dbstorage.DataService;
import com.example.myfirebaseapphit.models.State;

import java.util.ArrayList;


public class DetailStateFragment extends Fragment {
    DataService ds = new DataService();
    ArrayList<State> arr;
    CustomAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MainActivity2 mainActivity2;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_state, container, false);

        mainActivity2=(MainActivity2) getActivity();
        //adapter = new CustomAdapter(arr,this);


        TextView detailName = view.findViewById(R.id.textViewDetailNameResult);
        TextView detailNativeName = view.findViewById(R.id.textViewDetailNativeNameResult);
        ImageView detailFlag = view.findViewById(R.id.imageViewDetailFlagResult);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewDetailBorders);
        layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        State state= (State) getArguments().getSerializable("name");

        String s=state.getName().toString();

        detailName.setText(state.getName().replace("\"", "").trim());
        detailNativeName.setText(state.getName().replace("\"", "").trim());
        Glide.with(this).load(state.getFlag()).into(detailFlag);
        ArrayList<String> bordersDetail = (ArrayList<String>) state.getBorders();


        return view;
    }
}