package com.example.kongsun.schoolguide.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.kongsun.schoolguide.OnRecyclerViewItemClickListener;
import com.example.kongsun.schoolguide.R;
import com.example.kongsun.schoolguide.accessor.University;
import com.example.kongsun.schoolguide.activity.DescriptionActivity;
import com.example.kongsun.schoolguide.adapter.UniversityAdapter;
import com.example.kongsun.schoolguide.singleton.MySingleTon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublicUniversityFragment extends Fragment implements OnRecyclerViewItemClickListener,SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView mRecyclerView;
    private UniversityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<University> universities = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pub_university, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rc_university);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new UniversityAdapter(universities,getContext());
        mAdapter.setOnRecyclerViewItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        initializeData();

        return view;
    }

    private void showLoading(boolean state) {
        mSwipeRefreshLayout.setRefreshing(state);
    }

    private void initializeData() {
        String url = "https://jsun.000webhostapp.com/public_university.php";
        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                showLoading(false); //បើមានទិន្នន័យ មិនចាំបាច់ Load Data
                try{
                    List<University> universities = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("ID");
                        String kh_name = jsonObject.getString("Kh_Name");
                        String en_name = jsonObject.getString("En_Name");
                        String logoUrl = jsonObject.getString("LogoUrl");
                        String photoUrl = jsonObject.getString("PhotoUrl");
                        String desc = jsonObject.getString("Description");
                        University university = new University(id, kh_name, en_name, logoUrl, photoUrl,desc);
                        universities.add(university);
                    }
                    mAdapter.setUniversities(universities);
                    }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Loading data from server error",Toast.LENGTH_SHORT).show();
                showLoading(false);
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(url,listener,errorListener);
        MySingleTon.getInstance(getActivity()).addRequest(request);

    }

    @Override
    public void onRefresh() {
        initializeData();
    }

    @Override
    public void OnRecyclerViewItemClickListener(int position) {
        University university = mAdapter.getPublicUniversity(position);
        Intent intent = new Intent(getActivity(),DescriptionActivity.class);
        intent.putExtra("Kh_Name", university.getKh_Name());
        intent.putExtra("En_Name", university.getEn_Name());
        intent.putExtra("LogoUrl", university.getLogoUrl());
        intent.putExtra("PhotoUrl", university.getPhotoUrl());
        intent.putExtra("Description", university.getDesc());
        startActivity(intent);
    }
}
