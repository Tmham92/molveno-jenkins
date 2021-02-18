package com.capgemini.molveno.service;

import com.capgemini.molveno.model.Room;
import com.capgemini.molveno.model.formmodels.BookRoomsFilter;
import com.capgemini.molveno.model.formmodels.RoomFilter;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

public interface IRoomService {

    void save(Room room);

    void saveRoom(Room room);

    Page<Room> getPageForProperty(String property, int roomAmount);

    List<Room> findAll();

    Iterable<Room> filter(BookRoomsFilter filter, RoomFilter detailsFilter);

    long count();

    Optional<Room> findRoomById(Long Id);
}
