package app.gtuinnovationcouncil.gicapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    View v ;
    private boolean loggedIn = false;
    private EditText etmail;
    private EditText etpassword;
    private Button bsubmit;
    private RequestQueue requestQueue;
    private RequestQueue requestQueue1;

    private boolean doubleBackToExitPressedOnce = false;

    SessionManager session;

    public static final String ROOT_URL = "http://www.gtuinnovationcouncil.ac.in/gic/forgot.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());

        v = (View) findViewById(R.id.main);

        TextView titleimage11=(TextView)findViewById(R.id.titleimage);
        Typeface tftitle=Typeface.createFromAsset(getAssets(),"fonts/PT_Sans-Web-Bold.ttf");

        TextView signup = (TextView)findViewById(R.id.link_signup);
        TextView forPas = (TextView)findViewById(R.id.link_forgotPass);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        titleimage11.setTypeface(tftitle);
        Typeface tfgen = Typeface.createFromAsset(getAssets(), "fonts/PT_Sans-Web-Regular.ttf");
        etmail = (EditText) findViewById(R.id.email);
        etpassword = (EditText) findViewById(R.id.password);
        etmail.setTypeface(tfgen);
        etpassword.setTypeface(tfgen);
        bsubmit = (Button) findViewById(R.id.simpleLogin);
        Button bgmail = (Button) findViewById(R.id.simpleLogin);
        bsubmit.setTypeface(tfgen);
        bgmail.setTypeface(tfgen);
        requestQueue=Volley.newRequestQueue(getApplicationContext());
        requestQueue1=Volley.newRequestQueue(getApplicationContext());

        bsubmit.setOnClickListener(this);

        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        TextView link1 = (TextView)findViewById(R.id.link_signup);
        TextView link2 = (TextView)findViewById(R.id.link_forgotPass);
        TextView link3 = (TextView)findViewById(R.id.link_guest);

        link1.setTypeface(tftitle);
        link2.setTypeface(tftitle);
        link3.setTypeface(tftitle);

        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etmail.getText().toString().isEmpty())
                {
                    Snackbar.make(v , "Enter your email first", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                  insertData();
                }
            }
        });

        link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                session.createLoginSession("xyz@gmail.com");
                Intent intent = new Intent(LoginActivity.this,HomeScreenActivity.class);
                startActivity(intent);
            }
        });
    }
private void insertData() {

    StringRequest stringRequest=new StringRequest(Request.Method.POST,ROOT_URL, new com.android.volley.Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Snackbar.make(v,response,Snackbar.LENGTH_LONG).show();
        }
    }, new com.android.volley.Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Snackbar.make(v,"New Temporary Password has been sent to your email",Snackbar.LENGTH_LONG).show();
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params=new HashMap<String,String>();
            params.put("email",etmail.getText().toString());
            return params;
        }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);
    }

    public RequestQueue getRequestQueue() {
        return requestQueue1;}


    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        this.doubleBackToExitPressedOnce = false;

        //If we will get true
        if (loggedIn) {
            //We will start the Profile Activity
            Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
            startActivity(intent);
        }
    }

    private void login() {
        //Getting values from edit texts
        final String email = etmail.getText().toString().trim();
        final String password = etpassword.getText().toString().trim();

        if (email.isEmpty() && password.isEmpty()) {
            Snackbar.make(v, "Please enter the email first", Snackbar.LENGTH_LONG).show();
        } else {
            final ProgressDialog loading = new ProgressDialog(this);
            loading.setMessage("Authenticating");
            loading.show();
            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                    new Response.Listener<String>() {
                        public void onResponse(String response) {
                            //If we are getting success from server
                            if (response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {

                                session.createLoginSession(etmail.getText().toString());
                                //Creating a shared preference
                                loading.hide();
                                //Starting profile activity
                                Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(LoginActivity.this,HomeScreenActivity.class);
                                intent.putExtra("email", etmail.getText().toString().trim());
                                startActivity(intent);
                            } else {
                                //If the server response is not success
                                //Displaying an error message on toast
                                loading.hide();
                                Snackbar.make(v ,"Invalid email or password", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put(Config.KEY_EMAIL, email);
                    params.put(Config.KEY_PASSWORD, password);

                    //returning parameter
                    return params;
                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

    @Override
    public void onClick(View v) {
        //Calling the login function
        login();
    }

    @Override
    public void onBackPressed() {
            Toast.makeText(this,"Can't go back to previous activity now", Toast.LENGTH_SHORT).show();}
}
