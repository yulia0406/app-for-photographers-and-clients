package org.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "consent_publ")
public class ConsentPubl implements Serializable
{
    @Id
    @Column(name = "id_consent_publ")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "increment")
    private Long idConsentPubl;

    @Column(name = "name_consent_publ")
    private String nameConsentPubl;


    public ConsentPubl(Long idConsentPubl, String nameConsentPubl) {
        this.idConsentPubl = idConsentPubl;
        this.nameConsentPubl = nameConsentPubl;
    }

    public ConsentPubl() {
    }




    public Long getIdConsentPubl() {
        return idConsentPubl;
    }
    public void setIdConsentPubl(Long idConsentPubl) {
        this.idConsentPubl = idConsentPubl;
    }

    //@JsonIgnore
    public String getNameConsentPubl() {
        return nameConsentPubl;
    }
    public void setNameConsentPubl(String nameConsentPubl) {
        this.nameConsentPubl = nameConsentPubl;
    }



    /*@JsonCreator
    public static Status getStatus(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        Status versions = null;
        try {
            versions = objectMapper.readValue(json, Status.class);
            return versions;
        }catch (IOException e){System.out.println(e);return versions;}
    }*/
}
