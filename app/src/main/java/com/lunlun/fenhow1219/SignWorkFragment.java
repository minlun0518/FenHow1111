package com.lunlun.fenhow1219;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class SignWorkFragment extends Fragment {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private View mView;
    public ViewPager mViewPager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.sign_work_content, container, false);
        getActivity().setTitle("打卡出勤系統");
        setToolbar();
        return this.mView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setToolbar() {
        createViewPager();
        ((TabLayout) this.mView.findViewById(R.id.signinTabs)).setupWithViewPager(this.mViewPager);
    }

    private void createViewPager() {
        this.mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        this.mViewPager = (ViewPager) this.mView.findViewById(R.id.viewPagerSigninWork);
        this.mViewPager.setAdapter(this.mSectionsPagerAdapter);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                try {
                    AppBarLayout appbar = (AppBarLayout) SignWorkFragment.this.getActivity().findViewById(R.id.appbar);
                    ((AppBarLayout.Behavior) ((CoordinatorLayout.LayoutParams) appbar.getLayoutParams()).getBehavior()).onNestedFling((CoordinatorLayout) SignWorkFragment.this.getActivity().findViewById(R.id.coordinatorLayoutMain), appbar, (View) null, 0.0f, -1000.0f, true);
                } catch (Exception e) {
                }
            }

            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SignInFragment();
                case 1:
                    return new DailysFragment();
                default:
                    return null;
            }
        }

        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "簽到退";
                case 1:
                    return "出勤紀錄";
                default:
                    return null;
            }
        }
    }

}