package org.example.service;
import org.example.model.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUsersService {

    ResponseEntity<String> authorization(Users client);
    ResponseEntity<String> register(Users client);
    ResponseEntity<Users> getUser(Long idUsers);
    ResponseEntity<String> getClientByLogin(String login);
    ResponseEntity<String> updateUser(String login, Users user);
    ResponseEntity<String> deleteUser(String login);
    ResponseEntity<String> allUsers();
}
