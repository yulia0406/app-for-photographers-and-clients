package org.example.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.LocalDateTimeDeserializer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comments
{

    @Id
    @Column(name = "id_comments")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "increment")
    private Long idComments;
    @Column(name = "text")
    private String text;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "date")
    private LocalDateTime dateCreate;


    // @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Order> orders = new ArrayList<>();

    /*public Comments(Long id, Integer countDish) {
        this.id_Orders_Content = id;
        this.countDish = countDish;
    }*/
   // public Comments(Integer countDish) {
      //  this.countDish = countDish;
    //}

    public Comments() {
    }
    @ManyToOne
    @JoinColumn(name = "login")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "id_versions")
    private Versions versions;
    @ManyToOne
    @JoinColumn(name = "id_image")
    private Image image;
    public void setIdComments(Long idComments) {
        this.idComments = idComments;
    }

    public Long getIdComments() {
        return idComments;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }
    public Image getImage(){return this.image;}
    public void setImage(Image image){this.image = image;}

    public Users getUsers(){return this.users;}
    public void setUsers(Users users){this.users = users;}

    public Versions getVersions(){return this.versions;}
    public void setVersions(Versions versions){this.versions = versions;}

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }
}
