package iammert.com.view.scalinglib;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mertsimsek on 30/09/2017.
 */

public class ScalingLayoutBehavior extends CoordinatorLayout.Behavior<ScalingLayout> {


    public ScalingLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ScalingLayout child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ScalingLayout child, View dependency) {
        int totalScrollRange = ((AppBarLayout) dependency).getTotalScrollRange();
        int toolbarHeightInPixel ;
        if(child.hasToolbar()) toolbarHeightInPixel = parent.getContext().getResources().getDimensionPixelSize(R.dimen.sl_toolbar_size);
        else toolbarHeightInPixel = 0 ;

        child.setProgress((-dependency.getY()) / totalScrollRange);
        if (totalScrollRange + dependency.getY() > (float) child.getMeasuredHeight() / 2) {
            child.setTranslationY(totalScrollRange + dependency.getY() + toolbarHeightInPixel - (float) child.getMeasuredHeight() / 2);
        } else {
            child.setTranslationY(toolbarHeightInPixel);
        }
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
