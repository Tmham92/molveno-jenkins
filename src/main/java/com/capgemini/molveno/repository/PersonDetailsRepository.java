package com.capgemini.molveno.repository;

import com.capgemini.molveno.model.details.PersonDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDetailsRepository extends CrudRepository<PersonDetails, Long> {

}
