package com.nwy.myapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 罗晓飞 on 2017/8/6.
 */

public class myView extends RelativeLayout {
    //屏幕的宽
    private int widthPixels;
    //屏幕的高
    private int heightPixels;
    //默认半径
    private int defultR = 300;
    private Context context;


    private ArrayList<String> data = new ArrayList();
    private ArrayList<ArrayList<String>> childData = new ArrayList();
    private ArrayList<ArrayList<Integer>> childDataindex = new ArrayList();
    private ArrayList<Integer> Dataindex = new ArrayList();
    private ArrayList<Integer> integers;
    private int mainMenuColor=0;
    private int itemMenuColor=0;

    public int getWidthPixels() {
        return widthPixels;
    }

    public void setWidthPixels(int widthPixels) {
        this.widthPixels = widthPixels;
    }

    public int getMainMenuColor() {
        return mainMenuColor;
    }

    public void setMainMenuColor(int mainMenuColor) {
        this.mainMenuColor = mainMenuColor;
    }

    public int getItemMenuColor() {
        return itemMenuColor;
    }

    public void setItemMenuColor(int itemMenuColor) {
        this.itemMenuColor = itemMenuColor;
    }

    public myView(Context context) {
        this(context, null);
    }

    public myView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public myView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setData(String s, ArrayList<String> child) {
        //将一组数据添加到集合
        int childsize = 0;
        for (int i = 0; i < childDataindex.size(); i++) {
            childsize = childsize + childDataindex.get(i).size();
        }
        Dataindex.add(data.size() + childsize);
        data.add(s);

        childData.add(child);
        //主按钮的长度
        int size = data.size();
        int childsize1 = 0;
        for (int i = 0; i < childDataindex.size(); i++) {
            childsize1 = childsize1 + childDataindex.get(i).size();
        }
        int allSize = size + childsize1;

        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < child.size(); i++) {
            integers.add(allSize + i);
        }
        childDataindex.add(integers);
    }

    private void init(final Context context) {
        this.context = context;
        DisplayMetrics dm = new DisplayMetrics();
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        widthPixels = dm.widthPixels;
        heightPixels = dm.heightPixels;
        defultR = widthPixels / 5;

        for (int i = 0; i < data.size(); i++) {
            final TextView textView = new TextView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(defultR, defultR);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(18);
            textView.setEnabled(false);
            textView.setVisibility(INVISIBLE);
            textView.setText(data.get(i));
            textView.setTextColor(Color.WHITE);
            //textView.setBackgroundColor(Color.RED);
            textView.setBackgroundResource(R.drawable.tv_shap);
            if (mainMenuColor!=0){
                GradientDrawable p = (GradientDrawable)textView.getBackground();
                p.setColor(mainMenuColor);
            }
            final int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    downAnimal(Dataindex.get(finalI), finalI);
                }
            });
            this.addView(textView);
            final ArrayList<String> arrayList = childData.get(i);
            for (int k = 0; k < arrayList.size(); k++) {
                final TextView textView1 = new TextView(context);
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(defultR, defultR);
                textView1.setLayoutParams(layoutParams1);
                textView1.setGravity(Gravity.CENTER);
                textView1.setTextSize(18);
                textView1.setText(arrayList.get(k));
                textView1.setTextColor(Color.WHITE);
                //textView.setBackgroundColor(Color.RED);
                textView1.setBackgroundResource(R.drawable.tv1_shap);
                if (itemMenuColor!=0){
                    GradientDrawable p1 = (GradientDrawable)textView1.getBackground();
                    p1.setColor(itemMenuColor);
                }
                final int finalK = k;
                textView1.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, arrayList.get(finalK), Toast.LENGTH_SHORT).show();
                    }
                });
                textView1.setVisibility(INVISIBLE);
                this.addView(textView1);
            }
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final TextView child = (TextView) this.getChildAt(i);
            int width = child.getLayoutParams().width;
            int height = child.getLayoutParams().height;
            child.layout(right / 2 - width / 2, bottom / 2 - height / 2, right / 2 + width / 2, bottom / 2 + height / 2);
        }
    }

    public void creat() {
        init(context);
        initRetunt();
        upAninmal();
    }

    private void initRetunt() {
        final TextView textView2 = new TextView(context);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(defultR, defultR);
        textView2.setLayoutParams(layoutParams2);
        textView2.setGravity(Gravity.CENTER);
        textView2.setTextSize(18);
        textView2.setText("上一级");
        textView2.setTextColor(Color.WHITE);
        //textView.setBackgroundColor(Color.RED);
        textView2.setBackgroundResource(R.drawable.tv_shap);
        if (mainMenuColor!=0){
            GradientDrawable p2 = (GradientDrawable)textView2.getBackground();
            p2.setColor(mainMenuColor);
        }
        textView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                retunAnimal();
            }
        });
        textView2.setVisibility(INVISIBLE);
        this.addView(textView2);
    }

    private void upAninmal() {

        int childCount = data.size();
        int childitem = childCount;
        //百分比
        if (childitem != 0) {
            int roud = 360 / childitem;
            for (int i = 0; i < childCount; i++) {
                double x = 0;
                double y = 0;
                final TextView childAt = (TextView) getChildAt(Dataindex.get(i));
                x = 0 + defultR*1.5 * Math.cos(roud * i * Math.PI / 180);
                y = 0 + defultR*1.5 * Math.sin(roud * i * Math.PI / 180);
                ObjectAnimator.ofFloat(childAt, "translationX", 0, (int) x).setDuration(500).start();
                ObjectAnimator.ofFloat(childAt, "translationY", 0, (int) y).setDuration(500).start();
                final ObjectAnimator alpha = ObjectAnimator.ofFloat(childAt, "alpha",  1.0f).setDuration(500);
                alpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float value = (Float) alpha.getAnimatedValue();
                        Log.d("value", String.valueOf(value));
                        if (value > 0.5f) {
                            childAt.setVisibility(VISIBLE);
                        }
                        if (value ==1f) {
                            childAt.setEnabled(true);
                        }
                    }
                });
                alpha.start();
            }
        }

    }

    private void downAnimal(Integer finalI, int index) {

        for (int i = 0; i < data.size(); i++) {
            final View childAt = getChildAt(Dataindex.get(i));
            childAt.setEnabled(false);
            ObjectAnimator.ofFloat(childAt, "translationX", 0).setDuration(500).start();
            ObjectAnimator.ofFloat(childAt, "translationY", 0).setDuration(500).start();
            final ObjectAnimator alpha = ObjectAnimator.ofFloat(childAt, "alpha", 0f).setDuration(500);
            alpha.start();
            alpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (Float) alpha.getAnimatedValue();
                    if (value <= 0.5f) {
                        childAt.setVisibility(INVISIBLE);
                    }
                }
            });

        }
        integers = childDataindex.get(index);
        int childitem = integers.size();
        final TextView retun = (TextView) getChildAt(getChildCount() - 1);
        for (int i = 0; i < integers.size(); i++) {
            final View childAt = getChildAt(integers.get(i));
            int roud = 360 / childitem;
            double x = 0;
            double y = 0;
            x = 0 + defultR*1.5 * Math.cos(roud * i * Math.PI / 180);
            y = 0 + defultR*1.5 * Math.sin(roud * i * Math.PI / 180);
            ObjectAnimator.ofFloat(childAt, "translationX", 0, (int) x).setDuration(500).start();
            ObjectAnimator.ofFloat(childAt, "translationY", 0, (int) y).setDuration(500).start();
            final ObjectAnimator alpha = ObjectAnimator.ofFloat(childAt, "alpha", 0f, 0.5f
                    , 1f).setDuration(500);
            alpha.start();
            alpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (Float) alpha.getAnimatedValue();
                    if (value >= 0.5f) {
                        childAt.setVisibility(VISIBLE);
                        retun.setVisibility(VISIBLE);
                    }
                    if (value==1f){
                        childAt.setEnabled(true);
                        retun.setEnabled(true);
                    }

                }
            });
        }
    }

    /**
     * 返回上一级的动画
     */
    private void retunAnimal() {
        final TextView retun = (TextView) getChildAt(getChildCount() - 1);
        for (int i = 0; i < integers.size(); i++) {
            final View childAt = getChildAt(integers.get(i));
            ObjectAnimator.ofFloat(childAt, "translationX", 0).setDuration(500).start();
            ObjectAnimator.ofFloat(childAt, "translationY", 0).setDuration(500).start();
            final ObjectAnimator alpha = ObjectAnimator.ofFloat(childAt, "alpha", 0f).setDuration(500);
            alpha.start();
            childAt.setEnabled(false);
            retun.setEnabled(false);
            alpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (Float) alpha.getAnimatedValue();
                    if (value <= 0.5f) {
                        childAt.setVisibility(INVISIBLE);
                        retun.setVisibility(INVISIBLE);
                    }
                }
            });
        }
        upAninmal();
    }

}
