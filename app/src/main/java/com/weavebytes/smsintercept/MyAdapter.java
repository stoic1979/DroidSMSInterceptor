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

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adapter for sms returning view of sms number and body
 */
public class MyAdapter extends ArrayAdapter {

    Context context;
    ArrayList<SmsModel> arrayList;

    public MyAdapter(Context context, ArrayList<SmsModel> arrayList) {
        super(context, R.layout.sms_lsit, arrayList);
        this.context = context;
        this.arrayList = arrayList;

    }//constructor


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.sms_lsit, null, true);

        TextView sms = (TextView) rowView.findViewById(R.id.txt_msg);
        TextView number = (TextView) rowView.findViewById(R.id.txt_number);

        for (int i = 0 ; i<arrayList.size() ; i++){
            Log.d("MyAdapter","-------------------" + arrayList.get(i).message);
            Log.d("MyAdapter","-------------------" + arrayList.get(i).number);
        }

        sms.setText(arrayList.get(position).message);
        number.setText(arrayList.get(position).number);

        return rowView;
    }

}
