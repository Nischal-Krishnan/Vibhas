package com.test.myapplication.nodes;

import android.content.Context;
import android.net.Uri;

import com.google.ar.core.AugmentedImage;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.test.myapplication.helpers.BaseAnchor;

import java.util.concurrent.CompletableFuture;

import timber.log.Timber;

public class AugmentedDynamic3DNode extends BaseAnchor {
    private CompletableFuture<ModelRenderable> dynamicModel;

    public AugmentedDynamic3DNode(Context context, String modelName){
        dynamicModel = ModelRenderable.builder ()
                .setSource ( context, Uri.parse ( modelName+".sfb" ) )
                .build ();
    }

    @Override
    public void setImage(AugmentedImage image){
        if(!dynamicModel.isDone ()){
            CompletableFuture.allOf ( dynamicModel )
                    .thenAccept ( (Void aVoid) -> setImage ( image ) )
                    .exceptionally ( (Throwable throwable) ->{
                        Timber.i(throwable);
                        return null;
                    } );
            return;
        }

        setAnchor ( image.createAnchor ( image.getCenterPose () ) );
        addNode();
    }


    private void addNode(){

        Node dynamicChildNode = new Node ();
        dynamicChildNode.setRenderable ( dynamicModel.getNow ( null ) );
        dynamicChildNode.setParent ( this );
    }
}
