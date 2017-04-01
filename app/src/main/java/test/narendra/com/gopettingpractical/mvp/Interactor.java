package test.narendra.com.gopettingpractical.mvp;

import android.content.Context;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import test.narendra.com.gopettingpractical.R;
import test.narendra.com.gopettingpractical.modal.GuideDataModal;
import test.narendra.com.gopettingpractical.rest.ApiService;
import test.narendra.com.gopettingpractical.rest.WebService;
import test.narendra.com.gopettingpractical.utils.ConnectionDetector;


/**
 * <h1>Interactor uses to set data to @{@link Presentor}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class Interactor implements MenuMVP.ModelOps {

    MenuMVP.PresentorRequiredOps requiredOps;
    ConnectionDetector cd;
    Subscription subscription;
    private WebService api;

    public Interactor(Context mCtx, MenuMVP.PresentorRequiredOps requiredOps) {
        this.requiredOps = requiredOps;
        cd = new ConnectionDetector(mCtx);
    }


    private WebService getApi() {
        if (api == null) {
            api = ApiService.getService();
        }
        return api;
    }

    @Override
    public void getGuideItems() {
        if (!cd.isConnectingToInternet()) {
            requiredOps.onError(R.string.network_not_availavle);
            return;
        }

        /**
         * Get data from retrofit web service for getGuide list
         * and observable to get data and sent Presentor
         */
        Observable<GuideDataModal> repositoryObservable = getApi().getGuideList();
        subscription = repositoryObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GuideDataModal>() {
                    @Override
                    public void call(GuideDataModal repositories) {

                        requiredOps.guideItemsSuccess(repositories);


                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        requiredOps.onError(throwable.getMessage());
                    }
                });
    }
}
