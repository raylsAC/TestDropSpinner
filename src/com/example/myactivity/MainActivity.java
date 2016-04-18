package com.example.myactivity;

import com.example.myview.DropSpinnerView;
import com.example.myview.DropSpinnerView.CallBack;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	public DropSpinnerView mDropView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initUI();
    }
    
	private void initUI() {
		mDropView = (DropSpinnerView) findViewById(R.id.dropview);
		mDropView.setArrowClickable(true);
		mDropView.setOnArrowClickListener(new CallBack() {
			@Override
			public void onArrowClick() {
				Toast.makeText(MainActivity.this, "onclick", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
