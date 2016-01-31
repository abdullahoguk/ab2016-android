package tr.org.ab.deneme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private TextView username;
    private String mUsername;
    private ListView cities;
    private ArrayList<String> cityList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Log.d("yasamdongusu", "Activity baslatildi.");

        username = (TextView) findViewById(R.id.username);
        mUsername = getIntent().getStringExtra("username");
        username.setText("Selam " + mUsername);

        cities = (ListView) findViewById(R.id.cities);


        fillInCities();
        adapter = new ArrayAdapter<String>(
                Dashboard.this,
                android.R.layout.simple_list_item_1,
                cityList
        );

        //List viewi adaptera baglama
        cities.setAdapter(adapter);


        //Iteme tıklanınca Toast goster
        cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        Dashboard.this,
                        cityList.get(position) + " is Great",
                        Toast.LENGTH_LONG
                ).show();
            }
        });


        //Iteme uzun basınca
        cities.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);

                /*
                Toast.makeText(
                        Dashboard.this,
                        cityList.get(position) + " will be deleted",
                        Toast.LENGTH_LONG
                ).show();
                //return false;*/

                return false;
            }
    });}



    private void showAlertDialog(final int position){
        AlertDialog.Builder dialog = new AlertDialog.Builder(Dashboard.this);
        dialog.setTitle("ar yu şuur");
        dialog.setMessage(
                cityList.get(position) + " to be deleted?"
        );
        dialog.setCancelable(false);
        dialog.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCity(position);
                        Toast.makeText(Dashboard.this,
                                "Item Deleted",
                                Toast.LENGTH_SHORT);
                    }
                });
                dialog.create().show();
    }




    private void deleteCity(int position){
    cityList.remove(position);
    adapter.notifyDataSetChanged();}


    private void fillInCities() {
        cityList.add("Istanbul");
        cityList.add("Adana");
        cityList.add("Izmir");
        cityList.add("Düzce");
        cityList.add("Ordu");
        cityList.add("Manisa");
    }

    @Override
    protected void onPause() {
        Log.d("yasamdongusu", "onPause() calisti");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("yasamdongusu", "onResume() calisti");
    }

    @Override
    protected void onDestroy() {
        Log.d("yasamdongusu", "Activity kapatildi.");
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
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
}
