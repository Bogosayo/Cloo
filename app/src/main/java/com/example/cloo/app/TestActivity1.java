package com.example.cloo.app;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class TestActivity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test_activity1);
        setTitle("");
        ActionBar actionBar = getActionBar();
        actionBar.setIcon(R.drawable.angry);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void GetNewTitle(View View){
        EditText setTitleEditText = (EditText) findViewById(R.id.SetTitleEditText);
        setTitle(setTitleEditText.getText().toString());
        setTitleEditText.setText("");
        ActionBar actionBar = getActionBar();
        actionBar.setIcon(R.drawable.joey);
    }

}
