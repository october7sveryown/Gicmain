package app.gtuinnovationcouncil.gicapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.gtuinnovationcouncil.gicapp.R;


public class MOOCFragment extends Fragment {


    Context context ;

    public MOOCFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_mooc,container, false);

        Typeface tf= Typeface.createFromAsset(getActivity().getAssets(),"fonts/Overpass-Regular.ttf");
        Typeface title= Typeface.createFromAsset(getActivity().getAssets(),"fonts/BreeSerif-Regular.ttf");


        TextView name = (TextView)rootView.findViewById(R.id.tvTitle);
        name.setTypeface(title);

        TextView txt = (TextView)rootView.findViewById(R.id.txtDetails);
        txt.setTypeface(tf);

        TextView meetup = (TextView)rootView.findViewById(R.id.txtMeetus);
        meetup.setTypeface(title);

        TextView meetuptext = (TextView)rootView.findViewById(R.id.txtMeetupDetails);
        meetuptext.setTypeface(tf);

        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gtu.ac.in/startupmooc.aspx"));
                startActivity(i);
            }
        });
        
        return rootView ;
    }

}
