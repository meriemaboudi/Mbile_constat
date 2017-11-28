package com.example.oualhaomar.insurancepi.Entities;

import java.util.Date;

/**
 * Created by oualhaomar on 23/11/2017.
 */

public class Topic {


    private int idTopic;
    private String sujet;
    private String contenu;
    private String dateCreation;
    private Date lastpost;
    private User idCreateur ;
    private SousCategory idSouscategory;
    private String username;


    public Topic() {
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getLastpost() {
        return lastpost;
    }

    public void setLastpost(Date lastpost) {
        this.lastpost = lastpost;
    }

    public User getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(User idCreateur) {
        this.idCreateur = idCreateur;
    }

    public Topic(int idTopic, String sujet, String contenu, String dateCreation, Date lastpost, User idCreateur) {
        this.idTopic = idTopic;
        this.sujet = sujet;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
        this.lastpost = lastpost;
        this.idCreateur = idCreateur;
    }

    public Topic(int idTopic, String sujet, String contenu, String dateCreation, Date lastpost) {
        this.idTopic = idTopic;
        this.sujet = sujet;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
        this.lastpost = lastpost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SousCategory getIdSouscategory() {
        return idSouscategory;
    }

    public void setIdSouscategory(SousCategory idSouscategory) {
        this.idSouscategory = idSouscategory;
    }
}
