package com.example.oualhaomar.insurancepi.Entities;

import com.android.volley.toolbox.StringRequest;

/**
 * Created by oualhaomar on 26/11/2017.
 */

public class Messages {
    private int idMessage;
    private String contenu;
    private String datePost;
    private String dateEdit;
    private int nbLike;
    private Topic topic;
    private User user;


    public Messages() {
    }

    public Messages(int idMessage, String contenu, String datePost, String dateEdit, int nbLike) {
        this.idMessage = idMessage;
        this.contenu = contenu;
        this.datePost = datePost;
        this.dateEdit = dateEdit;
        this.nbLike = nbLike;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(String dateEdit) {
        this.dateEdit = dateEdit;
    }

    public int getNbLike() {
        return nbLike;
    }

    public void setNbLike(int nbLike) {
        this.nbLike = nbLike;
    }


    @Override
    public String toString() {
        return "Messages{" +
                "idMessage=" + idMessage +
                ", contenu='" + contenu + '\'' +
                ", datePost='" + datePost + '\'' +
                ", dateEdit='" + dateEdit + '\'' +
                ", nbLike=" + nbLike +
                '}';
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
