package com.pm.commons.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class FrameAnimaView extends ImageView {

	public FrameAnimaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public FrameAnimaView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void startFrameAnimation() {
		AnimationDrawable anim = (AnimationDrawable) getBackground();
		anim.start();
	}

	public void stopFrameAnimation() {
		AnimationDrawable anim = (AnimationDrawable) getBackground();
		anim.stop();
	}

	public FrameAnimaView(Context context) {
		super(context);
	}

}
