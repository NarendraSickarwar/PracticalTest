package test.narendra.com.gopettingpractical.rest;

import retrofit2.http.GET;
import rx.Observable;
import test.narendra.com.gopettingpractical.modal.GuideDataModal;

/**
 * <h1>create method for web service calling </h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public interface WebService {
    String appurl = "v2";

    @GET(appurl + "/upcomingGuides/")
    Observable<GuideDataModal> getGuideList();


}
