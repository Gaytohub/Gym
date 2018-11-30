package com.example.tiamo.gym;

import java.util.Timer;
import java.util.TimerTask;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class SlidingSwitcherView extends RelativeLayout implements OnTouchListener {
    //让菜单滚动，手指应达到的速度
    public static final int SNAP_VELOCITY = 200;
    private int switcherViewWidth;
    //当前标签的下标
    private int currentItemIndex;
    //菜单中所有标签的总数
    private int totalItem;
    //各个元素偏移的边界值
    private int[] border;
    //菜单中的第一个元素。
    private View firstItem;
    private MarginLayoutParams firstItemParams;
    private int leftEdge = 0;
    private int rightEdge = 0;
    private float xDown;
    private float xMove;
    private float xUp;
    //标签布局
    private LinearLayout dotsLayout;
    private LinearLayout itemsLayout;
    //用于在定时器当中操作UI界面。
    private Handler handler = new Handler();
    private VelocityTracker mVelocityTracker;


    public SlidingSwitcherView(Context context, AttributeSet attrs){
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingSwitcherView);
        boolean isAutoPlay = a.getBoolean(R.styleable.SlidingSwitcherView_auto_play, false);
        if (isAutoPlay) {
            startAutoPlay();
        }
        a.recycle();
    }

    //滚动到下一个标签
    public void scrollToNext() {
        new ScrollTask().execute(-20);
    }

    public void scrollToPrevious() {
        new ScrollTask().execute(20);
    }

    //滚动到第一个标签
    public void scrollToFirstItem() {
        new ScrollToFirstItemTask().execute(20 * totalItem);
    }

    //开启图片自动播放的功能
    public void startAutoPlay(){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentItemIndex == totalItem - 1){
                    currentItemIndex = 0;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollToFirstItem();
                            refreshDotsLayout();
                        }
                    });
                }else {
                    currentItemIndex++;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollToNext();
                            refreshDotsLayout();
                        }
                    });
                }
            }
        }, 3000, 3000);
    }

    /*
    *在onLayout中重新设定菜单元素和标签元素的参数。
     */
    protected void onLayout(boolean changed, int l, int t, int r , int b){
        super.onLayout(changed, l, t, r, b);
        if(changed){
            initializeItems();
            initializeDots();
        }
    }

    /*
    *初始化菜单元素，为每一个子元素增加监听事件，并且改变所有子元素的宽度，让它们等于父元素的宽度
     */
    private void initializeItems(){
        switcherViewWidth = getWidth();
        itemsLayout = (LinearLayout) getChildAt(0);
        totalItem = itemsLayout.getChildCount();
        border = new int[totalItem];
        for (int i = 0; i < totalItem; i++){
            border[i] = -i * switcherViewWidth;
            View item = itemsLayout.getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) item.getLayoutParams();
            params.width = switcherViewWidth;
            item.setLayoutParams(params);
            item.setOnTouchListener(this);
        }
        leftEdge = border[totalItem - 1];
        firstItem = itemsLayout.getChildAt(0);
        firstItemParams = (MarginLayoutParams) firstItem.getLayoutParams();
    }

    private void initializeDots(){
        dotsLayout = (LinearLayout) getChildAt(1);
        refreshDotsLayout();
    }

    public boolean onTouch(View v, MotionEvent event){
        createVelocityTracker(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                xDown = event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = event.getRawX();
                int distanceX = (int) (xMove - xDown) - (currentItemIndex * switcherViewWidth);
                firstItemParams.leftMargin  = distanceX;
                if (beAbleToScroll()) {
                    firstItem.setLayoutParams(firstItemParams);
                }
                break;
            case MotionEvent.ACTION_UP:
                xUp = event.getRawX();
                if (beAbleToScroll()){
                    if (wantScrollToPrevious()){
                        if (shouldScrollToPrevious()){
                            currentItemIndex--;
                            scrollToPrevious();
                            refreshDotsLayout();
                        }else {
                            scrollToNext();
                        }
                    }else if (wantScrollToNext()){
                        if (shouldScrollToNext()){
                            currentItemIndex++;
                            scrollToNext();
                            refreshDotsLayout();
                        }else {
                            scrollToPrevious();
                        }
                    }
                }
                recycleVelocityTracker();
                break;
        }
        return false;
    }

    private boolean beAbleToScroll() {
        return firstItemParams.leftMargin < rightEdge && firstItemParams.leftMargin > leftEdge;
    }

    private boolean wantScrollToPrevious() {
        return xUp - xDown > 0;
    }

    private boolean wantScrollToNext() {
        return xUp - xDown < 0;
    }

    private boolean shouldScrollToNext() {
        return xDown - xUp > switcherViewWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;
    }

    private boolean shouldScrollToPrevious() {
        return xUp - xDown > switcherViewWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;
    }

    private void refreshDotsLayout() {
        dotsLayout.removeAllViews();
        for (int i = 0; i < totalItem; i++) {
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(0,
                    LayoutParams.FILL_PARENT);
            linearParams.weight = 1;
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            ImageView image = new ImageView(getContext());
            if (i == currentItemIndex) {
                image.setBackgroundResource(R.drawable.dot_selected);
            } else {
                image.setBackgroundResource(R.drawable.dot_unselected);
            }
            LayoutParams relativeParams = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            relativeParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            relativeLayout.addView(image, relativeParams);
            dotsLayout.addView(relativeLayout, linearParams);
        }
    }

    private void createVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getXVelocity();
        return Math.abs(velocity);
    }

    /**
     * 回收VelocityTracker对象。
     */
    private void recycleVelocityTracker() {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
    }


    /**
     * 检测菜单滚动时，是否有穿越border，border的值都存储在{@link #border}中。
     *
     * @param leftMargin
     *            第一个元素的左偏移值
     * @param speed
     *            滚动的速度，正数说明向右滚动，负数说明向左滚动。
     * @return 穿越任何一个border了返回true，否则返回false。
     */
    private boolean isCrossBorder(int leftMargin, int speed) {
        for (int border : border) {
            if (speed > 0) {
                if (leftMargin >= border && leftMargin - speed < border) {
                    return true;
                }
            } else {
                if (leftMargin <= border && leftMargin - speed > border) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 找到离当前的leftMargin最近的一个border值。
     *
     * @param leftMargin
     *            第一个元素的左偏移值
     * @return 离当前的leftMargin最近的一个border值。
     */
    private int findClosestBorder(int leftMargin) {
        int absLeftMargin = Math.abs(leftMargin);
        int closestBorder = border[0];
        int closestMargin = Math.abs(Math.abs(closestBorder) - absLeftMargin);
        for (int border : border) {
            int margin = Math.abs(Math.abs(border) - absLeftMargin);
            if (margin < closestMargin) {
                closestBorder = border;
                closestMargin = margin;
            }
        }
        return closestBorder;
    }

    class ScrollToFirstItemTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... speed) {
            int leftMargin = firstItemParams.leftMargin;
            while (true) {
                leftMargin = leftMargin + speed[0];
                // 当leftMargin大于0时，说明已经滚动到了第一个元素，跳出循环
                if (leftMargin > 0) {
                    leftMargin = 0;
                    break;
                }
                publishProgress(leftMargin);
                sleep(20);
            }
            return leftMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... leftMargin) {
            firstItemParams.leftMargin = leftMargin[0];
            firstItem.setLayoutParams(firstItemParams);
        }

        @Override
        protected void onPostExecute(Integer leftMargin) {
            firstItemParams.leftMargin = leftMargin;
            firstItem.setLayoutParams(firstItemParams);
        }
    }

    class ScrollTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... speed) {
            int leftMargin = firstItemParams.leftMargin;
            // 根据传入的速度来滚动界面，当滚动穿越border时，跳出循环。
            while (true) {
                leftMargin = leftMargin + speed[0];
                if (isCrossBorder(leftMargin, speed[0])) {
                    leftMargin = findClosestBorder(leftMargin);
                    break;
                }
                publishProgress(leftMargin);
                // 为了要有滚动效果产生，每次循环使线程睡眠10毫秒，这样肉眼才能够看到滚动动画。
                sleep(10);
            }
            return leftMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... leftMargin) {
            firstItemParams.leftMargin = leftMargin[0];
            firstItem.setLayoutParams(firstItemParams);
        }

        @Override
        protected void onPostExecute(Integer leftMargin) {
            firstItemParams.leftMargin = leftMargin;
            firstItem.setLayoutParams(firstItemParams);
        }
    }

    /**
     * 使当前线程睡眠指定的毫秒数。
     *
     * @param millis
     *            指定当前线程睡眠多久，以毫秒为单位
     */
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
