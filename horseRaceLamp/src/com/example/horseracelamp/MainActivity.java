package com.example.horseracelamp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
	
	private HorseRaceLamp horse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horse = (HorseRaceLamp) findViewById(R.id.horseRaceLamp1);
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0;i<10;i++){
					horse.setLightNum(i*0.1f);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
    }

}
