package school.hei.springboothotel.service;

import org.springframework.stereotype.Service;
import school.hei.springboothotel.model.Booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    private final List<Booking> bookingList = new ArrayList<>();

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public boolean isRoomAlreadyBooked(int roomNumber, LocalDate date) {
        return bookingList.stream()
                .anyMatch(b -> b.getRoomNumber() == roomNumber && b.getReservationDate().equals(date));
    }

    public Booking addBooking(Booking booking) {
        bookingList.add(booking);
        return booking;
    }
}
