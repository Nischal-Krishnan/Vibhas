package com.test.myapplication.helpers;

import com.google.ar.sceneform.ux.ArFragment;

public class BaseARFragment<T> extends ArFragment {
    public T getMain(){
        return (T)getActivity ();
    }
}
