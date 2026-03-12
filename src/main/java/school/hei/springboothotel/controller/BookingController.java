package school.hei.springboothotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.springboothotel.model.Booking;
import school.hei.springboothotel.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getALlBookings() {
        return ResponseEntity.ok(bookingService.getBookingList());
    }

    @PostMapping
    public ResponseEntity<?> addBooking(@RequestBody Booking booking) {
        if(booking.getRoomNumber() < 1 || booking.getRoomNumber() > 9){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erreur : les numéros de chambre sont compris entre 1 et 9");
        }

        if(bookingService.isRoomAlreadyBooked(booking.getRoomNumber(), booking.getReservationDate())){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("La chambre " + booking.getRoomNumber() + " est ddéjà pris");
        }
        bookingService.addBooking(booking);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookingService.getBookingList());
    }
}
