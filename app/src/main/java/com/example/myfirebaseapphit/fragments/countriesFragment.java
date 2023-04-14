package com.example.myfirebaseapphit.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfirebaseapphit.activities.MainActivity;
import com.example.myfirebaseapphit.activities.MainActivity2;
import com.example.myfirebaseapphit.R;
import com.example.myfirebaseapphit.adapters.CustomAdapter;
import com.example.myfirebaseapphit.dbstorage.DataService;
import com.example.myfirebaseapphit.models.State;
import com.example.myfirebaseapphit.recyclerViewInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class countriesFragment extends Fragment implements recyclerViewInterface {
    DataService ds = new DataService();
    ArrayList<State> arr;
    CustomAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MainActivity2 mainActivity2;
    FirebaseFirestore userRef;
    private FirebaseAuth mAuth;
    FirebaseUser user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_countries, container, false);
        mainActivity2 = (MainActivity2) getActivity();
        mAuth = FirebaseAuth.getInstance();//auth
        userRef= FirebaseFirestore.getInstance();

        arr = ds.getArrState();
        adapter = new CustomAdapter(arr,this);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);
        user=mAuth.getCurrentUser();

        return view;
    }

    public void onItemClick(int position)
    {
        Bundle bundle=new Bundle();

        //passing the cocktail object that the user chosen
        bundle.putSerializable("name",arr.get(position));
        DetailStateFragment newState=new DetailStateFragment();
        newState.setArguments(bundle);

        mainActivity2.replaceFragment(newState);
    }
}