package BookMyTurf.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import BookMyTurf.entity.Booking;
import BookMyTurf.entity.Slot;
import BookMyTurf.exception.BookingAlreadyCancelledException;
import BookMyTurf.exception.BookingDateMismatchException;
import BookMyTurf.exception.SlotNotAvailableException;
import BookMyTurf.exception.SlotTurfMismatchException;
import BookMyTurf.repository.BookingRepository;
import BookMyTurf.repository.SlotRepository;

@Service
public class BookingService
{

    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepository;

    public BookingService(
            BookingRepository bookingRepository,
            SlotRepository slotRepository) 
    {

        this.bookingRepository = bookingRepository;
        this.slotRepository = slotRepository;
    }

    @Transactional
    public Booking saveBooking(Booking booking) 
    {

        Slot slot = slotRepository
                .findByIdForUpdate(booking.getSlot().getId())
                .orElseThrow(() -> new RuntimeException("Slot not found"));


        if (!Slot.AVAILABLE.equals(slot.getStatus())) 
        {
            throw new SlotNotAvailableException("Slot is not available");
        }

        if (!slot.getSlotDate().equals(booking.getBookingDate())) 
        {
            throw new BookingDateMismatchException("Booking date does not match slot date");
        }

  
        if (!slot.getTurf().getId().equals(booking.getTurf().getId())) 
        {
            throw new SlotTurfMismatchException(
                    "Slot does not belong to selected turf");
        }

       
        booking.setSlot(slot);

        booking.setAmount(slot.getPrice());

        booking.setBookingCode(
                "BMT-" + UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase());

      
        booking.setStatus("PENDING");

    
        slot.setStatus(Slot.BOOKED);

        slotRepository.save(slot);

      
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() 
    {
        return bookingRepository.findAll();
    }
    
    public Booking getBookingById(Long id) 
    {

        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
    
    
    
    @Transactional
    public Booking cancelBooking(Long id)
    {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if ("CANCELLED".equals(booking.getStatus())) {
            throw new BookingAlreadyCancelledException("Booking is already cancelled");
        }

        Slot slot = slotRepository.findByIdForUpdate(booking.getSlot().getId())
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        booking.setStatus("CANCELLED");

        slot.setStatus(Slot.AVAILABLE);

        slotRepository.save(slot);

        return bookingRepository.save(booking);
    }
    
    
}