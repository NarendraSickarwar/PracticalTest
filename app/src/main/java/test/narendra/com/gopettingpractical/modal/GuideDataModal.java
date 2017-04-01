package test.narendra.com.gopettingpractical.modal;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import test.narendra.com.gopettingpractical.database.DataTable;

/**
 * <h1>Guide data getter and setter to help parse a json from webservice using json</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class GuideDataModal {


    private String total;


    @SerializedName("data")
    private List<DataTable> data = new ArrayList<>();

    public List<DataTable> getData() {
        return data;
    }

    public void setData(List<DataTable> data) {
        this.data = data;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public static class DataBean {


        private String startDate;
        private String objType;
        private String endDate;
        private String name;
        private boolean loginRequired;
        private String url;
        private VenueBean venue;
        private String icon;


        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getObjType() {
            return objType;
        }

        public void setObjType(String objType) {
            this.objType = objType;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isLoginRequired() {
            return loginRequired;
        }

        public void setLoginRequired(boolean loginRequired) {
            this.loginRequired = loginRequired;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public VenueBean getVenue() {
            return venue;
        }

        public void setVenue(VenueBean venue) {
            this.venue = venue;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }


        public static class VenueBean {
        }
    }
}
