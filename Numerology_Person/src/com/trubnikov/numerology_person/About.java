package com.trubnikov.numerology_person;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class About extends Activity implements OnClickListener{

	Button back;


	TextView infoTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
	
		infoTextView = (TextView) findViewById(R.id.textViewInfo);
		infoTextView.setText(getStringFromRawFile(About.this));
		
		back= (Button) findViewById(R.id.bBack); back.setOnClickListener(this);
	}

	private String  getStringFromRawFile(Activity activity) {
	    Resources r = activity.getResources();
	    InputStream is = null;
	    is = r.openRawResource(R.raw.about);
	    
	    String myText = null;
	    try {
	        myText = convertStreamToString(is);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    try {
	        is.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return  myText;
	}

	private String  convertStreamToString(InputStream is) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    int i = is.read();
	    while( i != -1)
	    {
	        baos.write(i);
	        i = is.read();
	    }
	    return  baos.toString();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_edit, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		switch (item.getItemId()) 
		{
//		case R.id.journal: {Intent intent = new Intent(this,Journal.class);  startActivity(intent);finish();} return true;
		case R.id.main_m: {Intent intent = new Intent(this,MainMenu.class);  startActivity(intent);finish();}	return true;
		case R.id.exit: finish(); return true;
		default: return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) {
			 Intent intent = new Intent(this,MainMenu.class); startActivity(intent);finish(); 
		}
}
