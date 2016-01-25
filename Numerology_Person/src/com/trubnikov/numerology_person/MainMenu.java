package com.trubnikov.numerology_person;

import org.apache.commons.lang3.StringUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startad.lib.SADView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainMenu extends Activity implements OnClickListener{
	LinearLayout layout, layout1;
	Button history, newCalc, about, exit;

	private int birthDay1;
	private int birthMonth1;
	private int birthYear1;
	String surname1, name1, patronymic1;

	Context context;
	AlertDialog.Builder ad;
	
	//Реклама StartAd.mobi
	protected SADView sadView;
	//Реклама
	private AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
        // Реклама StartAd.mobi
        this.sadView = new SADView(this, getString(R.string.banner_start_ad_mobi_id));
        LinearLayout layout = (LinearLayout)findViewById(R.id.advLayout);
        layout.addView(this.sadView);
        //Load ad for currently active language in app
 //       if(getResources().getString(R.string.hello_world).equals("Hello"))
 //       {this.sadView.loadAd(SADView.LANGUAGE_EN);} //or 
 //       else 
        {this.sadView.loadAd(SADView.LANGUAGE_RU);}
		
		//Реклама
	    // Creating an AdView
		adView = (AdView) findViewById(R.id.adView);
	    // Creating an AdRequest
	    AdRequest adRequest = new AdRequest.Builder()
	    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	    .addTestDevice("TEST_DEVICE_ID")
	    .build();
	    // Загрузка adView с объявлением.
	    adView.loadAd(adRequest);
		
		
		
		
		layout = (LinearLayout) findViewById(R.id.layout);
		layout1 = (LinearLayout) findViewById(R.id.layout1);
		Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.alpha_transparent2);
		layout1.startAnimation(animation1);
		
		Intent intent = getIntent();
		surname1=intent.getStringExtra("sur1");
		name1=intent.getStringExtra("nam1");
		patronymic1=intent.getStringExtra("pat1");
		birthDay1=intent.getIntExtra("day1", 0);
		birthMonth1=intent.getIntExtra("mon1", 0);
		birthYear1=intent.getIntExtra("yea1", 0);

		history= (Button) findViewById(R.id.bHistory); history.setOnClickListener(this);
		newCalc= (Button) findViewById(R.id.bNewCalc); newCalc.setOnClickListener(this);
		about= (Button) findViewById(R.id.bAbout); about.setOnClickListener(this);
		exit= (Button) findViewById(R.id.bExit); exit.setOnClickListener(this);

	}
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(null != this.sadView) this.sadView.saveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);

        if(null != this.sadView) this.sadView.restoreInstanceState(outState);
    }
		
	@Override
	  public void onPause() {
	    adView.pause();
	    super.onPause();
	  }

	  @Override
	  public void onResume() {
	    super.onResume();
	    adView.resume();
	  }

	  @Override
	  public void onDestroy() {
	    adView.destroy();
	    if (this.sadView != null) {this.sadView.destroy();}
	    super.onDestroy();
	  }


	@Override
	public void onClick(View v) {
		Intent intent=null;
		switch (v.getId())
		{
		case R.id.bHistory:{intent=new Intent(this,Journal.class);
		transData(intent);
		startActivity(intent);finish(); break;}
		case R.id.bNewCalc:{
			if 
			(((surname1==null&&name1==null&&patronymic1==null)||
				(StringUtils.isEmpty(surname1)&&StringUtils.isEmpty(name1)&&StringUtils.isEmpty(patronymic1)))&&
				(birthDay1==0&&birthMonth1==0&&birthYear1==0))
			{getNewCalc();}			
			else {
			//Настройка AlertDialog
	        context = MainMenu.this;
		    String title = "Очистить введенные данные?";
		    String button1String = "Да";
		    String button2String = "Нет";	    
		    String button3String = "Отмена";
		    ad = new AlertDialog.Builder(context);
	        ad.setTitle(title);  // заголовок
	        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int arg1) 
	            {
	        		surname1=name1=patronymic1="";
	        		birthDay1=birthMonth1=birthYear1=0;
	        		Toast.makeText(context, "Введенные данные очищены", Toast.LENGTH_SHORT).show();
	        		getNewCalc();
	            }
	        });
	        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int arg1) {
	                getNewCalc();
	            }
	        });
	        ad.setNeutralButton(button3String, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int arg1){}
	        });
	        ad.setCancelable(true);
	        ad.setOnCancelListener(new OnCancelListener() {
	            public void onCancel(DialogInterface dialog) {}
	        });	
	        ad.show();}
			
	        break;
		}
		
		
		case R.id.bAbout:{intent=new Intent(this,About.class);
		transData(intent);
		startActivity(intent);finish(); break;}
		case R.id.bExit: finish();break;
		}
	}

	void getNewCalc() //начало расчета
	{Intent intent=null;
	intent=new Intent(this,FirstPartner.class);
	transData(intent);
	startActivity(intent);finish();}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		switch (item.getItemId()) 
		{
		case R.id.first_partner: {Intent intent = new Intent(this,FirstPartner.class); transData(intent); startActivity(intent);finish();}	return true;
		case R.id.exit: finish(); return true;
		
		default: return super.onOptionsItemSelected(item);
		}
	}

	private void transData(Intent intent) { //передача данных между активити

		intent.putExtra("sur1", surname1);
		intent.putExtra("nam1", name1);
		intent.putExtra("pat1", patronymic1);
		intent.putExtra("day1", birthDay1);
		intent.putExtra("mon1", birthMonth1);
		intent.putExtra("yea1", birthYear1);
	}

}
