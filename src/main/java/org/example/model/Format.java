package org.example.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.LocalDateTimeDeserializer;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "format")
public class Format implements Serializable {
    @Id
    @Column(name = "id_format")
    private Long id_format;
    @Column(name = "name_format")
    private String name_format;


    public Format() {
    }

    public void setIdFormat(Long id_format) {
        this.id_format = id_format;
    }

    public Long getIdFormat() {
        return id_format;
    }
    public void setNameFormat(String name_format) {
        this.name_format = name_format;
    }

    public String getNameFormat() {
        return name_format;
    }


    @Override
    public String toString() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id_format", id_format);
            jsonObject.put("name_format", name_format);

            return jsonObject.toString();
        } catch (JSONException e) {
            return null;
        }
    }

    public Format(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            this.id_format = jsonObject.getLong("id_format");
            this.name_format = jsonObject.getString("name_format");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}