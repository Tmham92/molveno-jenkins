package com.capgemini.molveno.repository;

import com.capgemini.molveno.model.Room;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>, JpaSpecificationExecutor<Room> {
}
