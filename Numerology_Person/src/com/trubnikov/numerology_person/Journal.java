package com.trubnikov.numerology_person;

import java.util.concurrent.TimeUnit;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startad.lib.SADView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Journal extends Activity implements OnClickListener, OnItemClickListener, LoaderCallbacks<Cursor>{
	Button next;
	TextView firstPartner;

	AlertDialog.Builder ad;
	Context context;

	private int birthDay1;
	private String bd1;
	private int birthMonth1;
	private String bm1;
	private int birthYear1;
	private String surname1, name1, patronymic1;
	private int birthDayEd;
	private int birthMonthEd;
	private int birthYearEd;
	private String surnameEd, nameEd, patronymicEd;

	  ListView lvData;
	  DB db;
	  SimpleCursorAdapter scAdapter;
	  Cursor cursor;
	
	//������� StartAd.mobi
	protected SADView sadView;
	//�������
	private AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.journal);

		firstPartner=(TextView) findViewById(R.id.firstPartner); firstPartner.setOnClickListener(this);

 // ������� StartAd.mobi
        this.sadView = new SADView(this, getString(R.string.banner_start_ad_mobi_id));
        LinearLayout layout = (LinearLayout)findViewById(R.id.advLayout);
        layout.addView(this.sadView);
        {this.sadView.loadAd(SADView.LANGUAGE_RU);}
		
		//�������
	    // Creating an AdView
		adView = (AdView) findViewById(R.id.adView);
	    // Creating an AdRequest
	    AdRequest adRequest = new AdRequest.Builder()
	    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	    .addTestDevice("TEST_DEVICE_ID")
	    .build();
	    // �������� adView � �����������.
	    adView.loadAd(adRequest);
		
		
		
		
		
		Intent intent = getIntent();
		surname1=intent.getStringExtra("sur1");
		name1=intent.getStringExtra("nam1");
		patronymic1=intent.getStringExtra("pat1");
		birthDay1=intent.getIntExtra("day1", 0);
		birthMonth1=intent.getIntExtra("mon1", 0);
		birthYear1=intent.getIntExtra("yea1", 0);

	
		// ��������� ����������� � ��
	    db = new DB(this);
	    db.open();
	    // �������� ������
	    cursor = db.getAllData();
	    // ��������� ������� �������������
	    String[] from = new String[] { DB.COLUMN_SUR, DB.COLUMN_NAM };
	    int[] to = new int[] { R.id.tvText, R.id.tvText1 };
	    // ������� ������� � ����������� ������
	    scAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to, 0);
	    lvData = (ListView) findViewById(R.id.lvData);
	    lvData.setAdapter(scAdapter); 
	    lvData.setOnItemClickListener(this);
	    // ������� ������ ��� ������ ������
	    getLoaderManager().initLoader(0, null, this);
		
		next= (Button) findViewById(R.id.bCalc3); next.setOnClickListener(this);

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
	    // ��������� ����������� ��� ������
	    db.close();
	  }


	@Override
	public void onClick(View v) 
	{Intent intent=null;
		switch (v.getId())
		{
		case R.id.firstPartner:{intent = new Intent(this,FirstPartner.class);transData(intent); startActivity(intent);finish(); break;}
		case R.id.bCalc3: {Calculations calc = new Calculations(getApplicationContext()); //�������� Context � ����������� ������ ��� ����������� ������ ��������
		if (surname1==null||name1==null||birthDay1==0) {Toast.makeText(getApplicationContext(), 
                "��� ������� �������� ������ � �������!", Toast.LENGTH_SHORT).show(); break;}
		else calc.calculation(surname1, name1, patronymic1, birthDay1, birthMonth1, birthYear1);
        finish(); break;}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.journal_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{Intent intent=null;
		switch (item.getItemId()) 
		{
		case R.id.first_partner: {intent = new Intent(this,FirstPartner.class); transData(intent); startActivity(intent);finish();}	return true;
		case R.id.main_m: {intent = new Intent(this,MainMenu.class); transData(intent); startActivity(intent);finish();}	return true;
		case R.id.exit: finish(); return true;
		
		default: return super.onOptionsItemSelected(item);
		}
	}

	private void transData(Intent intent) { //�������� ������ ����� ��������

		intent.putExtra("sur1", surname1);
		intent.putExtra("nam1", name1);
		intent.putExtra("pat1", patronymic1);
		intent.putExtra("day1", birthDay1);
		intent.putExtra("mon1", birthMonth1);
		intent.putExtra("yea1", birthYear1);
	}
	@Override //PopUpMenu
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {showPopupMenu(v, position, id);}//�������� PopUpMenu

	private void showPopupMenu(View v, final int position, final long id) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        //popupMenu.inflate(R.menu.popup_menu); // ��� Android 4.0
        // ��� ������ Android 3.0 ����� ������������ ������� �������
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.first_partner_choice:
                        		cursor=db.getDataRow(id);
                        		cursor.moveToFirst();
												surname1=cursor.getString(cursor.getColumnIndex(DB.COLUMN_SUR));
												name1=cursor.getString(cursor.getColumnIndex(DB.COLUMN_NAM));
												patronymic1=cursor.getString(cursor.getColumnIndex(DB.COLUMN_PAT));
												birthDay1=cursor.getInt(cursor.getColumnIndex(DB.COLUMN_BD));
												birthMonth1=cursor.getInt(cursor.getColumnIndex(DB.COLUMN_BM));
												birthYear1=cursor.getInt(cursor.getColumnIndex(DB.COLUMN_BY));
                        		cursor.close();
                        		bd1= (birthDay1<10) ? "0"+birthDay1:""+birthDay1; 
                        		bm1= (birthMonth1<10) ? "0"+birthMonth1:""+birthMonth1;
                        		firstPartner.setText(surname1+" "+name1+" "+patronymic1+" � "+bd1+"."+bm1+"."+birthYear1);
                       		return true;

                            case R.id.edit_rec:
                        		cursor=db.getDataRow(id);
                        		cursor.moveToFirst();
												
                        						surnameEd=cursor.getString(cursor.getColumnIndex(DB.COLUMN_SUR));
												nameEd=cursor.getString(cursor.getColumnIndex(DB.COLUMN_NAM));
												patronymicEd=cursor.getString(cursor.getColumnIndex(DB.COLUMN_PAT));
												birthDayEd=cursor.getInt(cursor.getColumnIndex(DB.COLUMN_BD));
												birthMonthEd=cursor.getInt(cursor.getColumnIndex(DB.COLUMN_BM));
												birthYearEd=cursor.getInt(cursor.getColumnIndex(DB.COLUMN_BY));
                        		cursor.close();
                        		editRec(id);
                        		return true;

                            case R.id.del_rec:
                            {
                    			//��������� AlertDialog
                    	        context = Journal.this;
                    		    String title = "������� ������?";
                    		    String button1String = "��";
                    		    String button2String = "���";	    
                    		    ad = new AlertDialog.Builder(context);
                    	        ad.setTitle(title);  // ���������
                    	        ad.setIcon(R.drawable.ic_launcher);//������
                    	        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                    	            public void onClick(DialogInterface dialog, int arg1) 
                    	            {
                     	            Toast.makeText(context, "������ �������",Toast.LENGTH_SHORT).show();
                                    db.delRec(id);
                                 // �������� ����� ������ � �������
                                    getLoaderManager().getLoader(0).forceLoad();
                    	            }
                    	        	
                    	        });
                    	        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
                    	            public void onClick(DialogInterface dialog, int arg1) {
                    	            }
                    	        });
                    	        ad.setCancelable(true);//����� �� ������ Back
                    	        ad.setOnCancelListener(new OnCancelListener() {
                    	            public void onCancel(DialogInterface dialog) {
                    	                }
                    	        });	
                    	        ad.show();
                    		}
                               return true;
                            default:
                                return false;
                        }
                    }
                });

/*        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {// API-14

            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(getApplicationContext(), "onDismiss",
                        Toast.LENGTH_SHORT).show();
            }
        });*/
        popupMenu.show();
    }
	
	void editRec(long id) {
		Intent intent = new Intent(this,EditPartner.class);
		intent.putExtra("recId", id);
		intent.putExtra("surEd", surnameEd);
		intent.putExtra("namEd", nameEd);
		intent.putExtra("patEd", patronymicEd);
		intent.putExtra("dayEd", birthDayEd);
		intent.putExtra("monEd", birthMonthEd);
		intent.putExtra("yeaEd", birthYearEd);
		startActivity(intent);finish();
	}

	
	//������ ������ Back
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		openQuitDialog();
	}

	private void openQuitDialog() {
		
		//��������� AlertDialog
        context = Journal.this;
	    String title = "�����?";
	    String button1String = "�����";
	    String button2String = "������� ����";	    
	    String button3String = "������";
	    ad = new AlertDialog.Builder(context);
        ad.setTitle(title);  // ���������
        ad.setIcon(R.drawable.ic_launcher);//������
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

	
	  @Override
	  public Loader<Cursor> onCreateLoader(int id, Bundle bndl) {
	    return new MyCursorLoader(this, db);
	  }
	  @Override
	  public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
	    scAdapter.swapCursor(cursor);
	  }
	  @Override
	  public void onLoaderReset(Loader<Cursor> loader) {
	  }
	  static class MyCursorLoader extends CursorLoader {
	    DB db;
	    public MyCursorLoader(Context context, DB db) {
	      super(context);
	      this.db = db;
	    }
	    @Override
	    public Cursor loadInBackground() {
	      Cursor cursor = db.getAllData();
	      try {
	        TimeUnit.SECONDS.sleep(3);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	      return cursor;
	    }
	    
	  }
}
