package com.trubnikov.numerology_person;

import java.util.Calendar;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startad.lib.SADView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FirstPartner extends Activity implements OnClickListener{


	Button next;

	private static Button date1;
	private static int birthDay1;
	private static int birthMonth1;
	private static int birthYear1;
	String surname1, name1, patronymic1;
	
	
	EditText eSur1,eName1,ePatr1;
	private static String [] months;
	
	DB db;

	AlertDialog.Builder ad;
	Context context;
	
	//Реклама StartAd.mobi
	protected SADView sadView;
	//Реклама
	private AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_partner);
		
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
		surname1=intent.getStringExtra("sur1");
		name1=intent.getStringExtra("nam1");
		patronymic1=intent.getStringExtra("pat1");
		birthDay1=intent.getIntExtra("day1", 0);
		birthMonth1=intent.getIntExtra("mon1", 0);
		birthYear1=intent.getIntExtra("yea1", 0);
		
		
		eSur1=(EditText) findViewById(R.id.etSurname1); eSur1.setText(surname1);
		eName1=(EditText) findViewById(R.id.etFullName1); eName1.setText(name1);
		ePatr1=(EditText) findViewById(R.id.etPatronymic1); ePatr1.setText(patronymic1);
		next= (Button) findViewById(R.id.bNext); next.setOnClickListener(this);
		date1=(Button) findViewById(R.id.dateButton1);
		try {date1.setText(birthDay1+" "+months[(birthMonth1-1)]+" "+birthYear1);} catch (Exception e) {}//try catch для неправильных месяцев

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
	birthDay1 = day;
	birthMonth1 = month+1;
	birthYear1 = year;
	date1.setText(day+" "+months[month]+" "+year);
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
		
		case R.id.bNext:{
    		surname1 = eSur1.getText().toString().trim();
    		name1 = eName1.getText().toString().trim();
    		patronymic1 = ePatr1.getText().toString().trim();
			if (surname1==null||name1==null||birthDay1==0) {Toast.makeText(getApplicationContext(), 
	                "Для расчета введите полные данные!", Toast.LENGTH_SHORT).show(); break;}
			else
			//Настройка AlertDialog
	        context = FirstPartner.this;
		    String title = "Сохранить данные в журнале?";
		    String button1String = "Да";
		    String button2String = "Нет";	    
		    String button3String = "Отмена";
		    ad = new AlertDialog.Builder(context);
	        ad.setTitle(title);  // заголовок
	        ad.setIcon(R.drawable.ic_launcher);//иконка
	        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int arg1) 
	            {
	            Toast.makeText(context, "Данные записаны в журнал",Toast.LENGTH_SHORT).show();
	            db.addRec(surname1, name1, patronymic1, birthDay1, birthMonth1, birthYear1);//Добавим запись в журнал
	            getCalc();
	            }
	        	
	        });
	        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int arg1) {
	            	Toast.makeText(context, "Данные не записаны", Toast.LENGTH_SHORT).show();
	            	getCalc();
	            }
	        });
	        ad.setNeutralButton(button3String, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int arg1) {
	                Toast.makeText(context, "Возврат к вводу данных", Toast.LENGTH_SHORT).show();
	                }
	        });
	        ad.setCancelable(true);
	        ad.setOnCancelListener(new OnCancelListener() {
	            public void onCancel(DialogInterface dialog) {
	                Toast.makeText(context, "Вы ничего не выбрали",Toast.LENGTH_SHORT).show();}
	        });	
	        ad.show();
		}
		}
	}

	void getCalc (){//передадим данные в расчет
        Calculations calc = new Calculations(getApplicationContext()); //Передаем Context в конструктор класса для возможности вызова ресурсов
		calc.calculation(surname1, name1, patronymic1, birthDay1, birthMonth1, birthYear1);
        finish();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_first, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		switch (item.getItemId()) 
		{
		case R.id.clear: {surname1=null; name1=null; patronymic1=null; birthDay1=0; birthMonth1=0; birthYear1=0; 
		eSur1.setText(""); eName1.setText(""); ePatr1.setText("");date1.setText("");} return true;
		case R.id.journal: {Intent intent = new Intent(this,Journal.class); transData(intent); startActivity(intent);finish();} return true;
		case R.id.main_m: {Intent intent = new Intent(this,MainMenu.class); transData(intent); startActivity(intent);finish();}	return true;
		case R.id.exit: finish(); return true;
		
		default: return super.onOptionsItemSelected(item);
		}
	}



	private void transData(Intent intent) { //передача данных между активити
		surname1 = eSur1.getText().toString().trim();
		name1 = eName1.getText().toString().trim();
		patronymic1 = ePatr1.getText().toString().trim();

		intent.putExtra("sur1", surname1);
		intent.putExtra("nam1", name1);
		intent.putExtra("pat1", patronymic1);
		intent.putExtra("day1", birthDay1);
		intent.putExtra("mon1", birthMonth1);
		intent.putExtra("yea1", birthYear1);
	}

	//диалог кнопки Back
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		openQuitDialog();
	}

	private void openQuitDialog() {
		
		//Настройка AlertDialog
        context = FirstPartner.this;
	    String title = "Выход?";
	    String button1String = "Выход";
	    String button2String = "Главное меню";	    
	    String button3String = "Отмена";
	    ad = new AlertDialog.Builder(context);
        ad.setTitle(title);  // заголовок
        ad.setIcon(R.drawable.ic_launcher);//иконка
        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) 
            {
            	finish();            }
        });
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            	goBack();
            }
        });
        ad.setNeutralButton(button3String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {               
                }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                }
        });	

        ad.show();
	}
	void goBack(){
		Intent intent = new Intent(this,MainMenu.class);
		transData(intent);
		startActivity(intent);finish();	
		}

}
