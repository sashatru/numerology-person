package com.trubnikov.numerology_person;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;


public class Calculations {
	
	
	Map <Character,Integer> mapSoul = new HashMap <Character,Integer>();
	Map <Character,Integer> mapDest = new HashMap <Character,Integer>();
	Map <Character,Integer> mapPers = new HashMap <Character,Integer>();


	String fullName1;

	
	int numSoul1;
	int numDest1;

	int cl1=1;
	int cl2=2;
	int cl3=3;
	int cl4=4;
	int cl5=5;
	int cl6=6;
	int cl7=7;
	int cl8=8;
	int cl9=9;

	int numPers1;
	int numLifePath1;
	int numMatur1;

	int period;
	int perBegin, perEnd;
	int numPeak1;
	int numProblem1;

	int periodmc;
	int perBeginmc, perEndmc;

	int numMainCycle1;
	int numPersYear1;
	int numPersMonth;
	int numPersDay;
	int concord1;
	int compatRating;
	private int birthDay1;
	private int birthMonth1;
	private int birthYear1;
	String surname1, name1, patronymic1, sur, nam, pat;

	// ����������� ��� ��������� Contexta
	Context context;
	public Calculations(Context context){
		this.context=context;
	}	

	void next(){
	Intent intent = new Intent(context,SummaryTable.class);

	intent.putExtra("s1", numSoul1);
	intent.putExtra("d1", numDest1);
	intent.putExtra("cl1", cl1);
	intent.putExtra("cl2", cl2);
	intent.putExtra("cl3", cl3);
	intent.putExtra("cl4", cl4);
	intent.putExtra("cl5", cl5);
	intent.putExtra("cl6", cl6);
	intent.putExtra("cl7", cl7);
	intent.putExtra("cl8", cl8);
	intent.putExtra("cl9", cl9);
	intent.putExtra("p1", numPers1);
	intent.putExtra("lp1", numLifePath1);
	intent.putExtra("m1", numMatur1);
	intent.putExtra("period", period);
	intent.putExtra("perBegin", perBegin);
	intent.putExtra("perEnd", perEnd);
	intent.putExtra("peak1", numPeak1);
	intent.putExtra("prob1", numProblem1);
	intent.putExtra("periodmc", periodmc);
	intent.putExtra("perBeginmc", perBeginmc);
	intent.putExtra("perEndmc", perEndmc);
	intent.putExtra("mc1", numMainCycle1);
	intent.putExtra("con1", concord1);
	intent.putExtra("persyear1", numPersYear1);
	intent.putExtra("persmonth", numPersMonth);
	intent.putExtra("persday", numPersDay);

	intent.putExtra("sur1", sur);
	intent.putExtra("nam1", nam);
	intent.putExtra("pat1", pat);
	intent.putExtra("day1", birthDay1);
	intent.putExtra("mon1", birthMonth1);
	intent.putExtra("yea1", birthYear1);

	
	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// ���� ��� ������� ���������� �� �� Activity class
	context.startActivity(intent);
	}
	
	public void calculation(String sur1, String nam1, String pat1, int dat1, int mon1, int yea1) {

		sur=sur1;
		nam=nam1;
		pat=pat1;
		surname1=sur1.toUpperCase();
		name1=nam1.toUpperCase();
		patronymic1=pat1.toUpperCase();
		birthDay1=dat1;
		birthMonth1=mon1;
		birthYear1=yea1;



		fullName1=surname1+name1+patronymic1;

		numSoul1=oneDigit(soulNumber(surname1)+soulNumber(name1)+soulNumber(patronymic1));

		numDest1=oneDigit(destinyNumber(surname1)+destinyNumber(name1)+destinyNumber(patronymic1));

		carmaDebt(fullName1);
		
		numPers1=oneDigit1(personalityNumber(surname1)+personalityNumber(name1)+personalityNumber(patronymic1));

		numLifePath1=lifepathNumber();

		numMatur1=maturityNumber(numDest1+numLifePath1);

		numPeak1=peakNumber(oneDigit3(numLifePath1), dat1, mon1, yea1);

		numProblem1=problemNumber(oneDigit3(numLifePath1), dat1, mon1, yea1);

		numMainCycle1=mainCycleNumber(oneDigit3(numLifePath1), dat1, mon1, yea1);

		concord1=concord(dat1);

		numPersYear1=persYearNumber(dat1, mon1);
		
		numPersMonth=persMonthNumber();

		numPersDay=persDayNumber();

		
		next();
	}
	

	private Integer soulNumber(String name) {//���������� ����� ����
		mapSoul.put('�', 1);
		mapSoul.put('�', 1);
		mapSoul.put('�', 5);
		mapSoul.put('�', 6);
		mapSoul.put('�', 6);
		mapSoul.put('�', 6);
		mapSoul.put('�', 8);
		mapSoul.put('�', 9);
		mapSoul.put('�', 9);
		mapSoul.put('�', 9);
	int numSoul=0;
		char[] nam = name.toCharArray();
		for (Character n : nam)
		{try {numSoul=numSoul+mapSoul.get(n);} catch (Exception e) {}}
	return numSoul=oneDigit3(numSoul);
}
	
	private Integer destinyNumber(String name) {//���������� ����� ������
		mapDest.put('�', 1);
		mapDest.put('�', 1);
		mapDest.put('�', 1);
		mapDest.put('�', 2);
		mapDest.put('�', 2);
		mapDest.put('�', 2);
		mapDest.put('�', 3);
		mapDest.put('�', 3);
		mapDest.put('�', 3);
		mapDest.put('�', 4);
		mapDest.put('�', 4);
		mapDest.put('�', 4);
		mapDest.put('�', 5);
		mapDest.put('�', 5);
		mapDest.put('�', 5);
		mapDest.put('�', 6);
		mapDest.put('�', 6);
		mapDest.put('�', 6);
		mapDest.put('�', 6);
		mapDest.put('�', 6);
		mapDest.put('�', 7);
		mapDest.put('�', 7);
		mapDest.put('�', 7);
		mapDest.put('�', 8);
		mapDest.put('�', 8);
		mapDest.put('�', 8);
		mapDest.put('�', 9);
		mapDest.put('�', 9);
		mapDest.put('�', 9);
		mapDest.put('�', 9);
		mapDest.put('�', 9);
	int numDest=0;
		char[] nam = name.toCharArray();
		for (Character n : nam)
		{try {numDest=numDest+mapDest.get(n);} catch (Exception e) {}}
	return numDest=oneDigit3(numDest);
}

	private void carmaDebt(String name) {//���������� ����� ������������ �����
		mapDest.put('�', 1);
		mapDest.put('�', 1);
		mapDest.put('�', 1);
		mapDest.put('�', 2);
		mapDest.put('�', 2);
		mapDest.put('�', 2);
		mapDest.put('�', 3);
		mapDest.put('�', 3);
		mapDest.put('�', 3);
		mapDest.put('�', 4);
		mapDest.put('�', 4);
		mapDest.put('�', 4);
		mapDest.put('�', 5);
		mapDest.put('�', 5);
		mapDest.put('�', 5);
		mapDest.put('�', 6);
		mapDest.put('�', 6);
		mapDest.put('�', 6);
		mapDest.put('�', 6);
		mapDest.put('�', 6);
		mapDest.put('�', 7);
		mapDest.put('�', 7);
		mapDest.put('�', 7);
		mapDest.put('�', 8);
		mapDest.put('�', 8);
		mapDest.put('�', 8);
		mapDest.put('�', 9);
		mapDest.put('�', 9);
		mapDest.put('�', 9);
		mapDest.put('�', 9);
		mapDest.put('�', 9);
		int numDest=0;
		char[] nam = name.toCharArray();
		for (Character n : nam)
		{try {numDest=mapDest.get(n);
		switch (numDest) {
		case 1: cl1=0; break;
		case 2: cl2=0; break;
		case 3: cl3=0; break;
		case 4: cl4=0; break;
		case 5: cl5=0; break;
		case 6: cl6=0; break;
		case 7: cl7=0; break;
		case 8: cl8=0; break;
		case 9: cl9=0; break;}
		} catch (Exception e) {}}
	}
	
	private Integer personalityNumber(String name) {//���������� ����� ��������
		mapPers.put('�', 1);
		mapPers.put('�', 2);
		mapPers.put('�', 2);
		mapPers.put('�', 2);
		mapPers.put('�', 3);
		mapPers.put('�', 3);
		mapPers.put('�', 3);
		mapPers.put('�', 4);
		mapPers.put('�', 4);
		mapPers.put('�', 4);
		mapPers.put('�', 5);
		mapPers.put('�', 5);
		mapPers.put('�', 6);
		mapPers.put('�', 6);
		mapPers.put('�', 7);
		mapPers.put('�', 7);
		mapPers.put('�', 7);
		mapPers.put('�', 8);
		mapPers.put('�', 8);
		mapPers.put('�', 9);
		mapPers.put('�', 9);
	int numPers=0;
		char[] nam = name.toCharArray();
		for (Character n : nam)
		{try {numPers=numPers+mapPers.get(n);} catch (Exception e) {}}
	return numPers=oneDigit3(numPers);
}
	
	private int lifepathNumber() {//���������� ����� ���������� ����
		return oneDigit2(oneDigit3(birthDay1)+oneDigit3(birthMonth1)+oneDigit3(birthYear1));
	}
	
	private int maturityNumber(int numMaturity) {//���������� ����� ��������
		return numMaturity=oneDigit4(numMaturity);
	}
	
	private int peakNumber( int numLP, int dat, int mon, int yea){//���������� ����� ����
		//��������� ������� �� �������
		int age; 
		age = ageToday(dat, mon, yea);
		//������ ������� ������ ���� � ���������� ���
		int numPeak = 0;
		if (age<=(36-numLP)) {numPeak=oneDigit3(dat+mon); period=1; perBegin=0; perEnd=(36-numLP);}
		else if ((36-numLP)<age&&age<=(45-numLP)){numPeak=oneDigit3(dat+yea); period=2; perBegin=(36-numLP); perEnd=(45-numLP);}
		else if ((45-numLP)<age&&age<=(54-numLP)){numPeak=oneDigit3((2*dat+mon+yea)); period=3; perBegin=(45-numLP); perEnd=(54-numLP);}
		else if ((54-numLP)<age){numPeak=oneDigit3(mon+yea); period=4; perBegin=(54-numLP); perEnd=0;}
		return numPeak;
	}

	private int problemNumber( int numLP, int dat, int mon, int yea){//���������� ����� ��������
		//��������� ������� �� �������
		int age; 
		age = ageToday(dat, mon, yea);
		//������ ������� ������ ���� � ���������� ���
		int numProblem = 0;
		if (age<=(36-numLP)) {numProblem=Math.abs(oneDigit3(dat)-oneDigit3(mon));}
		else if ((36-numLP)<age&&age<=(45-numLP)){numProblem=Math.abs(oneDigit3(dat)-oneDigit3(yea));}
		else if ((45-numLP)<age&&age<=(54-numLP)){numProblem=Math.abs(Math.abs(oneDigit3(dat)-oneDigit3(yea))-Math.abs(oneDigit3(dat)-oneDigit3(mon)));}
		else if ((54-numLP)<age){numProblem=Math.abs(oneDigit3(mon)-oneDigit3(yea));}
		return numProblem;
	}

	private int mainCycleNumber( int numLP, int dat, int mon, int yea){//���������� ����� ��������� �����
		//��������� ������� �� �������
		int age; 
		age = ageToday(dat, mon, yea);
		int numMainCycle=0;
		switch (numLP) {
		case 1: 		
		if (age<=26) {numMainCycle=oneDigit3(mon); periodmc=1; perBeginmc=0; perEndmc=26;}
		else if (26<age&&age<=53){numMainCycle=oneDigit5(dat); periodmc=2; perBeginmc=27; perEndmc=53;}
		else if (53<age){numMainCycle=oneDigit5(yea); periodmc=3; perBeginmc=54; perEndmc=0;} break;
		case 2: 		
		if (age<=25) {numMainCycle=oneDigit3(mon); periodmc=1; perBeginmc=0; perEndmc=25;}
		else if (25<age&&age<=52){numMainCycle=oneDigit5(dat); periodmc=2; perBeginmc=26; perEndmc=52;}
		else if (52<age){numMainCycle=oneDigit5(yea); periodmc=3; perBeginmc=53; perEndmc=0;} break;
		case 3: 		
		if (age<=33) {numMainCycle=oneDigit3(mon); periodmc=1; perBeginmc=0; perEndmc=33;}
		else if (33<age&&age<=60){numMainCycle=oneDigit5(dat); periodmc=2; perBeginmc=34; perEndmc=60;}
		else if (60<age){numMainCycle=oneDigit5(yea); periodmc=3; perBeginmc=61; perEndmc=0;} break;
		case 4: 		
		if (age<=32) {numMainCycle=oneDigit3(mon); periodmc=1; perBeginmc=0; perEndmc=32;}
		else if (32<age&&age<=59){numMainCycle=oneDigit5(dat); periodmc=2; perBeginmc=33; perEndmc=59;}
		else if (59<age){numMainCycle=oneDigit5(yea); periodmc=3; perBeginmc=60; perEndmc=0;} break;
		case 5: 		
		if (age<=31) {numMainCycle=oneDigit3(mon); periodmc=1; perBeginmc=0; perEndmc=31;}
		else if (31<age&&age<=58){numMainCycle=oneDigit5(dat); periodmc=2; perBeginmc=32; perEndmc=58;}
		else if (58<age){numMainCycle=oneDigit5(yea); periodmc=3; perBeginmc=59; perEndmc=0;} break;
		case 6: 		
		if (age<=30) {numMainCycle=oneDigit3(mon); periodmc=1; perBeginmc=0; perEndmc=30;}
		else if (30<age&&age<=57){numMainCycle=oneDigit5(dat); periodmc=2; perBeginmc=31; perEndmc=57;}
		else if (57<age){numMainCycle=oneDigit5(yea); periodmc=3; perBeginmc=58; perEndmc=0;} break;
		case 7: 		
		if (age<=29) {numMainCycle=oneDigit3(mon); periodmc=1; perBeginmc=0; perEndmc=29;}
		else if (29<age&&age<=56){numMainCycle=oneDigit5(dat); periodmc=2; perBeginmc=30; perEndmc=56;}
		else if (56<age){numMainCycle=oneDigit5(yea); periodmc=3; perBeginmc=57; perEndmc=0;} break;
		case 8: 		
		if (age<=28) {numMainCycle=oneDigit3(mon); periodmc=1; perBeginmc=0; perEndmc=28;}
		else if (28<age&&age<=55){numMainCycle=oneDigit5(dat); periodmc=2; perBeginmc=29; perEndmc=55;}
		else if (55<age){numMainCycle=oneDigit5(yea); periodmc=3; perBeginmc=56; perEndmc=0;} break;
		case 9: 		
		if (age<=27) {numMainCycle=oneDigit3(mon); periodmc=1; perBeginmc=0; perEndmc=27;}
		else if (27<age&&age<=54){numMainCycle=oneDigit5(dat); periodmc=2; perBeginmc=28; perEndmc=54;}
		else if (54<age){numMainCycle=oneDigit5(yea); periodmc=3; perBeginmc=55; perEndmc=0;} break;
		default:break;
		}
		return numMainCycle;
	}

	private int concord(int dat) {
		dat=oneDigit3(dat);
		int concord = 0;
		if (dat==3||dat==6||dat==9) concord=1; //����������, ���������
		if (dat==1||dat==5||dat==7) concord=2; //�����, ���������
		if (dat==2||dat==4||dat==8) concord=3; //������, ������
		return concord;
	}

	private int persYearNumber( int dat, int mon){//���������� ������� ����
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int numPersYear = oneDigit3(dat+mon+year);
		return numPersYear;
	}

	private int persMonthNumber( ){//���������� ������� ������
		final Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH)+1;
		int numPersMonth = oneDigit3(numPersYear1+month);
		return numPersMonth;
	}

	private int persDayNumber( ){//���������� ������� ������
		final Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);
		int numPersDay = oneDigit3(numPersMonth+day);
		return numPersDay;
	}

	
	private int ageToday (int dat, int mon, int yea){//��������� ������� �� �������
		int age; 
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		age = year-yea;
		if (month<mon) {age--;}
		else if (month==mon&&day<dat)age--;
		return age;
	}



	private int oneDigit(int numOneDigit) {//�������� ����� ���� � ������������ �����, ���� ��� ������ �� �����������
		int b, a=0;
		b=numOneDigit;
		while (numOneDigit>9){
			if(numOneDigit==99) break;
			if(numOneDigit==88) break;
			if(numOneDigit==77) break;
			if(numOneDigit==66) break;
			if(numOneDigit==55) break;
			if(numOneDigit==44) break;
			if(numOneDigit==33) break;
			if(numOneDigit==22) break;
			if(numOneDigit==19) break;
			if(numOneDigit==16) break;
			if(numOneDigit==14) break;
			if(numOneDigit==13) break;
			if(numOneDigit==11) break;
			if(numOneDigit==10) break;
			a=a+b%10; 
			b/=10;
			if(b<10) {numOneDigit=b+a; b=numOneDigit; a=0;}
			}
		return numOneDigit;}

	private int oneDigit1(int numOneDigit) {//�������� ����� ���� � ������������ �����, ���� ��� ������ �� �����������
		int b, a=0;
		b=numOneDigit;
		while (numOneDigit>9){
			if(numOneDigit==22) break;
			if(numOneDigit==11) break;
			a=a+b%10; 
			b/=10;
			if(b<10) {numOneDigit=b+a; b=numOneDigit; a=0;}
			}
		return numOneDigit;}

	private int oneDigit2(int numOneDigit) {//�������� ����� ���� � ������������ �����, ���� ��� ������ �� �����������
		int b, a=0;
		b=numOneDigit;
		while (numOneDigit>9){
			if(numOneDigit==44) break;
			if(numOneDigit==33) break;
			if(numOneDigit==22) break;
			if(numOneDigit==19) break;
			if(numOneDigit==16) break;
			if(numOneDigit==14) break;
			if(numOneDigit==13) break;
			if(numOneDigit==11) break;
			if(numOneDigit==10) break;
			a=a+b%10; 
			b/=10;
			if(b<10) {numOneDigit=b+a; b=numOneDigit; a=0;}
			}
		return numOneDigit;}

	private int oneDigit3(int numOneDigit) {//�������� ����� ���� � ������������ �����
		int b, a=0;
		b=numOneDigit;
		while (numOneDigit>9){
			a=a+b%10; 
			b/=10;
			if(b<10) {numOneDigit=b+a; b=numOneDigit; a=0;}
			}
		return numOneDigit;}

	private int oneDigit4(int numOneDigit) {//�������� ����� ���� � ������������ �����, ���� ��� ������ �� �����������
		int b, a=0;
		b=numOneDigit;
		while (numOneDigit>9){
			if(numOneDigit==99) break;
			if(numOneDigit==88) break;
			if(numOneDigit==77) break;
			if(numOneDigit==66) break;
			if(numOneDigit==55) break;
			if(numOneDigit==44) break;
			if(numOneDigit==33) break;
			if(numOneDigit==22) break;
			if(numOneDigit==11) break;
			a=a+b%10; 
			b/=10;
			if(b<10) {numOneDigit=b+a; b=numOneDigit; a=0;}
			}
		return numOneDigit;}

	private int oneDigit5(int numOneDigit) {//�������� ����� ���� � ������������ �����, ���� ��� ������ �� �����������
		int b, a=0;
		b=numOneDigit;
		while (numOneDigit>9){
			if(numOneDigit==22) break;
			if(numOneDigit==19) break;
			if(numOneDigit==16) break;
			if(numOneDigit==14) break;
			if(numOneDigit==13) break;
			if(numOneDigit==11) break;
			a=a+b%10; 
			b/=10;
			if(b<10) {numOneDigit=b+a; b=numOneDigit; a=0;}
			}
		return numOneDigit;}

}
