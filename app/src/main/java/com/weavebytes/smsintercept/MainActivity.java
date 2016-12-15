/*
 *  This file is part of Droid SMS Intercept sample app.
 *
 *  SocialDashboard is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  SocialDashboard is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with SocialDashboard.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 *  ------------------------------------------------------------------------------
 *     About: Weavebytes Infotech Pvt Ltd, India
 *   Website: www.weavebytes.com
 *  ------------------------------------------------------------------------------
 */


package com.weavebytes.smsintercept;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //creating listview object
    ListView listView;

    //creating SmsModel object
    SmsModel smsModel;

    //creating  object of adapter class
    MyAdapter myAdapter;

    //creating arraylist of type SmsModel
    ArrayList<SmsModel> arrayList;

    //button to clear list
    Button button;

    //TAG
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing stuff
        button = (Button) findViewById(R.id.btn_clear);
        listView = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        myAdapter = new MyAdapter(this, arrayList);

        //setting adapter to listview
        listView.setAdapter(myAdapter);

        //setting listener on button
        button.setOnClickListener(this);

        //registering receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("Msg"));

    }//onCreate

    //receiver for incoming sms, showing their details into logcat.
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d(TAG,"---------------------- [onReceive] --------------" );

            String senderNumber = intent.getStringExtra("senderNumber");
            String message = intent.getStringExtra("message");

            Log.d(TAG, " ------------ senderNumber --------" + senderNumber);
            Log.d(TAG, " ------------ message --------" + message);

            //refreshing listview with new msg data
            smsModel = new SmsModel(senderNumber, message);
            arrayList.add(smsModel);
            myAdapter = new MyAdapter(MainActivity.this, arrayList);
            listView.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();

        }//onReceive

    };//broadcastReceiver

    @Override
    protected void onDestroy() {

        try {
            //unregistering receiver
            LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_clear:

                //clearing list
                arrayList.clear();
                myAdapter.notifyDataSetChanged();

                break;
        }
    }


}//MainActivity
