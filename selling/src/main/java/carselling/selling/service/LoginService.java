package carselling.selling.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
            user = userRepository.findByEmail(user.getEmail());
            if (user == null) {
                response.addError("email", "This account doesn't exist.");
                return response;
            }
            user.checkPassWord(password);
            user.setPassword(jwtUtils.generateJwt(loadUserByUsername(user.getEmail())));
            response.addData("data", user);
        }catch(UserException e){
            response.addError("error", e.getMessage());
            return response;
        }
        catch (Exception e) {
            // TODO: handle exception
            response.addData("error", e.getMessage());
            return response;
        }
        return response;
    }

    public ApiResponse loginAdmin( User user ) {
        ApiResponse response = new ApiResponse();
        String password = user.getPassword();
        try {
            user = userRepository.findByEmail(user.getEmail());
            if (user == null) {
                response.addError("error", "This account doesn't exist.");
                return response;
            }
            if (!user.isAdmin()) {
                response.addError("admin", "This account is not an admin.");
                return response;
            }
            user.checkPassWord(password);
            user.setPassword(jwtUtils.generateJwt(loadUserByUsername(user.getEmail())));
            response.addData("data", user);
        }catch(UserException e){
            response.addError("error", e.getMessage());
            return response;
        }
        catch (Exception e) {
            // TODO: handle exception
            response.addData("error", e.getMessage());
            return response;
        }
        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.findByEmail(email);
        System.out.println("HOAOAOOAOAOAO");
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user == null) {
            throw new UsernameNotFoundException("Check your mail");
        }
        if(user.isAdmin()){
            System.out.println("HUHUHHU");
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        else{
            System.out.println("HAHAHAHHA");
            authorities.add(new SimpleGrantedAuthority("USER"));
        }
        System.out.println(authorities.size() + " " + authorities.get(0));
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .authorities(authorities)
            .build();
    }
}
