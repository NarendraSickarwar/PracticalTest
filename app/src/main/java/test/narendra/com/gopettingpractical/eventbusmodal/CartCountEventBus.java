package test.narendra.com.gopettingpractical.eventbusmodal;

/**
 * <h1>send data between app, uses send budge count for main activity @{@link org.greenrobot.eventbus.EventBus}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */

public class CartCountEventBus {

    private int mCount;

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }
}
