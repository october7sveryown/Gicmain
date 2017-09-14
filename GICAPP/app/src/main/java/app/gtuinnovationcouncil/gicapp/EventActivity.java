package app.gtuinnovationcouncil.gicapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EventActivity extends AppCompatActivity {

    private  CustomerAdapter Adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static List<MyData> data_list;
    private Toolbar toolbar;

    public ProgressDialog loadingevent;
    static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data_list=new ArrayList<>();
        load_event_date();
        Adapter=new CustomerAdapter(data_list,this);
        recyclerView.setAdapter(Adapter);
    }

    private void load_event_date() {
       /* loadingevent=new ProgressDialog(this);
        loadingevent.setMessage("Getting events..");
        loadingevent.show();
        */AsyncTask<Void,Void,Void> task=new AsyncTask<Void,Void,Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Adapter.notifyDataSetChanged();
            }

            @Override
            protected Void doInBackground(Void... voids) {

                OkHttpClient client=new  OkHttpClient();
                Request request=new Request.Builder().url("http://www.gtuinnovationcouncil.ac.in/gic/getData.php").build();
                try {
                    Response response=client.newCall(request).execute();
                    JSONArray jsonArray=new JSONArray(response.body().string());
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String Datestr=jsonObject.getString("event_date");
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                        Date event_date=sdf.parse(Datestr);
                        SimpleDateFormat AppDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        AppDateFormat.format(event_date);

                        String s = jsonObject.getString("event_starttime");
                        String s1 = jsonObject.getString("event_endtime");

                        MyData mydata=new MyData(jsonObject.getInt("event_id"),jsonObject.getString("event_name"),jsonObject.getString("event_venue"),event_date,jsonObject.getString("event_register_link"),jsonObject.getString("event_description"),s,s1,jsonObject.getString("event_img"));
                        data_list.add(mydata);
                    }
                }  catch (ParseException e) {
                    e.printStackTrace();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task.execute();
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
