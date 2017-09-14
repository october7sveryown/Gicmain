package app.gtuinnovationcouncil.gicapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Typeface tf= Typeface.createFromAsset(getAssets(),"fonts/Overpass-Regular.ttf");
        Typeface title= Typeface.createFromAsset(getAssets(),"fonts/BreeSerif-Regular.ttf");


        TextView name = (TextView)findViewById(R.id.tvTitle);
        name.setTypeface(title);

        TextView txt = (TextView)findViewById(R.id.txtDetails);
        txt.setTypeface(tf);

        TextView meetup = (TextView)findViewById(R.id.txtMeetus);
        meetup.setTypeface(title);

        TextView meetuptext = (TextView)findViewById(R.id.txtMeetupDetails);
        meetuptext.setTypeface(tf);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab) ;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://gtuinnovationcouncil.ac.in"));
                startActivity(i);
            }
        });


    }

}
