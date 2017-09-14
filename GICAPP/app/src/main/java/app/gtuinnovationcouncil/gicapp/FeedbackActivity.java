package app.gtuinnovationcouncil.gicapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class FeedbackActivity extends AppCompatActivity{

    ProgressDialog loading;

    private EditText username;
    private EditText email;
    private EditText eventname;
    private EditText comment;
    private RequestQueue requestQueue;
    Button bt;

    View v ;

    public static final String ROOT_URL = "http://192.168.1.106/gic/insertFeedback.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        v = (View) findViewById(R.id.activity_feedback);


        TextView title =(TextView)findViewById(R.id.title_text);
        Typeface tftitle=Typeface.createFromAsset(getAssets(),"fonts/PT_Sans-Web-Bold.ttf");
        title.setTypeface(tftitle);

        username = (EditText)findViewById(R.id.txtName);
        email = (EditText)findViewById(R.id.txtEmail);
        eventname = (EditText)findViewById(R.id.txtEvent);
        comment = (EditText)findViewById(R.id.txtComment);
        requestQueue= Volley.newRequestQueue(getApplicationContext());



        bt = (Button)findViewById(R.id.btFeedback);
        bt.setTypeface(tftitle);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().isEmpty() || email.getText().toString().isEmpty()
                        || eventname.getText().toString().isEmpty() || comment.getText().toString().isEmpty())
                {
                    Snackbar.make(v,"Please fill the all data first", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    if (isValidEmaillId(email.getText().toString().trim()))
                    {
                            insertData();
                        Intent i = new Intent(FeedbackActivity.this,HomeScreenActivity.class);
                        startActivity(i);

                    }
                    else
                    {
                        Snackbar.make(v,"Invalid Email", Snackbar.LENGTH_LONG).show();
                    }

                }
            }
        });
    }


    private boolean isValidEmaillId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
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

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void insertData(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, ROOT_URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Try Again after sometime",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("user_name",username.getText().toString());
                params.put("email_id",email.getText().toString());
                params.put("event_name",eventname.getText().toString());
                params.put("user_comment",comment.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
