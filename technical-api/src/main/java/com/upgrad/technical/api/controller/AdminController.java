package com.upgrad.technical.api.controller;


import com.upgrad.technical.api.model.ImageDetailsResponse;
import com.upgrad.technical.service.business.AdminService;
import com.upgrad.technical.service.dao.ImageDao;
import com.upgrad.technical.service.entity.ImageEntity;
import com.upgrad.technical.service.exception.ResourceNotFoundException;
import com.upgrad.technical.service.exception.UnauthorizedException;
import com.upgrad.technical.service.exception.UploadFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/images/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageDetailsResponse> showImage(@PathVariable("id")String uuid, @RequestHeader("authorization") String header) throws ResourceNotFoundException, UploadFailedException, UnauthorizedException {
        adminService.checkAdmin(header.split("Bearer ")[1]);

        ImageEntity imageEntity = adminService.getImageById(uuid);

        ImageDetailsResponse response = new ImageDetailsResponse();
        response.description(imageEntity.getDescription());
        response.id(imageEntity.getUuid());
        response.name(imageEntity.getName());
        response.status(imageEntity.getStatus());
        response.image(imageEntity.getImage());

        return new ResponseEntity<ImageDetailsResponse>(response, HttpStatus.OK);
    }
}
