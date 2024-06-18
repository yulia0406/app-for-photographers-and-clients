package org.example.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.IOException;
import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.example.LocalDateTimeDeserializer;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "image")
@Getter
@Setter
public class Image implements Serializable {
    @Id
    @Column(name = "id_image")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "increment")
    private Long idImage;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "date_create")
    private LocalDateTime dateCreate;
    @Column(name = "resolution")
    private String resolution;
    @Column(name = "link")
    private String link;
    @Column(name = "link_orig")
    private String linkorig;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_format")
    private Format idFormat;

    @ManyToOne
    @JoinColumn(name = "id_photosession")
    private Photosession photosession;

    @Column(name = "photo_processing")
    private int process;


    public Image(Long idImage, LocalDateTime dateCreate, String resolution, String link, Format idFormat, String name, String linkorig, Photosession photosession, int process) {
        this.idFormat = idFormat;
        this.idImage = idImage;
        this.dateCreate = dateCreate;
        this.resolution = resolution;
        this.link = link;
        this.name = name;
        this.linkorig = linkorig;
        this.photosession = photosession;
        this.process = process;
    }

    public Image() {
    }

    @JsonCreator
    public static Image getImage(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        Image picture = null;
        try {
            picture = objectMapper.readValue(json, Image.class);
            return picture;
        }catch (IOException e){System.out.println(e);return picture;}
    }

    @Override
    public String toString() {
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idImage", idImage);
            jsonObject.put("idFormat", idFormat);
            jsonObject.put("dateCreate", dateCreate);
            jsonObject.put("photosession", photosession);
            jsonObject.put("process", process);
            jsonObject.put("resolution", resolution);
            jsonObject.put("linkorig", linkorig);
            jsonObject.put("link", link);
            jsonObject.put("name", name);
            return jsonObject.toString();}
        catch (JSONException e){return null;}
    }
   /* @JsonCreator
    public static Image getMenu(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        Image image = null;
        try {
            image = objectMapper.readValue(json, Image.class);
            return image;
        }catch (IOException e){System.out.println(e);return image;}
    }*/




}