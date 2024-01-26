package carselling.selling.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import carselling.selling.entity.User;
import carselling.selling.exception.UserException;
import carselling.selling.repository.UserRepository;
import carselling.selling.response.ApiResponse;
import carselling.selling.security.token.JwtUtils;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;

    public ApiResponse login(User user) {
        ApiResponse response = new ApiResponse();
        String password = user.getPassword();
        try {
            user = userRepository.getUsersByEmail(user.getEmail());
            if (user == null) {
                response.addError("email", "This account doesn't exist.");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return response;
            }
            user.checkPassWord(password);
            response.addData("token", jwtUtils.generateJwt(user));
        }catch(UserException e){
            response.addError("error", e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return response;
        }
        catch (Exception e) {
            // TODO: handle exception
            response.addData("error", e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return response;
        }
        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.getUsersByEmail(email);
        List<String> roles = new ArrayList<>();
        if (user == null) {
            throw new UsernameNotFoundException("Check your mail");
        }
        roles.add("USER");
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getId())
            .password(user.getPassword())
            .roles(roles.toArray(new String[0]))
            .build();
    }
}
