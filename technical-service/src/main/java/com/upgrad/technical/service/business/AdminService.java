package com.upgrad.technical.service.business;


import com.upgrad.technical.service.dao.ImageDao;
import com.upgrad.technical.service.dao.UserDao;
import com.upgrad.technical.service.entity.ImageEntity;
import com.upgrad.technical.service.entity.UserAuthTokenEntity;
import com.upgrad.technical.service.entity.UserEntity;
import com.upgrad.technical.service.exception.ResourceNotFoundException;
import com.upgrad.technical.service.exception.UnauthorizedException;
import com.upgrad.technical.service.exception.UploadFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private UserDao userDao;

    public ImageEntity getImageById(String uuid) throws ResourceNotFoundException {
        ImageEntity imageEntity = imageDao.getImageById(uuid);

        if(imageEntity!=null){
            return imageEntity;
        }else{
            throw new ResourceNotFoundException("IMG-001","Image with uuid not found");
        }
    }

    public void checkAdmin(String accessToken) throws UploadFailedException, UnauthorizedException {
        UserAuthTokenEntity userAuthTokenEntity = userDao.getUserAuthToken(accessToken);

        if(userAuthTokenEntity == null){
            throw new UploadFailedException("USR-000","Sign in first");
        }
        UserEntity user = userAuthTokenEntity.getUser();

        if(!user.getRole().equals("admin")){
            throw new UnauthorizedException("USR-000","You are not a admin");
        }
    }
}
