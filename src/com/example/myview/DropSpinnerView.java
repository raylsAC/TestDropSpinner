package com.example.myview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.myactivity.R;

public class DropSpinnerView extends LinearLayout{

	private View mDropSpinnerView;
	private String mTitileText;
	private float mTitileTextSize;
	private int mTitileTextColor;
	private boolean ArrowClickable;
	private boolean isArrowselectable = true;
	
	private TextView mTitle;
	private ImageView mTrianglearrow;
	
	public CallBack mCallBack;
	
	public DropSpinnerView(Context context) {
		super(context);
	}

	public DropSpinnerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}
	
	private void init(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DropSpinner);
		mTitileText = a.getString(R.styleable.DropSpinner_titleText);
		mTitileTextSize = a.getDimension(R.styleable.DropSpinner_titleTextSize, 15);
		mTitileTextColor = a.getColor(R.styleable.DropSpinner_titleTextColor, context.getResources().getColor(R.color.normal_tv_color_66));
		ArrowClickable = a.getBoolean(R.styleable.DropSpinner_onClickArrow, false);
		a.recycle();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		mDropSpinnerView = LayoutInflater.from(getContext()).inflate(R.layout.dropspinner_layout, this);
		
		mTitle = (TextView) mDropSpinnerView.findViewById(R.id.tv_title);
		mTrianglearrow = (ImageView) mDropSpinnerView.findViewById(R.id.tv_triangle);
		
		setTitletext(mTitileText);
		setTitleSize(mTitileTextSize);
		setTitleColor(mTitileTextColor);
		
		mTrianglearrow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startArrowAnimation();
				if (mCallBack!=null) {
					mCallBack.onArrowClick();
				}
			}
		});
		setArrowClickable(ArrowClickable);
	}
	
	
	protected void startArrowAnimation() {
		ObjectAnimator animator = null;
		if (isArrowselectable) {
			animator = ObjectAnimator.ofFloat(mTrianglearrow, "rotation", 0, 180);
			isArrowselectable = false;
		}else {
			animator = ObjectAnimator.ofFloat(mTrianglearrow, "rotation", 180, 0);
			isArrowselectable = true;
		}
		animator.setDuration(500);
		animator.start();
	}


	public interface CallBack{
		public void onArrowClick();
	}
	
	public void setArrowClickable(boolean clickable) {
		mTrianglearrow.setClickable(clickable);
	}
	
	public void setOnArrowClickListener(CallBack callback) {
		this.mCallBack = callback;
	}

	public void setTitletext(CharSequence titletext) {
		if (!TextUtils.isEmpty(titletext)) {
			mTitle.setText(titletext);
		}
	}
	
	public void setTitleSize(float titletextsize) {
		mTitle.setTextSize(titletextsize);
	}
	
	public void setTitleColor(int titletextcolor) {
		mTitle.setTextColor(titletextcolor);
	}
	
}
