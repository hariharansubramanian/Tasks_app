package com.example.hari.taskit;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class TaskActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Task task= (Task) getIntent().getSerializableExtra("EXTRA");
        Log.d("MainActivity",task.getName());
        EditText taskNameInput= (EditText) findViewById(R.id.taskname);
        CheckBox doneBox= (CheckBox) findViewById(R.id.donecheckbox);
        Button dateBtn= (Button) findViewById(R.id.taskdatebtn);
        Button submitBtn= (Button) findViewById(R.id.submittask);

        taskNameInput.setText(task.getName());
        if(task.getDueDate()==null){
            dateBtn.setText(R.string.No_Date);
        }else {
            dateBtn.setText(task.getDueDate().toString());
        }
        doneBox.setChecked(task.isDone());
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task, menu);
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
