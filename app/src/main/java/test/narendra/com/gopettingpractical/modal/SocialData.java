package test.narendra.com.gopettingpractical.modal;

/**
 * <h1>uses for Social data model to get data from @{@link test.narendra.com.gopettingpractical.utils.GplusLoginHandler}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */

public class SocialData {
    private String id = "";
    private String name = "";
    private String firstname = "";
    private String lastname = "";
    private String email = "";
    private String loginfrom = "";
    private String Photo = "";

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginfrom() {
        return loginfrom;
    }

    public void setLoginfrom(String loginfrom) {
        this.loginfrom = loginfrom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id + ", " + "firstname=" + firstname + ", " + "lastname=" + lastname + ", " + "email=" + email + ", " + "loginfrom=" + loginfrom;
    }
}
