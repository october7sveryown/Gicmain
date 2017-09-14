package app.gtuinnovationcouncil.gicapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    String myValue ;
    ProgressDialog loading ;

    Context context ;

    TextView txtname ;
    TextView txtemail ;
    TextView txtenroll ;
    TextView txtmobile ;
    TextView txtcollege ;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        context = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_profile,container, false);

        Bundle bundle = this.getArguments();
        myValue = bundle.getString("message");

        txtname = (TextView)rootView.findViewById(R.id.name);
        txtemail = (TextView)rootView.findViewById(R.id.email);
        txtenroll = (TextView)rootView.findViewById(R.id.enroll);
        txtmobile = (TextView)rootView.findViewById(R.id.mobile);
        txtcollege = (TextView)rootView.findViewById(R.id.college);

        getData();

        return rootView ;

    }

    private void getData() {

        loading = ProgressDialog.show(getContext(),"Please wait...","Fetching...",false,false);

        String url = ConfigProfile.DATA_URL+myValue ;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String fname=" ";
        String lname=" ";
        String email = " ";
        String enroll =" ";
        String mobile =" ";
        String college  = " ";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(ConfigProfile.JSON_ARRAY);
            JSONObject userData = result.getJSONObject(0);
            fname = userData.getString(ConfigProfile.KEY_FNAME);
            lname = userData.getString(ConfigProfile.KEY_LNAME);
            email = userData.getString(ConfigProfile.KEY_EMAIL);
            enroll = userData.getString(ConfigProfile.KEY_ENROLL);
            mobile = userData.getString(ConfigProfile.KEY_MOBILE);
            college = userData.getString(ConfigProfile.KEY_CLG);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        txtname.setText(fname+" "+lname);
        txtemail.setText(email);
        txtcollege.setText(college);
        txtmobile.setText(mobile);
        txtenroll.setText(enroll);

    }

}
