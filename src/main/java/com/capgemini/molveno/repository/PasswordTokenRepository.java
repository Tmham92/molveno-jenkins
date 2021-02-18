package com.capgemini.molveno.repository;

import com.capgemini.molveno.model.PasswordToken;
import com.capgemini.molveno.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordTokenRepository extends CrudRepository<PasswordToken, Long> {

    @Query("select t from PasswordToken t where t.token = :token")
    PasswordToken findPasswordTokenByToken(@Param("token") String token);
}
