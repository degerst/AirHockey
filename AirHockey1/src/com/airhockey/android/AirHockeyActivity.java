package com.airhockey.android;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class AirHockeyActivity extends Activity {

	private GLSurfaceView m_glSurfaceView;

	private boolean m_rendererSet = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_glSurfaceView = new GLSurfaceView(this);
        
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
        
        if (supportsEs2) {
        	m_glSurfaceView.setEGLContextClientVersion(2);
        	m_glSurfaceView.setRenderer(new AirHockeyRenderer());
        	m_rendererSet = true;
        } else {
        	Toast.makeText(this, "This device does not support OpenGL ER 2.0.", Toast.LENGTH_LONG).show();
        	return;
        }
        setContentView(m_glSurfaceView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first_open_glproject, menu);
        return true;
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	if (m_rendererSet) {
    		m_glSurfaceView.onPause();
    	}
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	if (m_rendererSet) {
    		m_glSurfaceView.onResume();
    	}
    }
}
