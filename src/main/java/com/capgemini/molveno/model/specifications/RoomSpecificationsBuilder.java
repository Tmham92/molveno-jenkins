package com.capgemini.molveno.model.specifications;

import com.capgemini.molveno.model.Room;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomSpecificationsBuilder {
    private final List<SearchCriteria> params;

    public RoomSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public RoomSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Room> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Room>> specs = params.stream()
                .map(RoomSpecification::new)
                .collect(Collectors.toList());

        Specification<Room> result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
