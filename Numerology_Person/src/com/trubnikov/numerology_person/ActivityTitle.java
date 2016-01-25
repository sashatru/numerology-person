package com.trubnikov.numerology_person;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ActivityTitle extends Activity {

	private ImageView imageView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);

		imageView1 = (ImageView)findViewById(R.id.titletext);
		Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.alpha_transparent2);
		imageView1.startAnimation(animation1);
		
		//���� �������� ��������� ��������� ��������
		Thread splash_time = new Thread()
		 {
		 public void run()
		 {
		 try
		 {
		 //����� �������� ����� ����������� ��������:
		 int SplashTimer = 0;
		 //��������� ���� ������ � 2 �������:
		 while(SplashTimer < 2000) { sleep(100);SplashTimer = SplashTimer +100;};
		 startActivity(new Intent(ActivityTitle.this,MainMenu.class));
		 }
		 catch (InterruptedException e) {
		 e.printStackTrace(); }
		 finally { //��������� activity:
		 finish();
		 }}
		 };
		 splash_time.start();
	}


}
