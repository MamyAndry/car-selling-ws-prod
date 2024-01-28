package carselling.selling.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import carselling.selling.entity.User;
import carselling.selling.response.ApiResponse;
import carselling.selling.security.token.JwtUtils;
import carselling.selling.service.LoginService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping
    public @ResponseBody  ResponseEntity<ApiResponse> login(@RequestBody User user) {
        ApiResponse response = loginService.login(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin")
    public @ResponseBody  ResponseEntity<ApiResponse> loginAdmin(@RequestBody User user) {
        ApiResponse response = loginService.loginAdmin(user);
        return ResponseEntity.ok(response);
    }

    // @GetMapping
    // public @ResponseBody ResponseEntity<ApiResponse> test(@RequestHeader String authorization) {
    //     ApiResponse response = new ApiResponse();
    //     try {
    //         response.addData("token", jwtUtils.verify(jwtUtils.parseToken(authorization)));
    //     } catch (Exception e) {
    //         // TODO Auto-generated catch block
    //         response.addError("error", e.getMessage());
    //         response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
    //         return ResponseEntity.status(response.getStatus()).body(response);
    //     }
    //     return ResponseEntity.ok().body(response);
    // }

}
