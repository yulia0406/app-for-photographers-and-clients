package org.example.rest;


import org.example.model.Users;
import org.example.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;

@RestController
@RequestMapping("/api/v1/users/")
public class  UsersRestController {
    @Autowired
    private IUsersService usersService;

    @RequestMapping(value = "getUser/{id_Users}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Users> getUser(@PathVariable("id_Users") Long idUsers) {
        return usersService.getUser(idUsers);
    }

    @RequestMapping(value = "login/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getClientByLogin(@PathVariable String login) {
        return usersService.getClientByLogin(login);
    }


    @RequestMapping(value = "updateUsers/{login}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updateUsers(@PathVariable String login, @RequestBody Users user, UriComponentsBuilder builder) {
       return usersService.updateUser(login, user);
    }

    @RequestMapping(value = "deleteUser/{login}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteUsers(@PathVariable("login") String login) {
        return usersService.deleteUser(login);

    }

    @RequestMapping(value = "allUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getAllUsers() {
        return usersService.allUsers();
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody Users client) {
        return usersService.register(client);
    }

    @RequestMapping(value = "authorization", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> authorization(@RequestBody Users client) {
        return usersService.authorization(client);
    }
}
