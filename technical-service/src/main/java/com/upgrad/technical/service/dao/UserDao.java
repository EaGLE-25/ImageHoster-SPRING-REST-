package com.upgrad.technical.service.dao;

import com.upgrad.technical.service.entity.UserAuthTokenEntity;
import com.upgrad.technical.service.entity.UserEntity;
import com.upgrad.technical.service.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity createUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;
    }

    public UserEntity getUserByEmail(String email){
        try {
            return entityManager.createNamedQuery("userByEmail",UserEntity.class).setParameter("email",email).getSingleResult();
        }
        catch (NoResultException nre){
            return null;
        }
    }

    public UserAuthTokenEntity persistJwt(UserAuthTokenEntity jwtToken){
        entityManager.persist(jwtToken);
        return jwtToken;
    }


    public UserAuthTokenEntity getUserAuthToken(String accessToken){
        try {
            return entityManager.createNamedQuery("userAuthTokenByAccessToken",UserAuthTokenEntity.class)
                    .setParameter("accessToken",accessToken).getSingleResult();
        }
        catch (NoResultException nre){
            return null;
        }
    }
}
