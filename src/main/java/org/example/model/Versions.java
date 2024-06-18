package org.example.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "versions")
public class Versions implements Serializable
{
    @Id
    @Column(name = "id_versions")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "increment")
    private Long idVersions;


    @ManyToOne
    @JoinColumn(name = "id_image_processed")
    private Image image;
    @ManyToOne
    @JoinColumn(name = "id_image_original")
    private Image imageOrig;



    @ManyToOne
    @JoinColumn(name = "id_consent_publ_author")
    private ConsentPubl consent_publ;

    @ManyToOne
    @JoinColumn(name = "id_consent_publ_client")
    private ConsentPubl consent_publ_client;

    public Versions(Long idVersions, Image image, Image imageOrig, ConsentPubl consent_publ, ConsentPubl consent_publ_client) {
        this.idVersions = idVersions;
        this.imageOrig = imageOrig;
        this.image = image;
        this.consent_publ_client = consent_publ_client;

        this.consent_publ = consent_publ;
    }

    public Versions() {
    }

    public void setIdVersions(Long idVersions) {
        this.idVersions = idVersions;
    }
    public Long getIdVersions() {
        return idVersions;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public Image getImage() {
        return image;
    }

    public void setImageOrig(Image image) {
        this.imageOrig = image;
    }
    public Image getImageOrig() {
        return imageOrig;
    }
    public ConsentPubl getConsent_publ(){return  consent_publ;}
    public void setConsent_publ(ConsentPubl consent_publ){this.consent_publ = consent_publ;}
    public ConsentPubl getConsent_publ_client(){return  consent_publ_client;}
    public void setConsent_publ_client(ConsentPubl consent_publ){this.consent_publ_client = consent_publ;}


    /*@JsonCreator
    public static Versions getDish(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        Versions versions = null;
        try {
            versions = objectMapper.readValue(json, Versions.class);
            return versions;
        }catch (IOException e){System.out.println(e);return versions;}
    }*/
}
