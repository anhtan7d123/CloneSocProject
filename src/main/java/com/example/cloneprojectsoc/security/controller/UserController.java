package com.example.cloneprojectsoc.security.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.cloneprojectsoc.security.entity.Permission;
import com.example.cloneprojectsoc.security.entity.User;
import com.example.cloneprojectsoc.security.repository.PermissionRepository;
import com.example.cloneprojectsoc.security.repository.UserRepository;
import com.example.cloneprojectsoc.security.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;
    private final PermissionRepository permissionRepository;

    @GetMapping("/user/get-all")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/user/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User checkUser = userRepository.findByUsername(user.getUsername());
        if (checkUser != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            return ResponseEntity.created(null).body(userService.saveUser(user));
        }

    }

    @PostMapping("/permission/create-permission")
    public ResponseEntity<?> createPermission(@RequestBody Permission permission){
        Permission checkPermission = permissionRepository.findByPermissionName(permission.getPermissionName());
        if (checkPermission != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            return ResponseEntity.created(null).body(userService.savePermission(permission));
        }
    }

    @PostMapping("/permission/add-to-user")
    public ResponseEntity<?> addPermissionToUser(@RequestBody PermissionToUserForm form){
        userService.addPermissionToUser(form.getUsername(), form.getPermissionName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/token/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUserByUsername(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                        .withClaim("permissions", user.getPermissions().stream().map(Permission::getPermissionName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception e){
                response.setHeader("error", e.getMessage());
//                response.getStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        }else {
            throw new  RuntimeException("Refresh token is missing");
        }
    }

    @Data class PermissionToUserForm{
        private String username;
        private String permissionName;
    }
}
