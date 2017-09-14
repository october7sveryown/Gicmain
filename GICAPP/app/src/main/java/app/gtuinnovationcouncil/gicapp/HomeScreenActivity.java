package app.gtuinnovationcouncil.gicapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

import java.util.HashMap;


public class HomeScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    NavigationView navigationView ;

    CoordinatorLayout coordinatorLayout;
    Context context ;
    private Menu menu;

    ProgressDialog loading ;

    TextView Hemail ;
    TextView Hname ;

    SessionManager session;

    String email ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen2);

       // Intent i = getIntent();
       // email = i.getStringExtra("email");

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // email
        email = user.get(SessionManager.KEY_EMAIL);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.mycoordinatorlayout);


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        //Header set Text
        Hemail = (TextView)headerView.findViewById(R.id.txtEmail);
        Hname = (TextView)headerView.findViewById(R.id.txtName);


        if(email == "xyz@gmail.com")
        {
            Menu menu = navigationView.getMenu();

            // find MenuItem you want to change
            MenuItem nav_signout = menu.findItem(R.id.Signout);

            // set new title to the MenuItem
            nav_signout.setTitle("Sign in");
            nav_signout.setIcon(R.drawable.login);
        }

        HomeFragment homeFragment=new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home");
         DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        getData();

    }

    private void getData() {

        loading = ProgressDialog.show(HomeScreenActivity.this,"Please wait...","Fetching...",false,false);

        String url = ConfigProfile.DATA_URL+email ;

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
                        Toast.makeText(HomeScreenActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String fname="Welcome";
        String lname="Guest";
        String email = " ";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(ConfigProfile.JSON_ARRAY);
            JSONObject userData = result.getJSONObject(0);
            fname = userData.getString(ConfigProfile.KEY_FNAME);
            lname = userData.getString(ConfigProfile.KEY_LNAME);
            email = userData.getString(ConfigProfile.KEY_EMAIL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Hname.setText(fname+" "+lname);
        Hemail.setText(email);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int fragments = getFragmentManager().getBackStackEntryCount();
            if (fragments > 0) {
                super.onBackPressed();
            } else {
                if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
                    super.onBackPressed();
                } else {
                    Toast.makeText(getBaseContext(), "Press once again to exit!",
                            Toast.LENGTH_SHORT).show();
                }
                back_pressed = System.currentTimeMillis();
            }
    }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_screen, menu);

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            HomeFragment homeFragment=new HomeFragment();
            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container,homeFragment);

            ft.commit();
            getSupportActionBar().setTitle("Home");

        } else if (id == R.id.profile) {
            Bundle bundle = new Bundle();
            bundle.putString("message",email );
            ProfileFragment profileFragment=new ProfileFragment();
            profileFragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container,profileFragment);
            ft.commit();
            getSupportActionBar().setTitle("Your Profile");

        } else if (id == R.id.changepasss) {
            ChangePassword wishlistFragment=new ChangePassword();
            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container,wishlistFragment);
            ft.commit();
            getSupportActionBar().setTitle("Change Password");
        } else if (id == R.id.Signout) {

            Menu menu = navigationView.getMenu();

            // find MenuItem you want to change
            MenuItem nav_signout = menu.findItem(R.id.Signout);

            if(nav_signout.getTitle() == "Sign in")
            {
                Intent intent = new Intent(HomeScreenActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
            else{

                AlertDialog.Builder builder1 = new AlertDialog.Builder(HomeScreenActivity.this,R.style.AppCompatAlertDialogStyle);
                builder1.setTitle("Sign Out");
                builder1.setMessage("Do you want to Signout?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Hname.setText("Welcome Guest");
                                Hemail.setText("xyz@gmail.com");
                                Intent intent = new Intent(HomeScreenActivity.this,
                                        LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                                Toast.makeText(HomeScreenActivity.this,"You are succesfully signed out !!",Toast.LENGTH_LONG).show();

                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }


         /*   Button neg = alert11 .getButton(DialogInterface.);
            neg.setTextColor(Color.BLACK);

            Button pos = alert11 .getButton(DialogInterface.BUTTON_POSITIVE);
            pos.setTextColor(Color.BLACK);
*/

        } else if (id == R.id.aboutdev) {
            AboutDevelopersFragment aboutDevFragment=new AboutDevelopersFragment();
            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container,aboutDevFragment);
            ft.commit();
            getSupportActionBar().setTitle("About Develpoers");
        }
        else if (id == R.id.aboutgic) {
            AboutGICFragment GICFragment = new AboutGICFragment();
            android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, GICFragment);
            ft.commit();
            getSupportActionBar().setTitle("About GIC");
        }
         else if (id == R.id.share) {

                String  PACKAGE_NAME = getApplicationContext().getPackageName();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Download GTU Innovation Council's official app from : https://play.google.com/store/apps/details?id=" + PACKAGE_NAME);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}


