package com.codepath.mytodolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ArrayList<String> todoItems;
    ArrayAdapter<String> aTodoAdapter;
    ListView lvItems;
    EditText etEditText;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(aTodoAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);
        this.lvItems.setLongClickable(true);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Alert!!");
                alert.setMessage("Are you sure to delete record");

                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //remove item at position
                        todoItems.remove(position);
                        aTodoAdapter.notifyDataSetChanged();
                        writeItems();
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

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String lst_txt = parent.getItemAtPosition(position).toString().trim();

                Intent myIntent = new Intent(MainActivity.this, EditItemActivity.class);
                myIntent.putExtra("keyItem", lst_txt);
                startActivityForResult(myIntent, REQUEST_CODE);
                overridePendingTransition(R.anim.abc_slide_in_top ,R.anim.abc_slide_in_bottom);
                todoItems.remove(position);
            }
        });

    }

    public void populateArrayItems(){
        readItems();
        aTodoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,todoItems);
    }

    public void onAddItem(View v){

        if (etEditText.getText().toString()!= null && etEditText.getText().toString().length()>0) {

            aTodoAdapter.add(etEditText.getText().toString());
            etEditText.setText("");
            writeItems();
        }
        else{
            // tell the user to type the items if they did not d
            Toast badCredentials = Toast.makeText(getApplicationContext(),"Please type the item to add",Toast.LENGTH_SHORT);
            badCredentials.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            badCredentials.show();
        }
    }

    private void readItems(){
        File filesDir = getFilesDir();
        File file = new File(filesDir,"todo.txt");
        try{
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        }catch (IOException e){

        }
    }

    private void writeItems(){
        File filesDir = getFilesDir();
        File file = new File(filesDir,"todo.txt");
        try{
            FileUtils.writeLines(file,todoItems);
        }catch (IOException e){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String name = data.getExtras().getString("name");
           // int code = data.getExtras().getInt("code", 0);
            // Toast the name to display temporarily on screen
          //  aTodoAdapter.notifyDataSetChanged();
          //  writeItems();

      //      Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        //    aTodoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,todoItems);
        //    aTodoAdapter.notifyDataSetInvalidated();
         //   populateArrayItems();

            aTodoAdapter.add(name);
            aTodoAdapter.notifyDataSetChanged();

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
