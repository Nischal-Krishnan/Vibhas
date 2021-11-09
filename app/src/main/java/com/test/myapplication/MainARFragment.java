package com.test.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.test.myapplication.helpers.BaseARFragment;

public class MainARFragment extends BaseARFragment<MainActivity> {

    @Override
    protected Config getSessionConfiguration(Session session){
        Config config = super.getSessionConfiguration ( session );
        config.setFocusMode ( Config.FocusMode.AUTO );

        getMain ().loadARResources(config, session);
        return config;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = super.onCreateView ( inflater, container, savedInstanceState );

        getPlaneDiscoveryController ().hide ();
        getPlaneDiscoveryController ().setInstructionView ( null );
        getArSceneView ().getPlaneRenderer ().setEnabled ( false );

        return rootView;
    }

}
