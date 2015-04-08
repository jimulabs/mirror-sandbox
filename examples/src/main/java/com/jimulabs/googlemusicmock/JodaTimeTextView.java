package com.jimulabs.googlemusicmock;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Set;

/**
 * Created by lintonye on 15-04-05.
 */
public class JodaTimeTextView extends TextView {
    public JodaTimeTextView(Context context) {
        this(context, null);
    }

    public JodaTimeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JodaTimeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Set<String> zoneIds = DateTimeZone.getAvailableIDs();
        StringBuffer sb = new StringBuffer();
        for (String zid : zoneIds) {
            if (sb.length() > 0) sb.append("\n");
            DateTimeZone zone = DateTimeZone.forID(zid);
            DateTime now = DateTime.now(zone);
            sb.append(zid).append(": ").append(now);
        }
        setText(sb);
    }
}
