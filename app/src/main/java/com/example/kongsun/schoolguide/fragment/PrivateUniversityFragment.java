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
import com.example.kongsun.schoolguide.accessor.PrivateUniversity;
import com.example.kongsun.schoolguide.activity.RRIDescriptionActivity;
import com.example.kongsun.schoolguide.adapter.PrivateUniversityAdapter;
import com.example.kongsun.schoolguide.singleton.MySingleTon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kongsun on 9/8/17.
 */

public class PrivateUniversityFragment extends Fragment implements OnRecyclerViewItemClickListener,SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView mRecyclerView;
    private PrivateUniversityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<PrivateUniversity> privateUniversities = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pri_university, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rc_pri_university);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PrivateUniversityAdapter(privateUniversities,getContext());
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
        String url = "https://jsun.000webhostapp.com/private_university.php";
        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                showLoading(false); //បើមានទិន្នន័យ មិនចាំបាច់ Load Data
                try{
                    List<PrivateUniversity> privateUniversities = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("ID");
                        String kh_name = jsonObject.getString("Kh_Name");
                        String en_name = jsonObject.getString("En_Name");
                        String logoUrl = jsonObject.getString("LogoUrl");
                        String photoUrl = jsonObject.getString("PhotoUrl");
                        String desc = jsonObject.getString("Description");
                        PrivateUniversity privateUniversity = new PrivateUniversity(id, kh_name, en_name, logoUrl, photoUrl,desc);
                        privateUniversities.add(privateUniversity);
                    }
                    mAdapter.setPrivateUniversity(privateUniversities);
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
        PrivateUniversity privateUniversity = mAdapter.getPrivateUniversity(position);
        Intent intent = new Intent(getActivity(), RRIDescriptionActivity.class);
        intent.putExtra("Kh_Name",privateUniversity.getKh_Name());
        intent.putExtra("En_Name",privateUniversity.getEn_Name());
        intent.putExtra("LogoUrl",privateUniversity.getLogoUrl());
        intent.putExtra("PhotoUrl",privateUniversity.getPhotoUrl());
        intent.putExtra("Description",privateUniversity.getDesc());
        startActivity(intent);
    }
}
