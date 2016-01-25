package com.trubnikov.numerology_person;

import java.util.Calendar;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startad.lib.SADView;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

public class EditPartner extends Activity implements OnClickListener{


	Button save;

	private static Button dateEd;
	private static long idEd;
	private static int birthDayEd;
	private static int birthMonthEd;
	private static int birthYearEd;
	private String surnameEd, nameEd, patronymicEd;
	
	EditText eSur,eName,ePatr;
	private static String [] months;
	
	DB db;

	Context context;
	
	//Реклама StartAd.mobi
	protected SADView sadView;
	//Реклама
	private AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_partner);
		
        // Реклама StartAd.mobi
        this.sadView = new SADView(this, getString(R.string.banner_start_ad_mobi_id));
        LinearLayout layout = (LinearLayout)findViewById(R.id.advLayout);
        layout.addView(this.sadView);
        this.sadView.loadAd(SADView.LANGUAGE_RU);
		
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
		
		
		
		months=getResources().getStringArray(R.array.months);
		
		Intent intent = getIntent();

		idEd=intent.getLongExtra("recId", 0);
		surnameEd=intent.getStringExtra("surEd");
		nameEd=intent.getStringExtra("namEd");
		patronymicEd=intent.getStringExtra("patEd");
		birthDayEd=intent.getIntExtra("dayEd", 0);
		birthMonthEd=intent.getIntExtra("monEd", 0);
		birthYearEd=intent.getIntExtra("yeaEd", 0);
		
		eSur=(EditText) findViewById(R.id.etSurnameEd); eSur.setText(surnameEd);
		eName=(EditText) findViewById(R.id.etFullNameEd); eName.setText(nameEd);
		ePatr=(EditText) findViewById(R.id.etPatronymicEd); ePatr.setText(patronymicEd);
		save= (Button) findViewById(R.id.bSave); save.setOnClickListener(this);
		dateEd=(Button) findViewById(R.id.dateButtonEd);
		try {dateEd.setText(birthDayEd+" "+months[(birthMonthEd-1)]+" "+birthYearEd);} catch (Exception e) {}//try catch для неправильных месяцев

	    // открываем подключение к БД
	    db = new DB(this);
	    db.open();
	}

// Установка даты -->
public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker
final Calendar c = Calendar.getInstance();
int year = c.get(Calendar.YEAR);
int month = c.get(Calendar.MONTH);
int day = c.get(Calendar.DAY_OF_MONTH);
// Create a new instance of DatePickerDialog and return it
return new DatePickerDialog(getActivity(), this, year, month, day);
}
public void onDateSet(DatePicker view, int year, int month, int day) {
// Do something with the date chosen by the user
	birthDayEd = day;
	birthMonthEd = month+1;
	birthYearEd = year;
	dateEd.setText(day+" "+months[month]+" "+year);
}
}
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");}
// <-- установка даты.	

	
	
	
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
		switch (v.getId())
		{
		case R.id.bSave:{
			surnameEd = eSur.getText().toString().trim();
			nameEd = eName.getText().toString().trim();
			patronymicEd = ePatr.getText().toString().trim();
		db.editRec(surnameEd, nameEd, patronymicEd, birthDayEd, birthMonthEd, birthYearEd, idEd);
		Intent intent = new Intent(this,Journal.class);
		startActivity(intent);finish(); break;}
		}
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
		case R.id.journal: {Intent intent = new Intent(this,Journal.class);  startActivity(intent);finish();} return true;
		case R.id.main_m: {Intent intent = new Intent(this,MainMenu.class);  startActivity(intent);finish();}	return true;
		case R.id.exit: finish(); return true;
		default: return super.onOptionsItemSelected(item);
		}
	}

	//диалог кнопки Back
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this,Journal.class);
		startActivity(intent);finish();		
	}

}
