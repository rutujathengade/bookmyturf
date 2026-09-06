package BookMyTurf.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BookMyTurf.dto.BookingRequestDTO;
import BookMyTurf.dto.BookingResponseDTO;
import BookMyTurf.entity.Booking;
import BookMyTurf.entity.Slot;
import BookMyTurf.entity.Turf;
import BookMyTurf.entity.User;
import BookMyTurf.repository.SlotRepository;
import BookMyTurf.repository.TurfRepository;
import BookMyTurf.repository.UserRepository;
import BookMyTurf.service.BookingService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserRepository userRepository;
    private final TurfRepository turfRepository;
    private final SlotRepository slotRepository;

    public BookingController(
            BookingService bookingService,
            UserRepository userRepository,
            TurfRepository turfRepository,
            SlotRepository slotRepository) {

        this.bookingService = bookingService;
        this.userRepository = userRepository;
        this.turfRepository = turfRepository;
        this.slotRepository = slotRepository;
    }

    // Create Booking
    @PostMapping
    public BookingResponseDTO createBooking(
            @Valid @RequestBody BookingRequestDTO request) {

        User customer = userRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Turf turf = turfRepository.findById(request.getTurfId())
                .orElseThrow(() -> new RuntimeException("Turf not found"));

        Slot slot = slotRepository.findById(request.getSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        Booking booking = new Booking();

        booking.setCustomer(customer);
        booking.setTurf(turf);
        booking.setSlot(slot);
        booking.setBookingDate(request.getBookingDate());
        booking.setAmount(slot.getPrice());

        Booking savedBooking = bookingService.saveBooking(booking);

        BookingResponseDTO response = new BookingResponseDTO();

        response.setId(savedBooking.getId());
        response.setCustomerId(savedBooking.getCustomer().getId());
        response.setTurfId(savedBooking.getTurf().getId());
        response.setSlotId(savedBooking.getSlot().getId());
        response.setBookingDate(savedBooking.getBookingDate());
        response.setAmount(savedBooking.getAmount());
        response.setStatus(savedBooking.getStatus());
        response.setBookingCode(savedBooking.getBookingCode());
        response.setCreatedAt(savedBooking.getCreatedAt());

        return response;
    }

    // Get All Bookings
    @GetMapping
    public List<BookingResponseDTO> getAllBookings() {

        List<Booking> bookings = bookingService.getAllBookings();

        return bookings.stream().map(booking -> {

            BookingResponseDTO response = new BookingResponseDTO();

            response.setId(booking.getId());
            response.setCustomerId(booking.getCustomer().getId());
            response.setTurfId(booking.getTurf().getId());
            response.setSlotId(booking.getSlot().getId());
            response.setBookingDate(booking.getBookingDate());
            response.setAmount(booking.getAmount());
            response.setStatus(booking.getStatus());
            response.setBookingCode(booking.getBookingCode());
            response.setCreatedAt(booking.getCreatedAt());

            return response;

        }).toList();
    }

    // Get Booking By ID
    @GetMapping("/{id}")
    public BookingResponseDTO getBookingById(@PathVariable Long id) {

        Booking booking = bookingService.getBookingById(id);

        BookingResponseDTO response = new BookingResponseDTO();

        response.setId(booking.getId());
        response.setCustomerId(booking.getCustomer().getId());
        response.setTurfId(booking.getTurf().getId());
        response.setSlotId(booking.getSlot().getId());
        response.setBookingDate(booking.getBookingDate());
        response.setAmount(booking.getAmount());
        response.setStatus(booking.getStatus());
        response.setBookingCode(booking.getBookingCode());
        response.setCreatedAt(booking.getCreatedAt());

        return response;
    }
    
    @PutMapping("/{id}/cancel")
    public BookingResponseDTO cancelBooking(@PathVariable Long id) {

        Booking booking = bookingService.cancelBooking(id);

        BookingResponseDTO response = new BookingResponseDTO();

        response.setId(booking.getId());
        response.setCustomerId(booking.getCustomer().getId());
        response.setTurfId(booking.getTurf().getId());
        response.setSlotId(booking.getSlot().getId());
        response.setBookingDate(booking.getBookingDate());
        response.setAmount(booking.getAmount());
        response.setStatus(booking.getStatus());
        response.setBookingCode(booking.getBookingCode());
        response.setCreatedAt(booking.getCreatedAt());

        return response;
    }
}