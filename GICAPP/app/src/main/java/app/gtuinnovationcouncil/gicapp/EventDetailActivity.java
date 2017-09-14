package app.gtuinnovationcouncil.gicapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EventDetailActivity extends AppCompatActivity {

    ImageView imgView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        imgView = (ImageView)findViewById(R.id.eventDetImage);

        Intent intent = getIntent();
        final String link = intent.getStringExtra("register_link");
        final String eventname = intent.getStringExtra("event_name");
        final String eventdesc = intent.getStringExtra("event_description");
        final String eventvenue = intent.getStringExtra("event_venue");
        final String starttime = intent.getStringExtra("event_starttime");
        final String endtime = intent.getStringExtra("event_endtime");
        final String img = intent.getStringExtra("event_img");
        final String date = intent.getStringExtra("event_date");

        final Uri myUri = Uri.parse(link);

        if(img.isEmpty())
        {
            Picasso.with(this)
                    .load("http://apuzz.com/wp-content/uploads/2014/03/GTU-logo.png").into(imgView);
        }
        else {
            Picasso.with(this)
                    .load(img).into(imgView);
        }

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW,myUri);
                startActivity(i);
            }
        });

        Typeface tf= Typeface.createFromAsset(getAssets(),"fonts/Overpass-Regular.ttf");
        Typeface title= Typeface.createFromAsset(getAssets(),"fonts/BreeSerif-Regular.ttf");

        TextView name = (TextView)findViewById(R.id.tvTitle);
        name.setTypeface(title);
        name.setText(eventname);

        TextView txtVenue = (TextView)findViewById(R.id.txtVenue);
        txtVenue.setTypeface(title);

        TextView venue = (TextView)findViewById(R.id.Venue);
        venue.setTypeface(title);
        venue.setText(eventvenue);

        TextView txtDate = (TextView)findViewById(R.id.txtDate);
        txtDate.setTypeface(title);


        TextView Date = (TextView)findViewById(R.id.Date);
        Date.setTypeface(title);
        Date.setText(date);

        TextView txtTime = (TextView)findViewById(R.id.txtTime);
        txtTime.setTypeface(title);

        TextView time = (TextView)findViewById(R.id.Time);
        time.setTypeface(title);
        time.setText(starttime+" To "+endtime);

        TextView txt = (TextView)findViewById(R.id.txtDetails);
        txt.setTypeface(tf);
        txt.setText(eventdesc);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);


    }
}
