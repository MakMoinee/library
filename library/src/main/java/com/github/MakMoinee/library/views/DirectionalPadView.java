package com.github.MakMoinee.library.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DirectionalPadView extends View {
    public interface OnDirectionListener {
        void onUp();
        void onDown();
        void onLeft();
        void onRight();
        void onCenterRelease();
    }

    private OnDirectionListener listener;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private RectF upRect = new RectF();
    private RectF downRect = new RectF();
    private RectF leftRect = new RectF();
    private RectF rightRect = new RectF();

    public DirectionalPadView(Context context) {
        super(context);
        init();
    }

    public DirectionalPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DirectionalPadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setColor(0xFF555555);
    }

    public void setOnDirectionListener(OnDirectionListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        float centerX = w / 2f;
        float centerY = h / 2f;
        float size = Math.min(w, h) / 3f;

        upRect.set(centerX - size/2, 0, centerX + size/2, centerY);
        downRect.set(centerX - size/2, centerY, centerX + size/2, h);
        leftRect.set(0, centerY - size/2, centerX, centerY + size/2);
        rightRect.set(centerX, centerY - size/2, w, centerY + size/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRect(upRect, paint);
        canvas.drawRect(downRect, paint);
        canvas.drawRect(leftRect, paint);
        canvas.drawRect(rightRect, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN ||
                event.getAction() == MotionEvent.ACTION_MOVE) {

            if (upRect.contains(x, y)) {
                if (listener != null) listener.onUp();
            } else if (downRect.contains(x, y)) {
                if (listener != null) listener.onDown();
            } else if (leftRect.contains(x, y)) {
                if (listener != null) listener.onLeft();
            } else if (rightRect.contains(x, y)) {
                if (listener != null) listener.onRight();
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP) {

            if (listener != null) listener.onCenterRelease();
        }

        return true;
    }

    public void setPadColor(int color) {
        paint.setColor(color);
        invalidate();
    }
}
