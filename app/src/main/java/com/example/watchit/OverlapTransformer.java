package com.example.watchit;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;

public class OverlapTransformer implements ViewPager2.PageTransformer {
    private static final float SCALE_FACTOR = 0.85f;
    private static final float TRANSLATION_FACTOR = -0.2f;
    private static final float MIN_ALPHA = 0.7f;

    @Override
    public void transformPage(View page, float position) {
        float scaleFactor = Math.max(SCALE_FACTOR, 1 - Math.abs(position) * 0.15f);
        float translationX = position * -page.getWidth() * TRANSLATION_FACTOR;
        float alphaFactor = Math.max(MIN_ALPHA, 1 - Math.abs(position) * 0.4f);

        // Proper stacking order
        if (position < 0) {
            page.setTranslationX(translationX);
            page.setTranslationZ(position);
        } else {
            page.setTranslationX(translationX);
            page.setTranslationZ(-position);
        }

        page.setScaleY(scaleFactor);
        page.setAlpha(alphaFactor);
    }
}
