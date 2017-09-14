package app.gtuinnovationcouncil.gicapp;

/**
 * Created by october7sveryown on 14/12/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = {
        R.drawable.calendar_final,R.drawable.icon_final,R.drawable.conference_final,R.drawable.male,
            R.drawable.info_final,R.drawable.customer_reviews
    };
    String[] titles={
            "Events","GIC Sections","Clubs","Online Courses","About us","Customer Reviews"
    };
    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.row, parent, false);
        TextView label=(TextView)row.findViewById(R.id.image_name);
        label.setText(titles[position]);
        ImageView imageView;
        imageView= new ImageView(mContext);
        imageView =(ImageView)row.findViewById(R.id.album_image);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(80, 80));
        return row;
    }

}