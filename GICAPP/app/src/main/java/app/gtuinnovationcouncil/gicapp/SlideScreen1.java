package app.gtuinnovationcouncil.gicapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class SlideScreen1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_slide_screen1, container,
                false);
        TextView textView1=(TextView)rootView.findViewById(R.id.textsupport);
        Typeface tfview=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Lobster-Regular.ttf");
        textView1.setTypeface(tfview);
        TextView textViewdetails=(TextView)rootView.findViewById(R.id.studentsupportdetails);
        Typeface tfviewDetails=Typeface.createFromAsset(getActivity().getAssets(),"fonts/PT_Sans-Web-Regular.ttf");
        textViewdetails.setTypeface(tfviewDetails);
        return rootView;
    }
}