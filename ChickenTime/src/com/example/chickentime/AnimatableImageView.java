package com.example.chickentime;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

public class AnimatableImageView extends ImageView {

	public double duration;
	public boolean loop = false;
	public int fps = 24;
	Collection<ImageView> frames; 
	
	
	void renderFrame(){
		
	}
	void setFileSeqence(String sequance){
		
	}
	public AnimatableImageView(Context context) {
		super(context);
		frames = new ArrayList<ImageView>();
		
	}

}
