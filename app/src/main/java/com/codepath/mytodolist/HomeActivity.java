package com.codepath.mytodolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.codepath.adapters.TasksAdapter;
import com.codepath.models.Task;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_lettering);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("My Todo List"); // set the top title
        String title = actionBar.getTitle().toString(); // get the title
       // actionBar.hide();

        // Construct the data source
        ArrayList<Task> arrayOfTasks = new ArrayList<Task>();
// Create the adapter to convert the array to views
        TasksAdapter adapter = new TasksAdapter(this, arrayOfTasks);

 // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvTasks);
        listView.setAdapter(adapter);

        // Query ActiveAndroid for list of data
        List<Task> queryResults = new Select().from(Task.class)
                .orderBy("Description ASC").limit(100).execute();

        // Load the result into the adapter using `addAll`
        adapter.addAll(queryResults);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // String lst_txt = parent.getItemAtPosition(position).toString().trim();

                Intent myIntent = new Intent(HomeActivity.this, EditItemActivity.class);
              //  myIntent.putExtra("keyItem", lst_txt);
                startActivityForResult(myIntent, REQUEST_CODE);
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_in_bottom);

            }
        });

        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
                alert.setTitle("Alert!!");
                alert.setMessage("Are you sure to delete record");

                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //remove item at position
                       // todoItems.remove(position);
                       // aTodoAdapter.notifyDataSetChanged();
                      //  writeItems();

                        dialog.dismiss();
                    }
                });

                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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


    public void onNewTask(MenuItem mi){
        Intent i = new Intent(HomeActivity.this, AddItemActivity.class);
        startActivity(i);
        }
}
