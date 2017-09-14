package app.gtuinnovationcouncil.gicapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etmail;
    private EditText etpassword;
    private EditText rePass;
    private EditText fname;
    private EditText lname;
    private EditText enroll;
    private EditText college_txt;
    private EditText mobile;

    String gender;
    Button bsubmit;

    ProgressDialog loading;

    CheckBox terms;
    View v;

    RadioGroup rg;
    RadioButton radioButton;

    //  private static final String REGISTER_URL = "http://192.168.1.3/gic/register.php";
    public static final String ROOT_URL = "http://www.gtuinnovationcouncil.ac.in/gic/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        v = (View) findViewById(R.id.main);

        terms = (CheckBox) findViewById(R.id.terms);
        rg = (RadioGroup) findViewById(R.id.radiogroup);

        TextView title = (TextView) findViewById(R.id.title_text);
        Typeface tftitle = Typeface.createFromAsset(getAssets(), "fonts/PT_Sans-Web-Bold.ttf");
        title.setTypeface(tftitle);
        terms.setTypeface(tftitle);

        TextView login = (TextView) findViewById(R.id.link_login);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        Typeface tfgen = Typeface.createFromAsset(getAssets(), "fonts/PT_Sans-Web-Regular.ttf");
        etmail = (EditText) findViewById(R.id.input_email);
        etpassword = (EditText) findViewById(R.id.input_password);
        fname = (EditText) findViewById(R.id.input_firstname);
        lname = (EditText) findViewById(R.id.input_lastname);
        rePass = (EditText) findViewById(R.id.input_Repassword);
        enroll = (EditText) findViewById(R.id.enrollmentNo);
        college_txt = (EditText) findViewById(R.id.clgname);
        mobile = (EditText) findViewById(R.id.mobileNo);

        RadioButton rbMale = (RadioButton) findViewById(R.id.male_radio_btn);
        RadioButton rbfemale = (RadioButton) findViewById(R.id.female_radio_btn);


        TextView gender = (TextView) findViewById(R.id.gender_textview);
        gender.setTypeface(tfgen);

        TextView link = (TextView) findViewById(R.id.link_login);

        link.setTypeface(tftitle);

        etmail.setTypeface(tfgen);
        etpassword.setTypeface(tfgen);
        fname.setTypeface(tfgen);
        lname.setTypeface(tfgen);
        rePass.setTypeface(tfgen);
        enroll.setTypeface(tfgen);
        college_txt.setTypeface(tfgen);
        mobile.setTypeface(tfgen);
        rbMale.setTypeface(tfgen);
        rbfemale.setTypeface(tfgen);

        bsubmit = (Button) findViewById(R.id.btn_signup);
        bsubmit.setTypeface(tfgen);

        terms.setTypeface(tfgen);

        bsubmit.setOnClickListener(this);

    }

    private void insertUser() {

        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RegisterAPI api = adapter.create(RegisterAPI.class);

        //Defining the method insertuser of our interface
        api.insertUser(

                //Passing the values by getting it from editTexts
                fname.getText().toString(),
                lname.getText().toString(),
                etpassword.getText().toString(),
                etmail.getText().toString(),
                radioButton.getText().toString(),
                enroll.getText().toString(),
                mobile.getText().toString(),
                college_txt.getText().toString(),

                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        loading = new ProgressDialog(SignupActivity.this);
                        loading.setMessage("Please wait");
                        loading.show();
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast

                        Toast.makeText(SignupActivity.this, output, Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(SignupActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private boolean isValidEmaillId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


    @Override
    public void onClick(View v) {

        int selectedId = rg.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        if (fname.getText().toString().isEmpty() || lname.getText().toString().isEmpty()
                || etpassword.getText().toString().isEmpty() || etmail.getText().toString().isEmpty() ||
                rePass.getText().toString().isEmpty() || mobile.getText().toString().isEmpty())
        {
            Snackbar.make(v, "Please enter all details first", Snackbar.LENGTH_LONG).show();
        }
        else
        {
            if (isValidEmaillId(etmail.getText().toString().trim()))
            {
                if (etpassword.getText().toString().equals(rePass.getText().toString()))
                {
                    if(etpassword.getText().length() >= 8)
                    {
                        if(enroll.getText().length() == 12 || enroll.getText().length() == 0)
                        {
                            if(mobile.getText().length() == 10)
                            {
                                if (terms.isChecked()) {
                                    insertUser();
                                    Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    Toast.makeText(SignupActivity.this,"Please login first",Toast.LENGTH_LONG);
                                }
                                else
                                {
                                    Snackbar.make(v,"Please Accept terms & conditions first", Snackbar.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                Snackbar.make(v,"Enter valid mobile no", Snackbar.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Snackbar.make(v,"Enter valid enrollment no", Snackbar.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Snackbar.make(v,"Password length too short", Snackbar.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Snackbar.make(v,"Password does not match", Snackbar.LENGTH_LONG).show();
                }
            }
            else
            {
                Snackbar.make(v,"Invalid Email", Snackbar.LENGTH_LONG).show();
            }
        }

    }
}
