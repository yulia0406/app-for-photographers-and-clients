package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.example.LocalDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "photosession")
@Getter
@Setter
public class Photosession
{

    @Id
    @Column(name = "id_photosession")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "increment")
    private Long idPhotosession;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "date_create")
    private LocalDateTime dateCreate;
    @ManyToOne
    @JoinColumn(name = "status")
    private Status status;
    @Column(name = "name")
    private String name;
    @Column(name = "number_processed")
    private Integer numProcessed;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Users client;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Users author;
    @OneToMany(mappedBy="photosession", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Image> images;






    public Photosession(Long idPhotosession, LocalDateTime dateCreate, Status status, Users client, Users author, String name, Integer numProcessed) {
        this.idPhotosession = idPhotosession;
        this.dateCreate = dateCreate;
        this.status = status;
        this.client = client;
        this.author = author;

        this.name = name;
        this.numProcessed = numProcessed;
    }

    public Photosession() {
    }


        @JsonCreator
        public static Photosession getPhotosession(String json){
            ObjectMapper objectMapper = new ObjectMapper();
            Photosession photosession = null;
            try {
                photosession = objectMapper.readValue(json, Photosession.class);
                return photosession;
            }catch (IOException e){System.out.println(e);return photosession;}
        }

    @Override
    public String toString() {
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idPhotosession", idPhotosession);
            jsonObject.put("dateCreate", dateCreate);
            jsonObject.put("status", status);
            jsonObject.put("client", client);
            jsonObject.put("author", author);

            return jsonObject.toString();}
        catch (JSONException e){return null;}
    }
}
