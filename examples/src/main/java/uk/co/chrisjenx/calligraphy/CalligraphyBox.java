package uk.co.chrisjenx.calligraphy;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jimulabs.mirrorsandbox.MirrorAnimatorSandbox;

/**
 * Created by lintonye on 15-01-19.
 */
public class CalligraphyBox extends MirrorAnimatorSandbox {

    private final CalligraphyFactory mFactory;

    public CalligraphyBox() {
        super();
        mFactory = new CalligraphyFactory(R.attr.fontPath);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-ThinItalic.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    @Override
    public void enterSandbox() {
    }

    @Override
    public void onViewCreated(View view, Context context, AttributeSet attrs) {
        mFactory.onViewCreated(view, context, attrs);
    }
}
