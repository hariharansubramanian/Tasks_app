package com.example.hari.taskit;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class TaskActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Task task = (Task) getIntent().getSerializableExtra("EXTRA");
        Log.d("MainActivity", task.getName());
        EditText taskNameInput = (EditText) findViewById(R.id.taskname);
        CheckBox doneBox = (CheckBox) findViewById(R.id.donecheckbox);
        Button dateBtn = (Button) findViewById(R.id.taskdatebtn);
        Button submitBtn = (Button) findViewById(R.id.submittask);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(task.getDueDate());

        taskNameInput.setText(task.getName());
        if (task.getDueDate() == null) {
            dateBtn.setText(R.string.No_Date);
        } else {
            DateFormat dateFormat = DateFormat.getDateInstance();
            dateBtn.setText(dateFormat.format(task.getDueDate()));
        }
        doneBox.setChecked(task.isDone());
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(TaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                dpd.show();

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
