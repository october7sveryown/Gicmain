package app.gtuinnovationcouncil.gicapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePassword extends Fragment {
    public static final String ROOT_URL = "http://www.gtuinnovationcouncil.ac.in/gic/changepass.php";
    private EditText etnewpass;
    private EditText etoldpass;
    private EditText etemail;
    private Context context;
    private RequestQueue requestQueue;
    public ChangePassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        final View rootView = inflater.inflate(R.layout.fragment_change_password,container, false);
        etemail=(EditText)rootView.findViewById(R.id.email);
        etoldpass=(EditText)rootView.findViewById(R.id.oldPass);
        etnewpass=(EditText)rootView.findViewById(R.id.newPass);
        Button btnchangepass=(Button)rootView.findViewById(R.id.simplechange);
        requestQueue= Volley.newRequestQueue(context);
        btnchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etemail.getText().toString().isEmpty() || etoldpass.getText().toString().isEmpty()
                        || etoldpass.getText().toString().isEmpty() )
                {
                    Snackbar.make(rootView,"Please fill the all data first", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    if (isValidEmaillId(etemail.getText().toString().trim()))
                    {
                        insertData();
                      //  etemail.setText("");
                       // etoldpass.setText("");
                       // etnewpass.setText("");

                    }
                    else
                    {
                        Snackbar.make(rootView,"Invalid Email", Snackbar.LENGTH_LONG).show();
                    }

                }
            }
        });
        return rootView;
    }
    private boolean isValidEmaillId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void insertData(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, ROOT_URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(),HomeScreenActivity.class);
                startActivity(i);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Try Again after sometime",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();

                params.put("email",etemail.getText().toString());
                //params.put("old_pass",etoldpass.getText().toString());
                params.put("newpassword",etnewpass.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
