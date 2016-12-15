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

import android.util.Log;

import java.io.Serializable;

/**
 * class SmsModel for message number and body
 */
public class SmsModel implements Serializable{

    public String number;
    public String message;

    public SmsModel(String number, String message) {

        this.number = number;
        this.message = message;

        Log.d("model","-------------------" + message);
        Log.d("model","-------------------" + number);
    }

}
