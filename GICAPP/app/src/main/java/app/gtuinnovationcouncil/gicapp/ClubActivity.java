package app.gtuinnovationcouncil.gicapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;

import java.util.HashMap;

public class ClubActivity extends AppCompatActivity  {

        SliderLayout sliderLayout;
        HashMap<String, String> Hash_file_maps;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

                super.onCreate(savedInstanceState);

                setContentView(R.layout.activity_club);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                Typeface tf= Typeface.createFromAsset(getAssets(),"fonts/Overpass-Regular.ttf");
                Typeface title= Typeface.createFromAsset(getAssets(),"fonts/BreeSerif-Regular.ttf");

                TextView Title = (TextView)findViewById(R.id.Title);
                Title.setTypeface(title);

                TextView introduction = (TextView)findViewById(R.id.introduction);
                introduction.setTypeface(title);

                TextView process = (TextView)findViewById(R.id.process);
                process.setTypeface(title);

                TextView process2 = (TextView)findViewById(R.id.process2);
                process2.setTypeface(title);

                TextView intro_details = (TextView)findViewById(R.id.intro_details);
                intro_details.setTypeface(tf);

                TextView process_details = (TextView)findViewById(R.id.process_details);
                process_details.setTypeface(tf);

                TextView process2_details = (TextView)findViewById(R.id.process2_details);
                process2_details.setTypeface(tf);

                FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);

                fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gtu.ac.in/uploads/15092016.pdf"));
                                startActivity(i);
                        }
                });


        }

        public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                        case android.R.id.home:
                                // app icon in action bar clicked; go home
                                Intent intent = new Intent(this, HomeScreenActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                return true;
                        default:
                                return super.onOptionsItemSelected(item);
                }
        }
}