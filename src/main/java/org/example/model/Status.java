package org.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "status")
public class Status implements Serializable
{
    @Id
    @Column(name = "id_status")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "increment")
    private Long idStatus;

    @Column(name = "name_status")
    private String nameStatus;




    @JsonCreator
    public Status(@JsonProperty("idStatus") Long idStatus, @JsonProperty("nameStatus") String nameStatus) {
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
    }

    public Status() {
    }

    public Status(String status) {
        // Распарсить строку JSON и инициализировать поля
        JSONObject jsonObject = new JSONObject(status);
        this.idStatus = jsonObject.getLong("idStatus");
        this.nameStatus = jsonObject.getString("nameStatus");
    }




    public Long getIdStatus() {
        return idStatus;
    }
    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    //@JsonIgnore
    public String getNameStatus() {
        return nameStatus;
    }
    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }



    @JsonCreator
    public static Status getStatus(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        Status versions = null;
        try {
            versions = objectMapper.readValue(json, Status.class);
            return versions;
        }catch (IOException e){System.out.println(e);return versions;}
    }
}
