package com.example.testanimateimage;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Button alphaButton = null;
	private Button moveButton = null;
	private Button rotateButton = null;
	private Button sampleButton = null;
	private ImageView animationImageView = null;
	private View animationView = null;
	
//	private RelativeLayout rootLayout = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		alphaButton = (Button)findViewById(R.id.alpha_button_id);
		moveButton = (Button)findViewById(R.id.move_button_id);
		rotateButton = (Button)findViewById(R.id.rotation_button_id);
		sampleButton = (Button)findViewById(R.id.multianim_button_id);
		animationImageView = (ImageView)findViewById(R.id.animation_imageview_id);
		
////		animationView = (View)findViewById(R.id.animation_view_id);
//		GIFView gifView = new GIFView(this);
//		gifView.setResouceId(R.drawable.move_nikkoman);
//		animationView = gifView;
//		rootLayout = (RelativeLayout)findViewById(R.id.root_layout_id);
//		rootLayout.addView(gifView);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onButtonClick(View view) {
		if(view == alphaButton) {
			animateAlpha(animationImageView);
		} else if(view == moveButton) {
			animateTranslationX(animationImageView);
		} else if(view == rotateButton) {
			animateRotation(animationImageView);
		} else if(view == sampleButton) {
			animatePropertyValuesHolderSample(animationImageView, 45, 500); // 45角度、500values移動
		}
	}
	
	/**
	 * 3秒間ターゲット実施
	 * @param imageView
	 */
	private void animateAlpha(ImageView imageView) {
		ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f); // alphaプロパティを0fから1fに変化
		objectAnimator.setDuration(3000); // 3秒間実行
		objectAnimator.start(); // アニメーションを開始
	}
	
	/**
	 * X方向にターゲットを3秒間200values移動
	 * @param imageView
	 */
	private void animateTranslationX(ImageView imageView) {
		ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 500f); // translationXプロパティを0fから200fに変化
		objectAnimator.setDuration(3000); // 3秒間実行
		objectAnimator.start(); // アニメーションを開始
	}
	
	/**
	 * 3秒間ターゲットをZ軸周りに360度回転
	 */
	private void animateRotation(ImageView imageView) {
		ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
		objectAnimator.setDuration(3000); // 3秒間実行
		objectAnimator.start(); // アニメーションを開始
	}
	
	/**
	 * 2秒かけて引数に与えた角度と距離の位置に回転させながらターゲットを移動させる
	 * 
	 * @param target
	 * @param degree
	 * @param distance 
	 */
	private void animatePropertyValuesHolderSample( ImageView imageView, float degree, float distance ) {

	    // 距離と角度から到達点となるX座標、Y座標を求めます
	    float toX = (float) ( distance * Math.cos( Math.toRadians( degree ) ) );
	    float toY = (float) ( distance * Math.sin( Math.toRadians( degree ) ) );

	    // translationXプロパティを0fからtoXに変化
	    PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat( "translationX", 0f, toX );
	    // translationYプロパティを0fからtoYに変化
	    PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat( "translationY", 0f, toY );
	    // rotationプロパティを0fから360fに変化
	    PropertyValuesHolder holderRotaion = PropertyValuesHolder.ofFloat( "rotation", 0f, 360f );

	    // targetに対してholderX, holderY, holderRotationを同時に実行
	    ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(imageView, holderX, holderY, holderRotaion );

	    // 2秒かけて実行さ
	    objectAnimator.setDuration( 2000 );

	    // アニメーションを開始
	    objectAnimator.start();
	}
}
