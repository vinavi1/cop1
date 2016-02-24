package com.example.android.online2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    Menu m ;
    String url ;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    RequestQueue rq ;
    MainActivity instance1;
    Button butt;


    //variables for courses storage
    String[] c_array;
    String current_sem ;
    String current_year ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance1 = this ;

        rq = Volley.newRequestQueue(this);


        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;



        url="http://10.192.40.165:8000/courses/list.json";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject= new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("courses");
                    for (int i=0 ; i<jsonArray.length();i++) {
                        JSONObject j = jsonArray.getJSONObject(i);
                        String codename = j.getString("code");
                        m.add(1,i,0,codename);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }, this);


        rq.add(stringRequest);

        m = mNavigationView.getMenu();
        m.add(2,10,0,"Log_out");

        Intent intent = getIntent();
        String name = intent.getStringExtra("first")+" "+intent.getStringExtra("last");
        String entry_no = intent.getStringExtra("entry_no");
        String email = intent.getStringExtra("email");
        /**
         *Setup the DrawerLayout and NavigationView
         */




        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("entry_no",entry_no);
        bundle.putString("email",email);
        // set Fragmentclass Arguments
        Overview overobj = new Overview();
        overobj.setArguments(bundle);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,overobj).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();



                if (menuItem.getItemId() == 5 ) {
                    final int y = menuItem.getTitle().length();
                    url = "http://10.192.40.165:8000/default/logout.json";
                    StringRequest jlogout = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            try {
                                JSONObject noticount= new JSONObject(s);
                                if (noticount.getInt("noti_count")==4){
                                    SharedPreferences.Editor editor= sharedPreferences.edit();
                                    editor.clear();
                                    editor.commit();
                                    Intent intent= new Intent(instance1,Login.class);
                                    startActivity(intent);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e("Volley","hello");
                        }
                    },instance1);
                    rq.add(jlogout);
                }
                if (menuItem.getItemId() == 6) {
                    FragmentTransaction yfragmentTransaction = mFragmentManager.beginTransaction();
                    yfragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();

                }

                if (menuItem.getItemId() == R.id.nav_item_grades) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new GradeActivity()).commit();

                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
        xfragmentTransaction.replace(R.id.containerView,new SocialFragment()).commit();
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent= new Intent(instance1,Login.class);
            startActivity(intent);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "click BACK again to log_out", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}