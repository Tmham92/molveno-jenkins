package com.capgemini.molveno.repository;

import com.capgemini.molveno.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Override
    <S extends Booking> Iterable<S> saveAll(Iterable<S> iterable);
}
