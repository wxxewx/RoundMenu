package com.nwy.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private myView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = (myView) findViewById(R.id.mv);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("小学");
        arrayList.add("初中");
        arrayList.add("高中");
        arrayList.add("大学");
        arrayList.add("上班族");
        arrayList.add("留学");
        arrayList.add("学前");
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("一年级");
        list1.add("二年级");
        list1.add("三年级");
        list1.add("四年级");
        list1.add("五年级");
        list1.add("六年级");
        for (int i = 0; i < arrayList.size(); i++) {
            viewById.setData(arrayList.get(i),list1);
        }
        viewById.setMainMenuColor(Color.BLUE);
        viewById.setItemMenuColor(Color.BLUE);
        viewById.creat();
    }
}
