package com.capgemini.molveno.initializer;

import com.capgemini.molveno.model.Booking;
import com.capgemini.molveno.model.Room;
import com.capgemini.molveno.model.RoomType;
import com.capgemini.molveno.model.user.Employee;
import com.capgemini.molveno.model.user.Guest;
import com.capgemini.molveno.model.user.NotBookingGuest;
import com.capgemini.molveno.service.BookingService;
import com.capgemini.molveno.service.RoomService;
import com.capgemini.molveno.service.UserService;
import com.capgemini.molveno.utility.FormDateParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Component
public class DbInitializer implements CommandLineRunner {

    private final BookingService bookingService;
    private final RoomService roomService;
    private final UserService userService;

    public DbInitializer(BookingService bookingService, RoomService roomService, UserService userService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {



        Guest guest1 = new Guest();
        guest1.setFirstName("Tobias");
        guest1.setLastName("Ham");
        String date = "2021-02-12";
        guest1.setDateOfBirth(FormDateParser.convertDateStringToDate(date));
        guest1.setEmail("tobias@gmail.com");
        guest1.setPassword(encodePassword("ham"));
        guest1.setCreationDate(LocalDateTime.now());

        Guest guest2 = new Guest();
        guest2.setFirstName("Marijn");
        guest2.setLastName("Ham");
        guest2.setEmail("marinus123@123.nl");
        guest2.setPassword(encodePassword("Bouter"));
        guest2.setCreationDate(LocalDateTime.now());

        Guest guest3 = new Guest();
        guest3.setFirstName("Sebastiaan");
        guest3.setLastName("Martinez");
        guest3.setEmail("Sebas.Mart@gmail.com");
        guest3.setPassword(encodePassword("Zeventien"));
        guest3.setCreationDate(LocalDateTime.now());

        Employee employee1 = new Employee();
        employee1.setFirstName("Henk");
        employee1.setLastName("de Potvis");
        employee1.setDateOfBirth(FormDateParser.convertDateStringToDate(date));
        employee1.setEmail("pzkttlvw@sharklasers.com");
        employee1.setPassword(encodePassword("henk"));
        userService.save(employee1);

        System.out.println("Number of guests: " + userService.count());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        Booking booking1 = new Booking();
        Set<NotBookingGuest> notBookingGuests = new HashSet<>();

        booking1.setNonBookingGuests(notBookingGuests);
        booking1.setBeginDate(LocalDate.parse("08/02/2021", formatter));
        booking1.setEndDate(LocalDate.parse("14/02/2021", formatter));
        booking1.setDateOfCreation(LocalDateTime.now());
        booking1.setNumberOfGuests(2);

        guest1.getBookings().add(booking1);
        booking1.setGuest(guest1);
        userService.save(guest1);
        bookingService.save(booking1);


        Set<NotBookingGuest> notBookingGuests2 = new HashSet<>();
        Booking booking2 = new Booking();
        booking2.setNonBookingGuests(notBookingGuests2);
        booking2.setBeginDate(LocalDate.parse("01/02/2021", formatter));
        booking2.setEndDate(LocalDate.parse("03/02/2021", formatter));
        booking2.setDateOfCreation(LocalDateTime.now());
        booking2.setNumberOfGuests(3);


        guest2.getBookings().add(booking2);
        booking2.setGuest(guest2);
        userService.save(guest2);
        bookingService.save(booking2);

        Booking booking3 = new Booking();
        booking3.setGuest(guest2);
        booking3.setNonBookingGuests(notBookingGuests);
        booking3.setBeginDate(LocalDate.parse("05/02/2021", formatter));
        booking3.setEndDate(LocalDate.parse("15/02/2021", formatter));
        booking3.setDateOfCreation(LocalDateTime.now());
        booking3.setNumberOfGuests(3);

        guest3.getBookings().add(booking3);
        booking3.setGuest(guest3);
        userService.save(guest3);
        bookingService.save(booking3);

        System.out.println("Number of bookings: " + bookingService.count());


        for (Room room : createSingleRooms()) {
            room.setId(roomService.count() + 1);
            roomService.save(room);
        }
        for (Room room : createDoubleRooms()) {
            room.setId(roomService.count() + 1);
            roomService.save(room);
        }


        System.out.println("Number of rooms: "+ roomService.count());

    }

    public String encodePassword(String password) {
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.encode(password);
    }

    public Set<Room> createSingleRooms() {
        Set<Room> createdRooms = new HashSet<>();
        for (int floor = 1; floor <= 4; floor++) {
            for (int roomNumber = 1; roomNumber <= 9; roomNumber++) {
                if (roomNumber <= 3) {
                    if (floor != 4) {
                        BigDecimal price = new BigDecimal(100);
                        createdRooms.add(new Room(floor, RoomType.SINGLE, false, false, 1, 0, price));
                    } else {
                        BigDecimal price = new BigDecimal(125);
                        createdRooms.add(new Room(floor, RoomType.SINGLE, true, false, 1, 0, price));
                    }
                } else if (roomNumber <= 7) {
                    if (floor != 4) {
                        BigDecimal price = new BigDecimal(150);
                        createdRooms.add(new Room(floor, RoomType.SINGLE, false, false, 1, 1, price));
                    } else {
                        BigDecimal price = new BigDecimal(175);
                        createdRooms.add(new Room(floor, RoomType.SINGLE, true, false, 1, 1, price));
                    }
                } else {
                    if (floor != 4) {
                        BigDecimal price = new BigDecimal(100);
                        createdRooms.add(new Room(floor, RoomType.SINGLE, false, true, 1, 0, price));
                    } else {
                        BigDecimal price = new BigDecimal(125);
                        createdRooms.add(new Room(floor, RoomType.SINGLE, true, true, 1, 0, price));
                    }
                }
            }
        }
        return createdRooms;
    }

    public Set<Room> createDoubleRooms() {
        Set<Room> createdRooms = new HashSet<>();
        for (int floor = 1; floor <= 4; floor++) {
            for (int roomNumber = 1; roomNumber <= 10; roomNumber++) {
                if (roomNumber == 1) {
                    if (floor != 4) {
                        // DOUBLE DISABLED NON SMOKING
                        BigDecimal price = new BigDecimal(200);
                        createdRooms.add(new Room(floor, RoomType.DOUBLE, false, true, 2, 0, price));
                    } else {
                        // DOUBLE DISABLED SMOKING
                        BigDecimal price = new BigDecimal(225);
                        createdRooms.add(new Room(floor, RoomType.DOUBLE, true, true, 2, 0, price));
                    }
                } else if (roomNumber <= 3) {
                    if (floor != 4) {
                        // DOUBLE NON SMOKING
                        BigDecimal price = new BigDecimal(200);
                        createdRooms.add(new Room(floor, RoomType.DOUBLE, false, false, 2, 0, price));
                    } else {
                        // DOUBLE SMOKING
                        BigDecimal price = new BigDecimal(225);
                        createdRooms.add(new Room(floor, RoomType.DOUBLE, true, false, 2, 0, price));
                    }
                } else if (roomNumber <= 5) {
                    if (floor != 4) {
                        // DOUBLE SINGLE NON SMOKING
                        BigDecimal price = new BigDecimal(200);
                        createdRooms.add(new Room(floor, RoomType.DOUBLE_SINGLES, false, true, 2, 0, price));
                    } else {
                        // DOUBLE SINGLE SMOKING
                        BigDecimal price = new BigDecimal(225);
                        createdRooms.add(new Room(floor, RoomType.DOUBLE_SINGLES, true, true, 2, 0, price));
                    }
                } else if (roomNumber <= 7) {
                    if (floor != 4) {
                        // DOUBLE ONE CHILD
                        BigDecimal price = new BigDecimal(250);
                        createdRooms.add(new Room(floor, RoomType.DOUBLE, false, true, 2, 1, price));
                    } else {
                        // DOUBLE ONE CHILD SMOKING
                        BigDecimal price = new BigDecimal(275);
                        createdRooms.add(new Room(floor, RoomType.DOUBLE, true, true, 2, 1, price));
                    }
                } else if (roomNumber == 9 || roomNumber == 8) {
                    if (floor != 4) {
                        // DOUBLE TWO CHILDREN
                        BigDecimal price = new BigDecimal(300);
                        createdRooms.add(new Room(floor, RoomType.DOUBLE, false, true, 2, 2, price));
                    } else {
                        // PENTHOUSE
                        BigDecimal price = new BigDecimal(750);
                        createdRooms.add(new Room(floor, RoomType.PENTHOUSE, true, true, 8, 2, price));
                    }
                }  else {
                    if (floor != 4) {
                        // FAMILY
                        BigDecimal price = new BigDecimal(450);
                        createdRooms.add(new Room(floor, RoomType.FAMILY, false, true, 4, 2, price));
                    } else {
                        BigDecimal price = new BigDecimal(750);
                        createdRooms.add(new Room(floor, RoomType.PENTHOUSE, true, true, 8, 2, price));
                    }
                }
            }
        }
        return createdRooms;
    }
}
