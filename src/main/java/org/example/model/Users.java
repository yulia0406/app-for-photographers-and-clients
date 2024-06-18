package org.example.model;


import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "users")
@Getter
@Setter
public class Users implements Serializable {
    @Id
    @Column(name = "id_users")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "increment")
    private Long idusers;
    @Column(name = "login")
    private String loginUsers;
    @Column(name = "password")
    private String password;
    @Column(name = "fio")
    private String fio;
    @Column(name = "phone")
    private long phone;
    @Column(name = "city")
    private String city;
    @Column(name = "email")
    private String email;
    @Column(name = "post")
    private String post;
    @Column(name = "profile_status")
    private String profileStatus;

    @JsonCreator
    public Users(@JsonProperty("idusers")Long idusers, @JsonProperty("login")String loginUsers, @JsonProperty("fio") String fio, @JsonProperty("phone") long phone, @JsonProperty("city") String city, @JsonProperty("password")String password, @JsonProperty("email")String email, @JsonProperty("post")String post, @JsonProperty("profileStatus")String profileStatus) {
        this.idusers = idusers;
        this.loginUsers = loginUsers;
        this.fio = fio;
        this.phone = phone;
        this.city = city;
        this.password = password;
        this.email = email;
        this.post = post;
        this.profileStatus = profileStatus;
    }

    public Users() {
    }

    @JsonCreator
    public static Users getClient(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        Users users = null;
        try {
            users = objectMapper.readValue(json, Users.class);
            return users;
        }catch (IOException e){System.out.println(e);return users;}
    }


    @Override
    public String toString() {
        try{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idUsers", idusers);
        jsonObject.put("login", loginUsers);
        jsonObject.put("password", password);
        jsonObject.put("fio", fio);
        jsonObject.put("phone", phone);
        jsonObject.put("city", city);
        jsonObject.put("email", email);
        jsonObject.put("post", post);
        jsonObject.put("profileStatus", profileStatus);
        return jsonObject.toString();}
        catch (JSONException e){return null;}
    }
}