package com.gokhan.noengelleme;
 
import com.startapp.android.publish.StartAppAd;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.Sampler.Value;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Blockers  extends Activity implements RadioGroup.OnCheckedChangeListener, OnClickListener {
	NotificationManager notificationManager ;
	private SharedPreferences myPrefs; 
	private SharedPreferences.Editor editor; 
	
	
	String value;
	String msj="Yeni Mesaj"; 
	 private RadioButton allBlock;
	 private RadioButton unSaved;
	 private RadioButton fromList;
	 private RadioButton cancelAll;
	 private StartAppAd startAppAd = new StartAppAd(this);

	 private Button btnAddNumber;
	 private Button btnShowList;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		StartAppAd.showSlider(this);
		super.onCreate(savedInstanceState);

	    setContentView(R.layout.activity_main);
	    StartAppAd startAppAd = new StartAppAd(this);
		StartAppAd.init(this, "104746054", "204767802");

// ///////////////////////////////////
         myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
         editor=myPrefs.edit();     
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radGroup1);
        radiogroup.setOnCheckedChangeListener(this);
        // radio button setting
        allBlock=(RadioButton) findViewById(R.id.blockAll);
        unSaved=(RadioButton) findViewById(R.id.blockUnsaved);
        fromList=(RadioButton) findViewById(R.id.blockFromList);
        cancelAll=(RadioButton) findViewById(R.id.cancelBlocker);
        setDefaultButtonChecked();
        
        
        // add and show button controls
        btnAddNumber=(Button) findViewById(R.id.btnAdd);
        btnShowList=(Button) findViewById(R.id.btnShow);
        btnAddNumber.setOnClickListener(this);
        btnShowList.setOnClickListener(this);
	}
	
	
	@Override
	public void onResume() {
	    super.onResume();
	    startAppAd.onResume();
	}
	@Override
	public void onBackPressed() {
	    startAppAd.onBackPressed();
	    super.onBackPressed();
	}
	@Override
	public void onPause() {
	    super.onPause();
	    startAppAd.onPause();
	}
	public void btnOpenActivity (View view){
	    startAppAd.showAd();
	    startAppAd.loadAd();
	    Intent nextActivity = new Intent(this, ListActivity.class);
	    startActivity(nextActivity);
	}
	
	
	private  void setDefaultButtonChecked()
	{
		 value=getSharedPreferences();
		if(value.equals("all"))
		{
			allBlock.setChecked(true);
			
		}
		else if(value.equals("unsaved"))
		{
			 unSaved.setChecked(true);
		}
		else if(value.equals("list"))
		{
			 fromList.setChecked(true);
		}
		else if(value.equals("cancel"))
		{
			 cancelAll.setChecked(true);
		}
		else {
			cancelAll.setChecked(true);
		}
	}
	 public void onCheckedChanged(RadioGroup group, int checkedId) {
	        switch (checkedId) {
	        case R.id.blockAll:
	        	
	 			editor.putString("mode", "all");
	        	 editor.commit();
	        	 Toast.makeText(getApplicationContext(), "Tüm Rehber Engellendi",Toast.LENGTH_SHORT).show();
	        	 notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	        	  @SuppressWarnings("deprecation")
	        	  Notification notification = new Notification(R.drawable.ic_launcher,
	        	    msj, System.currentTimeMillis());

	        	   Intent notificationIntent = new Intent(this, Blockers.class);
	        	  PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
	        	    notificationIntent, 0);

	        	   notification.setLatestEventInfo(Blockers.this, "Uygulama Calisiyor.",
	        	    "Tüm rehber engelli", pendingIntent);
	        	   notification.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
	        	   notificationManager.notify(9999, notification);
	          break;

	        case R.id.blockUnsaved:
	        	editor.putString("mode", "unsaved");
	        	 editor.commit();
	          	 Toast.makeText(getApplicationContext(), "Bilinmeyen Numaralar Engellendi",Toast.LENGTH_SHORT).show();
	          	 notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	        	  @SuppressWarnings("deprecation")
	        	  Notification notification2 = new Notification(R.drawable.ic_launcher,
	        	    msj, System.currentTimeMillis());

	        	   Intent notificationIntent2 = new Intent(this, Blockers.class);
	        	  PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0,
	        	    notificationIntent2, 0);

	        	   notification2.setLatestEventInfo(Blockers.this, "Uygulama Calisiyor.",
	        	    "Bilinmeyen numaralar engelli", pendingIntent2);
	        	   notification2.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
	        	   notificationManager.notify(9999, notification2);
	          break;

	        case R.id.blockFromList:
	        	editor.putString("mode", "list"); 
	        	 editor.commit();
	          	 Toast.makeText(getApplicationContext(), "Listedeki Numaralar Engellendi",Toast.LENGTH_SHORT).show();
	          	notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	        	  @SuppressWarnings("deprecation")
	        	  Notification notification3 = new Notification(R.drawable.ic_launcher,
	        	    msj, System.currentTimeMillis());

	        	   Intent notificationIntent3 = new Intent(this, Blockers.class);
	        	  PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 0,
	        	    notificationIntent3, 0);

	        	   notification3.setLatestEventInfo(Blockers.this, "Uygulama Calisiyor.",
	        	    "Listedeki numaralar engelli", pendingIntent3);
	        	   notification3.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
	        	   notificationManager.notify(9999, notification3);

	          break;
	        case R.id.cancelBlocker:
	        	editor.putString("mode", "cancel"); 
	        	 editor.commit();
	          	 Toast.makeText(getApplicationContext(), "Engellemeler Kalktý",Toast.LENGTH_SHORT).show();
	          if(value.equals("all") || value.equals("unsaved")|| value.equals("list")){
	          	 notificationManager.cancel(9999);
	        }
	          	 break;
	       
	        }
	      }
	  @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.activity_main, menu);
	        return true;
	    }
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case R.id.menu_settings: 
	        	  
	        	  Intent i=new Intent(this, NumberList.class);
	        	  startActivity(i);
	            break;
	            case R.id.menu_show: 
	        	  
	        	  Intent ii=new Intent(this, ListActivity.class);
	        	  startActivity(ii);
	            break;
	        }
	        return true;
	    }
	   
		private String getSharedPreferences() {
			// TODO Auto-generated method stub
			 myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
			 String value=myPrefs.getString("mode", "not");
			 return value;
		}
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnAdd:
				  Intent i=new Intent(this, NumberList.class);
	        	  startActivity(i);
				break;
            case R.id.btnShow:
            	  Intent ii=new Intent(this, ListActivity.class);
	        	  startActivity(ii);
				break;
			default:
				break;
			}
		}
}
