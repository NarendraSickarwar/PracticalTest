package test.narendra.com.gopettingpractical.mvp;


import android.support.annotation.StringRes;

import test.narendra.com.gopettingpractical.modal.GuideDataModal;

/**
 * <h1> MenuMVP usese to reduces the work on main activity
 * </h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */

public class MenuMVP {

    /**
     * View operations P->V
     */
    public interface RequiredViewOps {
        void hideProgressBar();

        void showProgressBar();

        void showMessage(String message);

        void showMessage(@StringRes int message);

        void showGuideItems(GuideDataModal menuItems);


    }

    /**
     * Presentor operations V->P
     */
    public interface PresentorOps {
        void getGuideItems();
    }

    /**
     * Presentor operations I->P
     */
    public interface PresentorRequiredOps {
        void guideItemsSuccess(GuideDataModal menuItems);


        void onError();

        void onError(String message);

        void onError(@StringRes int message);
    }

    /**
     * Presentor operations P->I
     */
    public interface ModelOps {

        void getGuideItems();


    }
}
