package test.narendra.com.gopettingpractical.mvp;

import android.content.Context;
import android.support.annotation.StringRes;

import test.narendra.com.gopettingpractical.modal.GuideDataModal;

/**
 * <h1>Presentor is uses to handle the data form main activty and @{@link Interactor}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */

public class Presentor implements MenuMVP.PresentorOps, MenuMVP.PresentorRequiredOps {
    Interactor interactor;
    private MenuMVP.RequiredViewOps mViewOps;

    public Presentor(MenuMVP.RequiredViewOps requiredViewOps) {
        this.mViewOps = requiredViewOps;


    }

    public void inIt(Context context) {
        try {

            interactor = new Interactor(context, this);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void getGuideItems() {
        mViewOps.showProgressBar();
        interactor.getGuideItems();
    }

    @Override
    public void guideItemsSuccess(GuideDataModal menuItems) {
        mViewOps.hideProgressBar();
        mViewOps.showGuideItems(menuItems);
    }

    @Override
    public void onError() {
        mViewOps.hideProgressBar();

    }

    @Override
    public void onError(String message) {
        mViewOps.hideProgressBar();
        mViewOps.showMessage(message);
    }

    @Override
    public void onError(@StringRes int message) {
        mViewOps.hideProgressBar();
        mViewOps.showMessage(message);
    }
}
