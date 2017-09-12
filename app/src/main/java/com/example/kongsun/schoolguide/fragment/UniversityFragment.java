package com.example.kongsun.schoolguide.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kongsun.schoolguide.R;

/**
 * Created by kongsun on 9/8/17.
 */

public class UniversityFragment extends Fragment {
private ViewPager mViewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_university, container, false);
        TabLayout mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addTab(mTabLayout.newTab().setText("Public"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Private"));
        //getChildFragmentManager();
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(new SelectionPagerAdapter(getChildFragmentManager(),mTabLayout.getTabCount()));

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    public class SelectionPagerAdapter extends FragmentPagerAdapter {
        int mNumOfTabs;

        public SelectionPagerAdapter(FragmentManager fm, int mNumOfTabs) {
            super(fm);
            this.mNumOfTabs = mNumOfTabs;
        }


        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    PublicUniversityFragment publicUniversityFragment = new PublicUniversityFragment();
                    return publicUniversityFragment;
                case 1:
                    PrivateUniversityFragment privateUniversityFragment = new PrivateUniversityFragment();
                    return privateUniversityFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}
