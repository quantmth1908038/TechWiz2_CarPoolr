package com.techwiz2.carpoolr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.techwiz2.carpoolr.MainActivity;
import com.techwiz2.carpoolr.R;
import com.techwiz2.carpoolr.OnClickListener.StartonClickListener;
import com.techwiz2.carpoolr.activity.fragments.FragmentBlank1;
import com.techwiz2.carpoolr.activity.fragments.FragmentBlank2;
import com.techwiz2.carpoolr.activity.fragments.FragmentBlank3;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private FragmentBlank3 fragmentBlank3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        List<Fragment> list = new ArrayList<>();

        fragmentBlank3 = new FragmentBlank3();

        list.add(new FragmentBlank1());
        list.add(new FragmentBlank2());
        list.add(fragmentBlank3);

        fragmentBlank3.setStartonClickListener(new StartonClickListener() {
            @Override
            public void onClickListenerFragment(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        pager = findViewById(R.id.pager);

        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(),list);

        pager.setAdapter(pagerAdapter);
    }
}