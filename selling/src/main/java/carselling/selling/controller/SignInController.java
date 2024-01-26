package carselling.selling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import carselling.selling.entity.User;
import carselling.selling.repository.UserRepository;
import carselling.selling.response.ApiResponse;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/signin")
public class SignInController {
    @Autowired
    private UserRepository usersRepository;

    @PutMapping
    public @ResponseBody ApiResponse signin(@RequestBody User user){
        ApiResponse response = new ApiResponse();
        try {
            user = usersRepository.save(user);
            response.addData("user", usersRepository.save(user));
            response.addData("message", "Insert succesfully");
        } catch (Exception e) {
            // TODO: handle exception
            response.addData("error", e.getMessage());
            return response;
        }
        return response;
    }

}
