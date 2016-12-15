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
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * class IncomingSmsReceiver is detecting incoming messages and broadcasting the details of messages
 */

public class IncomingSmsReceiver extends BroadcastReceiver {

    //onReceive
    public void onReceive(Context context, Intent intent) {

        //creating bundle object and storing all intent data to it
        Bundle bundle = intent.getExtras();

        //TAG for IncomingSmsReceiver
        String TAG = "IncomingSmsReceiver";
        try {
            //checking the bundle for null case
            if (bundle != null) {

                //creating object array for storing the data of bundle
                final Object[] pusObj = (Object[]) bundle.get("pdus");

                //looping through all objects for getting message details
                for (Object aPusObj : pusObj) {

                    //creating SmsMessage object and passing message data to it as byte stream
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) aPusObj);

                    //creating variables for sender number and message text and putting values into it
                    String senderNum = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();

                    //important logs for checking the data
                    Log.d(TAG, "------- In SMS Receiver --------------");
                    Log.d(TAG, "------- SenderNumber ----" + senderNum + "----- message is -------" + message);

                    //creating intent object for incoming messages and putting values of sender number and message body into it
                    Intent incomingMessagesIntent = new Intent("Msg");
                    incomingMessagesIntent.putExtra("senderNumber", senderNum);
                    incomingMessagesIntent.putExtra("message", message);

                    //sending broadcast for incomingMessages
                    LocalBroadcastManager.getInstance(context).sendBroadcast(incomingMessagesIntent);

                    if(message != null){
                        abortBroadcast();
                    }

                }//end of for loop

            }//if

        } catch (Exception e) {
            Log.e(TAG, "----------- Exception sms Receiver -----------" + e);
        }

    }//onReceive

}//IncomingSmsReceiver