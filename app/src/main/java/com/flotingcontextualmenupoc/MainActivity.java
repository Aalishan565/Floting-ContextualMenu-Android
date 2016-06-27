package com.flotingcontextualmenupoc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    ArrayAdapter mArrayAdapter;
    String[] names;
    ArrayList<String> arrayListNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names = getResources().getStringArray(R.array.names);
        mListView = (ListView) findViewById(R.id.lv_names);
        arrayListNames = new ArrayList<>();
        for (String data : names) {
            arrayListNames.add(data);
        }
        mArrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.row_items, R.id.tv_names, arrayListNames);
        mListView.setAdapter(mArrayAdapter);
        registerForContextMenu(mListView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete:
                arrayListNames.remove(menuInfo.position);
                mArrayAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);

        }

    }
}
