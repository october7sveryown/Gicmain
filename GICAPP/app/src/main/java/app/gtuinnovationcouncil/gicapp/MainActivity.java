package app.gtuinnovationcouncil.gicapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1500 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        TextView myText=(TextView)findViewById(R.id.myText);

        Typeface tf= Typeface.createFromAsset(getAssets(),"fonts/PT_Sans-Web-Bold.ttf");
        myText.setTypeface(tf);

        if(isNetworkAvailable())
        {
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run(){
                    Intent i=new Intent(MainActivity.this,SlideScreenActivity.class);
                    startActivity(i);
                    finish();
                }
            },SPLASH_TIME_OUT);
        }
        else {
            Toast.makeText(MainActivity.this,"Please turn on the internet first",Toast.LENGTH_LONG).show();
            finish();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}

