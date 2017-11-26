package pidev.esprit.tn.insurance.Model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by aboud on 22/11/2017.
 */

public class Sinister {

    private int id;

    //l'autrevehicule
    private String nameFirstname;
    private String tel;
    private String email;
    private String nameInsurancCompany;
    private String policyNum;
    private Date dateCreation;
    private String urlImage;
    private Bitmap logo;


    public Sinister() {
        this.dateCreation = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFirstname() {
        return nameFirstname;
    }

    public void setNameFirstname(String nameFirstname) {
        this.nameFirstname = nameFirstname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameInsurancCompany() {
        return nameInsurancCompany;
    }

    public void setNameInsurancCompany(String nameInsurancCompany) {
        this.nameInsurancCompany = nameInsurancCompany;
    }

    public String getPolicyNum() {
        return policyNum;
    }

    public void setPolicyNum(String policyNum) {
        this.policyNum = policyNum;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }
}
