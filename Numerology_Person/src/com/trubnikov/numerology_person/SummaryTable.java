package com.trubnikov.numerology_person;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class SummaryTable extends Activity implements OnClickListener{


	//ресурсы для вызова массива текстов
	Resources res;
	TypedArray text;
	int textNumber;
	String textToPopUp;
	TextView infoTextView, nameTextView;

	String partner;
	String partner1;
	Spannable spanPartn1;
	
	Button save;
	TextView firstPartner;
	TextView bd, s1, d1, cl, p1, lp1, m1, periodpp, peak1, prob1, permc, mc1, conc1, persyear1, persmonth, persday;
	String clouttext, clout;
	
	private int birthDay1;
	private int bdnumOneDigit;
	private String bd1;
	private int birthMonth1;
	private String bm1;
	private int birthYear1;
	String surname1, name1, patronymic1;
	int numSoul1;
	int numDest1;
	int cl1, cl2, cl3, cl4, cl5, cl6, cl7, cl8, cl9;
	int clsum;
	int numPers1;
	int numLifePath1;
	int numMatur1;
	int period;
	int perBegin, perEnd;
	int periodmc;
	int perBeginmc, perEndmc;
	int numPeak1;
	int numProblem1;
	int numMainCycle1;
	int compatRating;
	int concord1;
	int persYear1;
	int persMonth;
	int persDay;

	
	AlertDialog.Builder ad;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary_table);
		
		res = getResources();
		
		Intent intent = getIntent();

		surname1=intent.getStringExtra("sur1");
		name1=intent.getStringExtra("nam1");
		patronymic1=intent.getStringExtra("pat1");
		birthDay1=intent.getIntExtra("day1", 0);
		birthMonth1=intent.getIntExtra("mon1", 0);
		birthYear1=intent.getIntExtra("yea1", 0);
		numSoul1=intent.getIntExtra("s1", 0);
		numDest1=intent.getIntExtra("d1", 0);
		cl1=intent.getIntExtra("cl1", 0);
		cl2=intent.getIntExtra("cl2", 0);
		cl3=intent.getIntExtra("cl3", 0);
		cl4=intent.getIntExtra("cl4", 0);
		cl5=intent.getIntExtra("cl5", 0);
		cl6=intent.getIntExtra("cl6", 0);
		cl7=intent.getIntExtra("cl7", 0);
		cl8=intent.getIntExtra("cl8", 0);
		cl9=intent.getIntExtra("cl9", 0);
		
		clsum=cl1+cl2+cl3+cl4+cl5+cl6+cl7+cl8+cl9;
		
		numPers1=intent.getIntExtra("p1", 0);
		numLifePath1=intent.getIntExtra("lp1", 0);
		numMatur1=intent.getIntExtra("m1", 0);
		period=intent.getIntExtra("period", 0);
		perBegin=intent.getIntExtra("perBegin", 0);
		perEnd=intent.getIntExtra("perEnd", 0);
		numPeak1=intent.getIntExtra("peak1", 0);
		numProblem1=intent.getIntExtra("prob1", 0);
		periodmc=intent.getIntExtra("periodmc", 0);
		perBeginmc=intent.getIntExtra("perBeginmc", 0);
		perEndmc=intent.getIntExtra("perEndmc", 0);
		numMainCycle1=intent.getIntExtra("mc1", 0);
		compatRating=intent.getIntExtra("rsum", 0);
		concord1=intent.getIntExtra("con1", 0);
		persYear1=intent.getIntExtra("persyear1", 0);
		persMonth=intent.getIntExtra("persmonth", 0);
		persDay=intent.getIntExtra("persday", 0);
		
		bd1= (birthDay1<10) ? "0"+birthDay1:""+birthDay1; 
		bm1= (birthMonth1<10) ? "0"+birthMonth1:""+birthMonth1;

		partner1 = surname1+" "+name1;

		firstPartner=(TextView) findViewById(R.id.firstPartner); 
		firstPartner.setText(surname1+" "+name1+" "+patronymic1+" • "+bd1+"."+bm1+"."+birthYear1);
		
		bdnumOneDigit=oneDigit(birthDay1);
		bd=(TextView) findViewById(R.id.bd); bd.setText(""+bdnumOneDigit); bd.setOnClickListener(this);

		s1=(TextView) findViewById(R.id.s1);
		if (numSoul1==10||numSoul1==19) {s1.setText(""+numSoul1+"/1");}  
		else if (numSoul1==13) {s1.setText(""+numSoul1+"/4");}  
		else if (numSoul1==14) {s1.setText(""+numSoul1+"/5");}  
		else if (numSoul1==16) {s1.setText(""+numSoul1+"/7");}  
		else {s1.setText(""+numSoul1);}
		s1.setOnClickListener(this);

		d1=(TextView) findViewById(R.id.d1); 
		if (numDest1==10||numDest1==19) {d1.setText(""+numDest1+"/1");}  
		else if (numDest1==13) {d1.setText(""+numDest1+"/4");}  
		else if (numDest1==14) {d1.setText(""+numDest1+"/5");}  
		else if (numDest1==16) {d1.setText(""+numDest1+"/7");}  
		else {d1.setText(""+numDest1);}
		d1.setOnClickListener(this);

		cl=(TextView) findViewById(R.id.cl); cdsetText(); cl.setOnClickListener(this);

		p1=(TextView) findViewById(R.id.p1); p1.setText(""+numPers1); p1.setOnClickListener(this);
		
		lp1=(TextView) findViewById(R.id.lp1); 
		if (numLifePath1==10||numLifePath1==19) {lp1.setText(""+numLifePath1+"/1");}  
		else if (numLifePath1==13) {lp1.setText(""+numLifePath1+"/4");}  
		else if (numLifePath1==14) {lp1.setText(""+numLifePath1+"/5");}  
		else if (numLifePath1==16) {lp1.setText(""+numLifePath1+"/7");}  
		else{lp1.setText(""+numLifePath1);} 
		lp1.setOnClickListener(this);

		
		
		m1=(TextView) findViewById(R.id.m1); m1.setText(""+numMatur1); m1.setOnClickListener(this);

		periodpp=(TextView) findViewById(R.id.per); periodpp.setText(""+period); periodpp.setOnClickListener(this);

		peak1=(TextView) findViewById(R.id.peak1); peak1.setText(""+numPeak1); peak1.setOnClickListener(this);
		prob1=(TextView) findViewById(R.id.prob1); prob1.setText(""+numProblem1); prob1.setOnClickListener(this);

		permc=(TextView) findViewById(R.id.permc); permc.setText(""+periodmc); permc.setOnClickListener(this);

		mc1=(TextView) findViewById(R.id.mc1); mc1.setText(""+numMainCycle1); mc1.setOnClickListener(this);
		conc1=(TextView) findViewById(R.id.conc1); conc1.setText(""+concord1); conc1.setOnClickListener(this);
		persyear1=(TextView) findViewById(R.id.persyear1); persyear1.setText(""+persYear1); persyear1.setOnClickListener(this);
		persmonth=(TextView) findViewById(R.id.persmonth); persmonth.setText(""+persMonth); persmonth.setOnClickListener(this);
		persday=(TextView) findViewById(R.id.persday); persday.setText(""+persDay); persday.setOnClickListener(this);
	}

	void cdsetText(){//текст кармических уроков
		if (clsum==0){cl.setText("Нет"); clouttext = getString(R.string.carma_lesson_title_no);}
		else {
			String clText=""; clouttext = getString(R.string.carma_lesson_title);
			if (cl1==1) {clText=clText+"1 ,"; clout=getString(R.string.carma_lesson1); clouttext=clouttext+clout;} 
			if (cl2==2) {clText=clText+"2 ,"; clout=getString(R.string.carma_lesson2); clouttext=clouttext+clout;} 
			if (cl3==3) {clText=clText+"3 ,"; clout=getString(R.string.carma_lesson3); clouttext=clouttext+clout;} 
			if (cl4==4) {clText=clText+"4 ,"; clout=getString(R.string.carma_lesson4); clouttext=clouttext+clout;} 
			if (cl5==5) {clText=clText+"5 ,"; clout=getString(R.string.carma_lesson5); clouttext=clouttext+clout;} 
			if (cl6==6) {clText=clText+"6 ,"; clout=getString(R.string.carma_lesson6); clouttext=clouttext+clout;} 
			if (cl7==7) {clText=clText+"7 ,"; clout=getString(R.string.carma_lesson7); clouttext=clouttext+clout;} 
			if (cl8==8) {clText=clText+"8 ,"; clout=getString(R.string.carma_lesson8); clouttext=clouttext+clout;} 
			if (cl9==9) {clText=clText+"9 ,"; clout=getString(R.string.carma_lesson9); clouttext=clouttext+clout;} 
			clText=clText.substring(0, clText.length()-2);
			cl.setText(clText);
			}
	}

	private int oneDigit(int bdOneDigit) {//приведем сумму цифр к однозначному числу
		int b, a=0;
		b=bdOneDigit;
		while (bdOneDigit>9){
			a=a+b%10; 
			b/=10;
			if(b<10) {bdOneDigit=b+a; b=bdOneDigit; a=0;}
		}
		return bdOneDigit;}

	
	@Override
	public void onClick(View v) {
		switch (v.getId())
		{

		case R.id.bd:{text = res.obtainTypedArray(R.array.bd);   
		textToPopUp = getStringFromRawFile(SummaryTable.this, birthDay1-1);
		partner = partner1; putTextToPopUp();break;}

		
		case R.id.s1:{text = res.obtainTypedArray(R.array.soul);//вызываем нужный массив текстов   
		if (numSoul1<=11){textToPopUp = getStringFromRawFile(SummaryTable.this, numSoul1-1);} //getStringFromRawFile(SummaryTable.this, номер в массиве текстов)
		else {switch (numSoul1){
		case 13: textToPopUp = getStringFromRawFile(SummaryTable.this, 11); break;
		case 14: textToPopUp = getStringFromRawFile(SummaryTable.this, 12); break;
		case 16: textToPopUp = getStringFromRawFile(SummaryTable.this, 13); break;
		case 19: textToPopUp = getStringFromRawFile(SummaryTable.this, 14); break;
		case 22: textToPopUp = getStringFromRawFile(SummaryTable.this, 15); break;
		case 33: textToPopUp = getStringFromRawFile(SummaryTable.this, 16); break;
		case 44: textToPopUp = getStringFromRawFile(SummaryTable.this, 17); break;
		case 55: textToPopUp = getStringFromRawFile(SummaryTable.this, 18); break;
		case 66: textToPopUp = getStringFromRawFile(SummaryTable.this, 19); break;
		case 77: textToPopUp = getStringFromRawFile(SummaryTable.this, 20); break;
		case 88: textToPopUp = getStringFromRawFile(SummaryTable.this, 21); break;
		case 99: textToPopUp = getStringFromRawFile(SummaryTable.this, 22); break;
		}}
		partner = partner1; putTextToPopUp(); break;}

		case R.id.d1:{text = res.obtainTypedArray(R.array.dest);   
		if (numDest1<=11){textToPopUp = getStringFromRawFile(SummaryTable.this, numDest1-1);} 
		else {switch (numDest1){
		case 13: textToPopUp = getStringFromRawFile(SummaryTable.this, 11); break;
		case 14: textToPopUp = getStringFromRawFile(SummaryTable.this, 12); break;
		case 16: textToPopUp = getStringFromRawFile(SummaryTable.this, 13); break;
		case 19: textToPopUp = getStringFromRawFile(SummaryTable.this, 14); break;
		case 22: textToPopUp = getStringFromRawFile(SummaryTable.this, 15); break;
		case 33: textToPopUp = getStringFromRawFile(SummaryTable.this, 16); break;
		case 44: textToPopUp = getStringFromRawFile(SummaryTable.this, 17); break;
		case 55: textToPopUp = getStringFromRawFile(SummaryTable.this, 18); break;
		case 66: textToPopUp = getStringFromRawFile(SummaryTable.this, 19); break;
		case 77: textToPopUp = getStringFromRawFile(SummaryTable.this, 20); break;
		case 88: textToPopUp = getStringFromRawFile(SummaryTable.this, 21); break;
		case 99: textToPopUp = getStringFromRawFile(SummaryTable.this, 22); break;
		}}
		partner = partner1; putTextToPopUp(); break;}

		case R.id.cl:{   
		textToPopUp = clouttext.trim();
		partner = partner1; putTextToPopUp();break;}
		
		case R.id.p1:{text = res.obtainTypedArray(R.array.pers);   
		if (numPers1<=9){textToPopUp = getStringFromRawFile(SummaryTable.this, numPers1-1);}
		else {switch (numPers1){
		case 11: textToPopUp = getStringFromRawFile(SummaryTable.this, 9); break;
		case 22: textToPopUp = getStringFromRawFile(SummaryTable.this, 10); break;
		}}
		partner = partner1; putTextToPopUp();break;}

		case R.id.lp1:{text = res.obtainTypedArray(R.array.lifepath);   
		if (numLifePath1<=11){textToPopUp = getStringFromRawFile(SummaryTable.this, numLifePath1-1);}
		else {switch (numLifePath1){
		case 13: textToPopUp = getStringFromRawFile(SummaryTable.this, 11); break;
		case 14: textToPopUp = getStringFromRawFile(SummaryTable.this, 12); break;
		case 16: textToPopUp = getStringFromRawFile(SummaryTable.this, 13); break;
		case 19: textToPopUp = getStringFromRawFile(SummaryTable.this, 14); break;
		case 22: textToPopUp = getStringFromRawFile(SummaryTable.this, 15); break;
		case 33: textToPopUp = getStringFromRawFile(SummaryTable.this, 16); break;
		case 44: textToPopUp = getStringFromRawFile(SummaryTable.this, 17); break;
		}}
		partner = partner1; putTextToPopUp();break;}

		case R.id.m1:{text = res.obtainTypedArray(R.array.maturity);   
		if (numMatur1<=9){textToPopUp = getStringFromRawFile(SummaryTable.this, numMatur1-1);}
		else {switch (numMatur1){
		case 11: textToPopUp = getStringFromRawFile(SummaryTable.this, 9); break;
		case 22: textToPopUp = getStringFromRawFile(SummaryTable.this, 10); break;
		case 33: textToPopUp = getStringFromRawFile(SummaryTable.this, 11); break;
		case 44: textToPopUp = getStringFromRawFile(SummaryTable.this, 12); break;
		case 55: textToPopUp = getStringFromRawFile(SummaryTable.this, 13); break;
		case 66: textToPopUp = getStringFromRawFile(SummaryTable.this, 14); break;
		case 77: textToPopUp = getStringFromRawFile(SummaryTable.this, 15); break;
		case 88: textToPopUp = getStringFromRawFile(SummaryTable.this, 16); break;
		case 99: textToPopUp = getStringFromRawFile(SummaryTable.this, 17); break;
		}}
		partner = partner1; putTextToPopUp();break;}

		case R.id.per:{   
			switch (period) {
			case 1: textToPopUp = getString(R.string.life_period1)+" "+perEnd+" лет."; break;
			case 2: textToPopUp = getString(R.string.life_period2)+" "+perBegin+" и до "+perEnd+" лет."; break;
			case 3: textToPopUp = getString(R.string.life_period3)+" "+perBegin+" и до "+perEnd+" лет."; break;
			case 4: textToPopUp = getString(R.string.life_period4)+" "+perBegin+" лет."; break;
			}
		partner = partner1; putTextToPopUp();break;}
		
		case R.id.peak1:{text = res.obtainTypedArray(R.array.peaks);   
		textToPopUp = getStringFromRawFile(SummaryTable.this, numPeak1-1);
		partner = partner1; putTextToPopUp();break;}

		case R.id.prob1:{text = res.obtainTypedArray(R.array.problems);   
		textToPopUp = getStringFromRawFile(SummaryTable.this, numProblem1);
		partner = partner1; putTextToPopUp();break;}

		case R.id.permc:{   
			switch (periodmc) {
			case 1: textToPopUp = getString(R.string.mc_period1)+" "+perEndmc+" лет."; break;
			case 2: textToPopUp = getString(R.string.mc_period2)+" "+perBeginmc+" и до "+perEndmc+" лет."; break;
			case 3: textToPopUp = getString(R.string.mc_period3)+" "+perBeginmc+" лет."; break;
			}
		partner = partner1; putTextToPopUp();break;}

		case R.id.mc1:{text = res.obtainTypedArray(R.array.maincycle);   
		if (numMainCycle1<=9){textToPopUp = getStringFromRawFile(SummaryTable.this, numMainCycle1-1);}
		else {switch (numMainCycle1){
		case 11: textToPopUp = getStringFromRawFile(SummaryTable.this, 9); break;
		case 13: textToPopUp = getStringFromRawFile(SummaryTable.this, 10); break;
		case 14: textToPopUp = getStringFromRawFile(SummaryTable.this, 11); break;
		case 16: textToPopUp = getStringFromRawFile(SummaryTable.this, 12); break;
		case 19: textToPopUp = getStringFromRawFile(SummaryTable.this, 13); break;
		case 22: textToPopUp = getStringFromRawFile(SummaryTable.this, 14); break;
		}}		
		partner = partner1; putTextToPopUp();break;}

		
		case R.id.conc1:{text = res.obtainTypedArray(R.array.concord);   
		textToPopUp = getStringFromRawFile(SummaryTable.this, concord1-1);
		partner = partner1; putTextToPopUp();break;}
		case R.id.persyear1:{text = res.obtainTypedArray(R.array.persyear);   
		textToPopUp = getStringFromRawFile(SummaryTable.this, persYear1-1);
		partner = partner1; putTextToPopUp();break;}
		case R.id.persmonth:{text = res.obtainTypedArray(R.array.persmon);   
		textToPopUp = getStringFromRawFile(SummaryTable.this, persMonth-1);
		partner = partner1; putTextToPopUp();break;}
		case R.id.persday:{text = res.obtainTypedArray(R.array.persday);   
		textToPopUp = getStringFromRawFile(SummaryTable.this, persDay-1);
		partner = partner1; putTextToPopUp();break;}
		}
	}

	void putTextToPopUp ()
	{
		spanPartn1=new SpannableString(partner+"\n\n"+textToPopUp); //форматированнный текст
		spanPartn1.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,partner.length(), 
		Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		showPopup(SummaryTable.this);	
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

	// The method that displays the popup.
	//private void showPopup(final Activity context, String txt) {
		private void showPopup(final Activity context) {
	   // Inflate the popup_layout.xml
	   LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
	   LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

	   // Creating the PopupWindow
	   final PopupWindow popup = new PopupWindow(context);
	   popup.setContentView(layout);
	   popup.setWidth(LayoutParams.WRAP_CONTENT);
	   popup.setHeight(LayoutParams.WRAP_CONTENT);
		//текст в PopUpWindow
	   infoTextView = (TextView) popup.getContentView().findViewById(R.id.textViewPop);
       infoTextView.setText(spanPartn1);
	   popup.setFocusable(true);
	 
	   // Displaying the popup at the specified location, + offsets.
	   popup.showAtLocation(layout, Gravity.CENTER, 0, 0);
	 
	   // Getting a reference to Close button, and close the popup when clicked.
	   Button close = (Button) layout.findViewById(R.id.close);
	   close.setOnClickListener(new OnClickListener() {
	     @Override
	     public void onClick(View v) {
	       popup.dismiss();
	     }
	   });
	}


	//вызов текста из RAW файла
	private String  getStringFromRawFile(Activity activity, int index) {
	    Resources r = activity.getResources();
	    InputStream is = null;
	    is = r.openRawResource(text.getResourceId(index, 0));
	    text.recycle();
	    
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


	//диалог кнопки Back
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		openQuitDialog();
	}

	private void openQuitDialog() {
		
		//Настройка AlertDialog
        context = SummaryTable.this;
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
		startActivity(intent);finish();	
		}

}
