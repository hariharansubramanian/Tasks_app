package com.example.hari.taskit;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class TaskListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Task[] listItems = new Task[3];
        listItems[0] = new Task();
        listItems[0].setName("Task 1");
        listItems[1] = new Task();
        listItems[1].setName("Task 2");
        listItems[2] = new Task();
        listItems[2].setName("Task 3");
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new TaskAdapter(listItems));
    }

    private class TaskAdapter extends ArrayAdapter<Task> {
        TaskAdapter(Task[] tasks) {
            super(TaskListActivity.this, R.layout.list_view_layout,R.id.textview1, tasks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
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
