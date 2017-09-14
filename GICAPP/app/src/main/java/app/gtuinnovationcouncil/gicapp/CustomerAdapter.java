package app.gtuinnovationcouncil.gicapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Abhi on 24-01-2017.
 */


public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>{

    private Context context;
    private List<MyData> my_data;
    private Intent intent;
    private CardView card;
    String str ;

    public CustomerAdapter(List<MyData> my_data, Context context) {
        this.my_data = my_data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewFest.setText(my_data.get(position).getEvent_name());
        holder.textViewPlace.setText(my_data.get(position).getEvent_venue());
        Date date=my_data.get(position).getEvent_date();
        DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        str=dateFormat.format(date);
        holder.textViewDate.setText(str);
        final String event_url= my_data.get(position).getEvent_register_link();
        final String event_name = my_data.get(position).getEvent_name();
        final String event_venue = my_data.get(position).getEvent_venue();
        final String event_description = my_data.get(position).getEvent_description();
        String  s1 = my_data.get(position).getEvent_starttime();
        String s2 = my_data.get(position).getEvent_endtime();
        final String event_image = my_data.get(position).getEvent_img();

        s1 = s1.substring(0, s1.length() - 3);
        s2 = s2.substring(0, s1.length() - 0);

        final String startTime = s1 ;
        final String endTime = s2 ;

        holder.register_link.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                intent=new Intent(context,EventDetailActivity.class);
                intent.putExtra("register_link",event_url);
                intent.putExtra("event_name",event_name);
                intent.putExtra("event_venue",event_venue);
                intent.putExtra("event_description",event_description);
                intent.putExtra("event_starttime",startTime);
                intent.putExtra("event_endtime",endTime);
                intent.putExtra("event_img",event_image);
                intent.putExtra("event_date",str);
                context.startActivity(intent);
            }
        });

        holder.sharebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                String sharestring="Share by GIC's official App \n\nJoin me in this event \nEvent Name : "+ event_name+"\nEvent Venue : "+event_venue+"\nFor more info click here : "+event_url;
                sendIntent.putExtra(Intent.EXTRA_TEXT, sharestring);
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewFest;
        public TextView textViewPlace;
        public TextView textViewDate;
        public Button register_link;
        public ImageButton sharebutton1;

        public ViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();

            this.textViewFest = (TextView) itemView.findViewById(R.id.event_name);
            this.textViewPlace = (TextView) itemView.findViewById(R.id.event_place);
            this.textViewDate = (TextView) itemView.findViewById(R.id.event_date);
            this.register_link= (Button)itemView.findViewById(R.id.reg_link);
            this.sharebutton1= (ImageButton)itemView.findViewById(R.id.sharebutton);

        }
    }
}
