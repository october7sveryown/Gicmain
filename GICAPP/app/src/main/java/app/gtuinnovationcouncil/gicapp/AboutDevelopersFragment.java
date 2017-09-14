package app.gtuinnovationcouncil.gicapp;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutDevelopersFragment extends Fragment {

    Context context;

    public AboutDevelopersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        context = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_about_developers,container, false);



        Typeface tfcard = Typeface.createFromAsset(getActivity().getAssets(), "fonts/PT_Sans-Web-Bold.ttf");

        TextView krunal = (TextView)rootView.findViewById(R.id.krunal);
        krunal.setTypeface(tfcard);
        krunal.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://www.facebook.com/krunal.hingu.102'>Krunal Hingu</a>";
        krunal.setText(Html.fromHtml(text));

        TextView yash = (TextView)rootView.findViewById(R.id.yash);
        yash.setTypeface(tfcard);
        yash.setMovementMethod(LinkMovementMethod.getInstance());
        String text1 = "<a href='https://www.facebook.com/yash.thaker.7'>Yash Thaker</a>";
        yash.setText(Html.fromHtml(text1));


        TextView abhi = (TextView)rootView.findViewById(R.id.abhi);
        abhi.setTypeface(tfcard);
        abhi.setMovementMethod(LinkMovementMethod.getInstance());
        String text2 = "<a href='https://www.facebook.com/Bitzacker'>Abhijit Raval</a>";
        abhi.setText(Html.fromHtml(text2));


        Typeface title = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BreeSerif-Regular.ttf");
        TextView Developers = (TextView)rootView.findViewById(R.id.txtDeveloper);
        Developers.setTypeface(title);


        return rootView;
    }
}


