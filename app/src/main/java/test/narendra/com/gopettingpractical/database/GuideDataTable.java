package test.narendra.com.gopettingpractical.database;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * <h1>provide and handle all data of guide @{@link io.realm.RealmObject}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class GuideDataTable extends RealmObject {

    private int total;
    @PrimaryKey
    private int ID;
    private RealmList<DataTable> data = new RealmList<>();

    public RealmList<DataTable> getData() {
        return data;
    }

    public void setData(RealmList<DataTable> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
