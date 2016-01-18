package com.codepath.mytodolist;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.codepath.models.Task;

import java.util.Date;


public class AddItemActivity extends AppCompatActivity {

    EditText edtTaskName;
    EditText edtDueDate;
    Spinner spStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_lettering);
        getSupportActionBar().setDisplayUseLogoEnabled(true); // Display the logo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the Up button
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("New Task"); // set the top title
        String title = actionBar.getTitle().toString();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
         // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
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

    public void onSave(View v){

        Date currentDate = new Date();

        Task task = new Task();

 //       if (edtTaskName.getText().toString()!= null && edtTaskName.getText().toString().length()>0) {

            task.description = edtTaskName.getText().toString();
            task.duedate = currentDate;
            task.statu = spStatus.getSelectedItem().toString();

            task.save();

           // aTodoAdapter.add(edtTaskName.getText().toString());
           // etEditText.setText("");
           // writeItems();
   //     }
    /*    else{
            // tell the user to type the items if they did not d
            Toast badCredentials = Toast.makeText(getApplicationContext(),"Please type the item to add",Toast.LENGTH_SHORT);
            badCredentials.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            badCredentials.show();
        }
        */

    }
}
