package app.gtuinnovationcouncil.gicapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SlideScreen2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_slide_screen2, container,
                false);
        TextView textView2=(TextView)rootView.findViewById(R.id.videolecture);
        Typeface tfview1=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Lobster-Regular.ttf");
        textView2.setTypeface(tfview1);
        TextView videodetails=(TextView)rootView.findViewById(R.id.videolecturedetails);
        Typeface tfviewvideoDetails=Typeface.createFromAsset(getActivity().getAssets(),"fonts/PT_Sans-Web-Regular.ttf");
        videodetails.setTypeface(tfviewvideoDetails);
        return rootView;
    }
}