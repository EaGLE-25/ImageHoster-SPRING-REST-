package com.upgrad.technical.service.business;

import com.upgrad.technical.service.commons.JwtTokenProvider;
import com.upgrad.technical.service.dao.UserDao;
import com.upgrad.technical.service.entity.UserAuthTokenEntity;
import com.upgrad.technical.service.entity.UserEntity;
import com.upgrad.technical.service.exception.AuthenticationFailedException;
import com.upgrad.technical.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class AuthenticationService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider passwordCryptographyProvider;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserAuthTokenEntity login(String email,String password) throws ResourceNotFoundException, AuthenticationFailedException {
        UserEntity user = userDao.getUserByEmail(email);

        if(user == null){
            throw  new ResourceNotFoundException("USR-000","User with email not found");
        }

        String encryptedPassword = passwordCryptographyProvider.encrypt(password,user.getSalt());

        if(encryptedPassword.equals(user.getPassword())){
            ZonedDateTime now = ZonedDateTime.now();
            ZonedDateTime expiresAt = now.plusHours(8);

            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(encryptedPassword);
            String accessToken = jwtTokenProvider.generateToken(user.getUuid(),now,expiresAt);

            UserAuthTokenEntity jwtToken = new UserAuthTokenEntity();

            jwtToken.setUser(user);
            jwtToken.setAccessToken(accessToken);
            jwtToken.setLoginAt(now);
            jwtToken.setExpiresAt(expiresAt);

            return userDao.persistJwt(jwtToken);
        }else{
            throw new AuthenticationFailedException("ATH-000","Wrong Password");
        }
    }
}
