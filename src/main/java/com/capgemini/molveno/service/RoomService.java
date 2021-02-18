package com.capgemini.molveno.service;

import com.capgemini.molveno.model.Room;
import com.capgemini.molveno.model.formmodels.BookRoomsFilter;
import com.capgemini.molveno.model.formmodels.RoomFilter;
import com.capgemini.molveno.model.specifications.RoomSpecificationsBuilder;
import com.capgemini.molveno.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService implements IRoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void saveRoom(Room room) {
        roomRepository.save(room);
        System.out.println(room.toString());
    }

    public Page<Room> getPageForProperty(String property, int roomAmount) {
        Pageable pageable = PageRequest.of(0, roomAmount, Sort.Direction.DESC, property);
        return roomRepository.findAll(null, pageable);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll(null);
    }

    public Iterable<Room> filter(BookRoomsFilter filter, RoomFilter detailsFilter) {
        RoomSpecificationsBuilder builder = new RoomSpecificationsBuilder();

        if (detailsFilter.getRoomType() != null)
            builder.with("type", ":", detailsFilter.getRoomType());

        if (detailsFilter.getSmokingAllowed() != null)
            builder.with("smokingAllowed", ":", detailsFilter.getSmokingAllowed());

        if (detailsFilter.isForDisabled())
            builder.with("forDisabled", ":", detailsFilter.isForDisabled());

        builder.with("maxChildren", ">=", detailsFilter.getChildren());

        if (detailsFilter.getPriceRange() != null) {
            String[] range = detailsFilter.getPriceRange().split(",");
            builder.with("price", ">=", Double.parseDouble(range[0]));
            builder.with("price", "<=", Double.parseDouble(range[1]));
        }

        List<Room> rooms = roomRepository.findAll(builder.build());

        if (filter.getGuests() > 0)
            rooms = rooms.stream().filter(r -> r.getMaxAdults() + r.getMaxChildren() >= filter.getGuests()).collect(Collectors.toList());

        return rooms;
    }

    @Override
    public long count() {
        return roomRepository.count();
    }

    @Override
    public Optional<Room> findRoomById(Long Id){
        return roomRepository.findById(Id);
    }


}
