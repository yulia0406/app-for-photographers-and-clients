package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Users;
import org.example.repository.UsersRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService implements IUsersService

{
    @Autowired
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public ResponseEntity<Users> getUser(Long idUsers)
    {
        if (idUsers == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Users user = this.usersRepository.findById(idUsers).get();
        if (idUsers == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    public ResponseEntity<String> getClientByLogin(String login)
    {
        Users users = usersRepository.findClientByLoginUsers(login);
        long id = users.getIdusers();
        if (users == null) {
            return ResponseEntity.notFound().build();
        }
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("str", users);
        String json = jsonObject1.toString();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    public ResponseEntity<String> updateUser(String login, Users user)
    {
        HttpHeaders heards = new HttpHeaders();
        Users users = usersRepository.findClientByLoginUsers(login);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(user);
        users.setPassword(user.getPassword());
        users.setCity(user.getCity());
        System.out.println(users.getPost());
        System.out.println(user.getProfileStatus());
        users.setEmail(user.getEmail());
        users.setFio(user.getFio());
        users.setPhone(user.getPhone());
        users.setLoginUsers(user.getLoginUsers());
        users.setProfileStatus(user.getProfileStatus());

        this.usersRepository.save(users);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("strup", "Данные успешно обновлены");
        String json = jsonObject1.toString();
        return new ResponseEntity<>(json, heards, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUser(String login)
    {
        Users user = this.usersRepository.findClientByLoginUsers(login);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.usersRepository.deleteById(user.getIdusers());
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("strdel", "Пользователь успешно удален");
        String json = jsonObject1.toString();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    public ResponseEntity<String> allUsers()
    {
        List<Users> users = this.usersRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("users", users);
        try{
            String str = objectMapper.writeValueAsString(jsonMap);
            System.out.println(str);
            return new ResponseEntity<>(str, HttpStatus.OK);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> register(Users client)
    {
        System.out.println(client.toString());
        List<Users> users = this.usersRepository.findAll();
        if (users
                .stream()
                .anyMatch(
                        predicat ->
                                predicat.getLoginUsers().equals(client.getLoginUsers())
                )
        ) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("strlog", "Клиент с таким логином уже существует");
            String json = jsonObject1.toString();
            System.out.println(json);
            return new ResponseEntity<>(json, HttpStatus.OK);
        }
        System.out.println("dsa");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("strlog", "Регистрация прошла успешно! Войдите в систему.");
        String json = jsonObject2.toString();
        usersRepository.save(client);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
    public ResponseEntity<String> authorization(Users client){
        List<Users> users = this.usersRepository.findAll();
        System.out.println(client.getLoginUsers());
        System.out.println(client.getPassword());
        Optional<Users> optional = users
                .stream()
                .filter(
                        predicat -> predicat.getLoginUsers().equals(client.getLoginUsers()) &&
                                predicat.getPassword().equals(client.getPassword())
                ).findFirst();
        int index = optional.isPresent()?users.indexOf(optional.get()):-1;
        Users users1 = users.get(index);
        if (optional.isPresent()) {

            System.out.println(users1.toString());
            String post = users1.getPost();
            System.out.println(post);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", users1);
            String json = jsonObject.toString();
            System.out.println(json);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("strlogin", "0");
            String json = jsonObject.toString();
            System.out.println("");
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
        }
    }

}
