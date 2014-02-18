package com.mvs.pelota_sensores;

import android.os.Bundle;
import android.app.Activity; 
import android.view.Window; 
import android.view.WindowManager;

public class MoverActivity extends Activity { 
	  
    // Nuestra  vista
    private MoverView mView; 
 
    @Override 
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
 
        // quitar el title_bar y hacer fullscreen la aplicacion
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // configuramos nuestra vista, le damos el foco y la pantalla 
        mView = new MoverView(getApplicationContext(), this); 
        mView.setFocusable(true); 
        setContentView(mView); 
    } 
 
    @Override 
    protected void onResume() { 
        super.onResume(); 
        mView.registerListener(); 
    } 
 
    @Override 
    public void onSaveInstanceState(Bundle icicle) { 
        super.onSaveInstanceState(icicle); 
        mView.unregisterListener(); 
    } 
} 
