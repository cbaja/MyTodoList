package com.codepath.mytodolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


public class EditItemActivity extends AppCompatActivity  {

    EditText etEditText;
    EditText edtTaskName;
    EditText edtDueDate;
    Spinner spStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_lettering);
        getSupportActionBar().setDisplayUseLogoEnabled(true); // Display the logo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the Up button
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("Edit Task"); // set the top title
        String title = actionBar.getTitle().toString();

        edtTaskName = (EditText)findViewById(R.id.edtTaskName);
        edtDueDate = (EditText)findViewById(R.id.edtDueDate);
        spStatus = (Spinner)findViewById(R.id.spStatus);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("keyItem");
            etEditText.setText(j);
        }
    }

    public void onSave(View v){
        edtTaskName = (EditText)findViewById(R.id.edtTaskName);
        edtDueDate = (EditText)findViewById(R.id.edtDueDate);
        spStatus = (Spinner)findViewById(R.id.spStatus);
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("name", etEditText.getText().toString());
        data.putExtra("name", edtTaskName.getText().toString());
        data.putExtra("name", spStatus.getSelectedView().toString());
       // data.putExtra("code", 200); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
      //  overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_in_top);
        finish(); // closes the activity, pass data to parent
/*
        Intent data = new Intent();
        data.putExtra("et", findViewById(R.id.etEditText).getText().toString());
        setResult(RESULT_OK, data);
        finish();
        */

    }

    public void onCancel(View v){

        setResult(RESULT_CANCELED);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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
