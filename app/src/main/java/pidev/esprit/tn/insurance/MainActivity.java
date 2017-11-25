package pidev.esprit.tn.insurance;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pidev.esprit.tn.insurance.Model.Sinister;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentTransaction fragmentTransaction;
    public static List<Sinister> listSinister =new ArrayList<Sinister>();
    private String mJSONURLString = "http://10.0.2.2:18080/insurance-web/api/sinister";
    private Context mContext;
    private Sinister[] items = new Sinister[1000];
    private ListView listView;

    public static ArrayAdapter<Sinister> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = getApplicationContext();
        listView = (ListView)findViewById(R.id.listView);;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Add sinister");
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()) {
                            try {
                                // Loop through the array elements

                                // Get current json object
                                JSONObject student = response.getJSONObject(count);
                                Log.i("1", "4");
                                // Get the current student (json object) data
                                String nameInsured = student.getString("nameInsured");
                                String nameConductor = student.getString("nameConductor");
                                Sinister sinister = new Sinister();
                                sinister.setNameInsured(nameInsured);
                                sinister.setNameConductor(nameConductor);
                                listSinister.add(sinister);
                                Log.i("sinister", sinister.getNameInsured());
                                Log.i("sinister", listSinister.get(count).getNameInsured());
                                items[count]= listSinister.get(count);


                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter = new ArrayAdapter<Sinister>(MainActivity.this,android.R.layout.simple_list_item_1,items);
                        if(!adapter.isEmpty()){
                            Log.i("1", "is not empty");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Do something when error occurred
                Log.i("1", "*****");

            }
        }

        );
        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
        //listView.setAdapter(adapter);










    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displaySelectedScreen(item.getItemId());
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_adSinister:
                fragment = new ListSinisterFragment();
                break;
            case R.id.nav_menu2:
                //fragment = new Menu2();
                break;
            case R.id.nav_menu3:
                //fragment = new Menu3();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            Log.i("fragment","à l'interieur");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
