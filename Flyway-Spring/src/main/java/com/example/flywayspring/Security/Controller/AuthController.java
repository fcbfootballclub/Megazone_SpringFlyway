package com.example.flywayspring.Security.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.flywayspring.Security.Entity.*;
import com.example.flywayspring.Security.Repository.RolesRepository;
import com.example.flywayspring.Security.Repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "api/auth")
@CrossOrigin
@Slf4j
public class AuthController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        if (usersRepository.existsByEmail(signUpDto.getEmail()) || usersRepository.existsByUserName(signUpDto.getUserName())) {
            return ResponseEntity.badRequest().body("Already taken user name or email!");
        }
        Optional<Roles> role = rolesRepository.findRolesByRoleTitle(RoleTitle.user);
        System.out.println("role here " + role);
        Users users = new Users()
                .setUserName(signUpDto.getUserName())
                .setEmail(signUpDto.getEmail())
                .setPassword(passwordEncoder.encode(signUpDto.getPassword()))
                .setRolesCollection(List.of(role.get()))
                .setActive(true);
        usersRepository.save(users);
        return ResponseEntity.ok(users);
    }

    @PostMapping(path = "/signin")
    public void signIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userNameOrEmail;
        String password;
        Map<String, String> requestMap = null;
        try {
            requestMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            userNameOrEmail = requestMap.get("userNameOrEmail");
            password = requestMap.get("password");
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

        log.info("User name is {}", userNameOrEmail);
        log.info("Password is {}", password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userNameOrEmail, password);
        Authentication newAuth = authenticationManager.authenticate(authenticationToken);
        User user = (User) newAuth.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_tocken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.getRequestURI().toString())
                .withClaim("roles", user.getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).sign(algorithm);

        String refresh_tocken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.getRequestURI().toString()).sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_tocken", access_tocken);
        tokens.put("refresh_tocken", refresh_tocken);

        tokens.put("username", user.getUsername());
        tokens.put("role", (user.getAuthorities()).toString());

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

}
