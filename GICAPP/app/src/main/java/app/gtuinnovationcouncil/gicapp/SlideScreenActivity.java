package app.gtuinnovationcouncil.gicapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.pixelcan.inkpageindicator.InkPageIndicator;


public class SlideScreenActivity extends AppCompatActivity {

    ViewPager mViewPager;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_slide_screen);
        changeStatusBarColor();
        mViewPager = (ViewPager) findViewById(R.id.pager);
/** set the adapter for ViewPager */
        mViewPager.setAdapter(new SamplePagerAdapter(
                getSupportFragmentManager()));
        InkPageIndicator inkPageIndicator = (InkPageIndicator)findViewById(R.id.indicator);
        inkPageIndicator.setViewPager(mViewPager);
        TextView features = (TextView) findViewById(R.id.Features);
        Typeface tffeatures = Typeface.createFromAsset(getAssets(), "fonts/PT_Sans-Web-Bold.ttf");
        features.setTypeface(tffeatures);
        Button textskip = (Button) findViewById(R.id.skip);
        Button logIn = (Button) findViewById(R.id.login);
        Button signUp = (Button) findViewById(R.id.signup);
       // SpannableString content = new SpannableString("Skip");
        //content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
     //   textskip.setText(content);
        textskip.setTypeface(tffeatures);
        textskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lgn = new Intent(SlideScreenActivity.this,LoginActivity.class);
                startActivity(lgn);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign = new Intent(SlideScreenActivity.this,SignupActivity.class);
                startActivity(sign);
            }
        });


    }

    /**
     * Defining a FragmentPagerAdapter class for controlling the fragments to be shown when user swipes on the screen.
     */
    public class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            /** Show a Fragment based on the position of the current screen */
            if (position == 0) {
                return new SlideScreen();
            } else if(position==1)
                return new SlideScreen1();
            else
                return new SlideScreen2();
        }

        @Override
        public int getCount() {

            return 3;
        }
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(SlideScreenActivity.this,HomeScreenActivity.class));
        finish();
    }

}