package test.narendra.com.gopettingpractical.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * <h1>guide list save in data base @{@link RealmObject}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */

public class DataTable extends RealmObject {

    private String startDate;
    private String objType;
    private String endDate;
    @PrimaryKey
    private String name;
    private boolean loginRequired;
    private String url;
    private boolean addToCart;
    private String icon;


    public boolean isAddToCart() {
        return addToCart;
    }

    public void setAddToCart(boolean addToCart) {
        this.addToCart = addToCart;
    }


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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
