package com.example.hari.taskit;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;


public class TaskListActivity extends ActionBarActivity {
    private static final int EDIT_TASK_REQUEST=10;
    private int itemClickPosition; //to capture which item is selected from listView
    private ArrayList<Task> listItems;
    private TaskAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        listItems = new ArrayList<>();
        listItems.add(new Task());
        listItems.get(0).setName("Task 1");
        listItems.get(0).setDueDate(new Date());
        listItems.get(0).setDone(true);
        listItems.add(new Task());
        listItems.get(1).setName("Task 2");
        listItems.add(new Task());
        listItems.get(2).setName("Task 3");
        ListView listView = (ListView) findViewById(R.id.listView);
        mAdapter = new TaskAdapter(listItems);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MainActivity", "Position clicked is " + position);
                itemClickPosition = position;
                Task task = (Task) parent.getAdapter().getItem(position);
                Intent i = new Intent(TaskListActivity.this, TaskActivity.class);
                i.putExtra("EXTRA", task);
                startActivityForResult(i,EDIT_TASK_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_TASK_REQUEST) {
            if (resultCode == RESULT_OK) {
                Task task = (Task) data.getSerializableExtra("EXTRA");
                listItems.set(itemClickPosition,task);
                mAdapter.notifyDataSetChanged(); //Notify adapter to change listView

            }
        }
    }
    private class TaskAdapter extends ArrayAdapter<Task> {
        TaskAdapter(ArrayList<Task> tasks) {
            super(TaskListActivity.this, R.layout.list_view_layout, R.id.textview1, tasks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);
            Task task = getItem(position);//get object into task
            TextView taskName = (TextView) convertView.findViewById(R.id.textview1);//assign textview1 into TextView variable
            taskName.setText(task.getName());//set each TextView row to corresponding Task objects name
            CheckBox doneBox = (CheckBox) convertView.findViewById(R.id.checkbox1);
            doneBox.setChecked(task.isDone());
            return convertView;
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
