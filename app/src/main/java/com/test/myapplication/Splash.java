package com.test.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import timber.log.Timber;

public class Splash extends AppCompatActivity {

    private static final double MIN_OPENGL_VERSION = 3.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        setContentView ( R.layout.activity_splash );
        findViewById ( R.id.textView2 ).setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                loadMain();
                finish ();
            }
        } );

        if (!checkIsSupportedDeviceOrFinish(this)){
            ((TextView)findViewById ( R.id.txtNote )).setText ( getString( R.string.device) );
            findViewById ( R.id.textView2 ).setVisibility ( View.INVISIBLE );
        }
    }

    private void loadMain() {
        startActivity ( new Intent (this, MainActivity.class) );
    }


    @SuppressLint("ObsoleteSdkInt")
    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
            Toast.makeText ( activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG ).show ();
            return false;
        }
        @SuppressLint("ServiceCast") String openGlVersionString =
                ((ActivityManager) activity.getSystemService ( Context.ACCOUNT_SERVICE ))
                .getDeviceConfigurationInfo ().getGlEsVersion ();
        if (Double.parseDouble ( openGlVersionString ) < MIN_OPENGL_VERSION){
            Timber.i("Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText ( activity,"Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG ).show ();
            return false;
        }
        return true;
    }
}