package com.upgrad.technical.service.business;

import com.upgrad.technical.service.dao.ImageDao;
import com.upgrad.technical.service.dao.UserDao;
import com.upgrad.technical.service.entity.ImageEntity;
import com.upgrad.technical.service.entity.UserAuthTokenEntity;
import com.upgrad.technical.service.entity.UserEntity;
import com.upgrad.technical.service.exception.UploadFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class ImageUploadService {

    @Autowired
    private ImageDao imageDao;
    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ImageEntity upload(ImageEntity imageEntity,String accessToken) throws UploadFailedException {
        //call createImage() method for imageDao and pass imageEntity as an argument
        //Note that createImage() method returns the created image of type ImageEntity
        //Also note that upload() method returns the value returned by createImage() method
        //Write code here//
        UserAuthTokenEntity foundAccessToken = userDao.getUserAuthToken(accessToken);
        if(foundAccessToken != null){
            return imageDao.createImage(imageEntity);
        }else{
            throw new UploadFailedException("USR-001","Sign in first");
        }
    }
}
