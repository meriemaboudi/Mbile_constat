package pidev.esprit.tn.insurance.Model;

import java.util.Date;

/**
 * Created by aboud on 22/11/2017.
 */

public class Sinister {

    private int id;

    //l'autrevehicule
    String compagnieAssurance;
    String immatriculation;
    String numContract;
    String nameInsured;
    String nameConductor;
    String tel;
    String mail;
    String typeCar;
    String ptChoc;
    String description;
    String circonstance;
    String State;
    private Date dateCreation;
    private Date dateAccident;

    public Sinister() {
    }

    public Sinister(String compagnieAssurance, String immatriculation, String numContract, String nameInsured, String nameConductor, String tel, String mail, String typeCar, String ptChoc, String description, String circonstance, String state, Date dateCreation, Date dateAccident) {
        this.compagnieAssurance = compagnieAssurance;
        this.immatriculation = immatriculation;
        this.numContract = numContract;
        this.nameInsured = nameInsured;
        this.nameConductor = nameConductor;
        this.tel = tel;
        this.mail = mail;
        this.typeCar = typeCar;
        this.ptChoc = ptChoc;
        this.description = description;
        this.circonstance = circonstance;
        State = state;
        this.dateCreation = dateCreation;
        this.dateAccident = dateAccident;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompagnieAssurance() {
        return compagnieAssurance;
    }

    public void setCompagnieAssurance(String compagnieAssurance) {
        this.compagnieAssurance = compagnieAssurance;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getNumContract() {
        return numContract;
    }

    public void setNumContract(String numContract) {
        this.numContract = numContract;
    }

    public String getNameInsured() {
        return nameInsured;
    }

    public void setNameInsured(String nameInsured) {
        this.nameInsured = nameInsured;
    }

    public String getNameConductor() {
        return nameConductor;
    }

    public void setNameConductor(String nameConductor) {
        this.nameConductor = nameConductor;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(String typeCar) {
        this.typeCar = typeCar;
    }

    public String getPtChoc() {
        return ptChoc;
    }

    public void setPtChoc(String ptChoc) {
        this.ptChoc = ptChoc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCirconstance() {
        return circonstance;
    }

    public void setCirconstance(String circonstance) {
        this.circonstance = circonstance;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateAccident() {
        return dateAccident;
    }

    public void setDateAccident(Date dateAccident) {
        this.dateAccident = dateAccident;
    }
}
