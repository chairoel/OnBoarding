package com.amrul.learn.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout dotLayout;
    private Button btnBack, btnNext, btnSkip;

    private final int dotPagesFirst = 0;
    private final int dotPages = 4;
    private TextView[] dots;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);

        btnBack.setOnClickListener(view -> {
            if (getItem() > dotPagesFirst) {
                slideViewPager.setCurrentItem((getItem() - 1), true);
            }
        });

        btnNext.setOnClickListener(view -> {
            if (getItem() < (dotPages - 1))
                slideViewPager.setCurrentItem((getItem() + 1),true);
            else {
//                Intent i = new Intent(MainActivity.this,mainscreen.class);
//                startActivity(i);
//                finish();
                Toast.makeText(this, "Last Pager", Toast.LENGTH_SHORT).show();
            }
        });

        btnSkip.setOnClickListener(view -> {
            Toast.makeText(this, "go to Main(Last Pager)", Toast.LENGTH_SHORT).show();
        });

        dotLayout = findViewById(R.id.dotIndicator);
        setupIndicator(dotPagesFirst);

        slideViewPager = findViewById(R.id.slideViewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        slideViewPager.setAdapter(viewPagerAdapter);
        slideViewPager.addOnPageChangeListener(viewListener);

    }

    private void setupIndicator(int position) {
        dots = new TextView[dotPages];
        dotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
            dotLayout.addView(dots[i]);
        }

        dots[position].setTextColor(getResources().getColor(R.color.active, getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setupIndicator(position);
            if (position > 0) {
                btnBack.setVisibility(View.VISIBLE);
            } else {
                btnBack.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getItem() {
        return slideViewPager.getCurrentItem();
    }
}