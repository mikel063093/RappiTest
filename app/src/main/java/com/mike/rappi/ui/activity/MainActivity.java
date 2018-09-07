package com.mike.rappi.ui.activity;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mike.rappi.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import com.mike.rappi.ui.adapter.ViewPagerAdapter;
import com.mike.rappi.ui.fragment.PopularFragment;
import com.mike.rappi.ui.fragment.TopRatedFragment;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainToolbar) Toolbar toolbar;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.viewPager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PopularFragment(), "Popular");
        adapter.addFragment(new TopRatedFragment(), "Top Rated");
        viewPager.setAdapter(adapter);
    }
}
