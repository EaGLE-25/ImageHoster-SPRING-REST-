package com.upgrad.technical.api.controller;


import com.upgrad.technical.api.model.AuthorizedUserResponse;
import com.upgrad.technical.service.business.AuthenticationService;
import com.upgrad.technical.service.entity.UserAuthTokenEntity;
import com.upgrad.technical.service.entity.UserEntity;
import com.upgrad.technical.service.exception.AuthenticationFailedException;
import com.upgrad.technical.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Base64;
import java.util.UUID;

@Controller
@RequestMapping("/")
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;


    @RequestMapping(value="/auth/login",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorizedUserResponse> login(@RequestHeader("authorization") String header) throws ResourceNotFoundException, AuthenticationFailedException {
        byte[] decoded = Base64.getDecoder().decode(header.split("Basic ")[1]);
        String decodedString = new String(decoded);

        String[] userNamePassword = decodedString.split(":");
        String email = userNamePassword[0];
        String password = userNamePassword[1];

        UserAuthTokenEntity jwtToken = authenticationService.login(email,password);
        UserEntity user = jwtToken.getUser();

        AuthorizedUserResponse response = new AuthorizedUserResponse();
        response.id(UUID.fromString(user.getUuid()));
        response.emailAddress(user.getEmail());
        response.firstName(user.getFirstName());
        response.lastName(user.getLastName());
        response.setMobilePhone(user.getMobilePhone());

        HttpHeaders headers = new HttpHeaders();
        headers.add("access-token",jwtToken.getAccessToken());

        return new ResponseEntity<AuthorizedUserResponse>(response,headers,HttpStatus.OK);
    }
}
