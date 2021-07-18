package com.example.demo.ApiRest;

import com.example.demo.model.User;
import com.example.demo.service.UserDAOService;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
// @CrossOrigin(origins = "http://localhost:8080")
// //Cross-Origin Resource Sharing (CORS) is a security concept that allows
// //restricting the resources implemented in web browsers.
@CrossOrigin("*")
@RequestMapping("/users")
public class UserRestApi {

    @Autowired
    UserDAOService userDAOService;



    /**
     *
     * @return all users
     */
    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userDAOService.getAll(), HttpStatus.OK);
    }

    /**
     *
     * @param user
     * @return
     */
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HashMap<String, Object>> addUser(@RequestBody User user) throws ParseException {
        System.out.println(user);
        HashMap<String, Object> response = new HashMap<>();
        User newUser = userDAOService.insert(user);

        if (newUser!=null) {
            response.put("status", "success");
            response.put("User", newUser);
        } else {
            response.put("status", "false");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}",  produces = "application/json")
    public ResponseEntity<HashMap<String, Object>> findUser(@PathVariable("id") int id) {
        HashMap<String, Object> response = new HashMap<>();
        if(userDAOService.findByID(id)!=null)
            response.put("User", userDAOService.findByID(id));
        else
            response.put("User", "not found!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     *
     * @param id
     * @param userDetail
     * @return
     */
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HashMap<String, Object>> update(@PathVariable(value = "id") int id,@RequestBody User userDetail) {
        HashMap<String, Object> response = new HashMap<>();
        User user = userDAOService.findByID(id);
        user.setFirstName(userDetail.getFirstName());
        user.setLastName(userDetail.getLastName());
        user.setLogin(userDetail.getLogin());
        user.setPwd(userDetail.getPwd());


        if (userDAOService.update(user, id)) {
            response.put("status", "success");
            response.put("admin", user);
        } else
            response.put("status", "error");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HashMap<String, Object>> deleteAdmin(@PathVariable("id") int id) {
        HashMap<String, Object> response = new HashMap<>();
        User u =userDAOService.findByID(id);
        if(u!=null) {
            if(userDAOService.delete(u))
                response.put("status", "success");
            else
                response.put("status", "error on delete");

        }else{
            response.put("status", "error not found");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
