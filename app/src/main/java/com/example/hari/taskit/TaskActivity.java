package com.example.hari.taskit;

import android.app.DatePickerDialog;
import android.content.Intent;
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
    Button dateBtn;
    Calendar cal;
    Task task;
    EditText taskNameInput;
    CheckBox doneBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        task = (Task) getIntent().getSerializableExtra("EXTRA");
        Log.d("MainActivity", task.getName());
        taskNameInput = (EditText) findViewById(R.id.taskname);
        doneBox = (CheckBox) findViewById(R.id.donecheckbox);
        dateBtn = (Button) findViewById(R.id.taskdatebtn);
        Button submitBtn = (Button) findViewById(R.id.submittask);
        cal = Calendar.getInstance();
        if (task.getDueDate() == null) {
            cal.setTime(new Date());
        } else {
            cal.setTime(task.getDueDate());
        }
        taskNameInput.setText(task.getName());
        if (task.getDueDate() == null) {
            dateBtn.setText(R.string.No_Date);
        } else {
            updateButton();
        }
        doneBox.setChecked(task.isDone());
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(TaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, monthOfYear);
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateButton();
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                dpd.show();

            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setName(taskNameInput.getText().toString());
                task.setDueDate(cal.getTime());
                task.setDone(doneBox.isChecked());
                Intent i = new Intent();
                i.putExtra("EXTRA", task);
                setResult(RESULT_OK, i);
                finish();

            }
        });
    }

    private void updateButton() {
        DateFormat dateFormat = DateFormat.getDateInstance();
        dateBtn.setText(dateFormat.format(cal.getTime()));
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
