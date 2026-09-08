package BookMyTurf.service;

import java.time.LocalDateTime;
import java.util.UUID;
import BookMyTurf.exception.PaymentNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import BookMyTurf.entity.Booking;
import BookMyTurf.entity.Payment;
import BookMyTurf.entity.Slot;

import BookMyTurf.exception.InvalidPaymentStatusException;
import BookMyTurf.exception.PaymentAlreadyExistsException;
import BookMyTurf.exception.PaymentAlreadyVerifiedException;

import BookMyTurf.repository.BookingRepository;
import BookMyTurf.repository.PaymentRepository;
import BookMyTurf.repository.SlotRepository;

@Service
public class PaymentService 
{

    private final PaymentRepository paymentRepository;

    private final BookingRepository bookingRepository;


    private final SlotRepository slotRepository;


    public PaymentService(
            PaymentRepository paymentRepository,
            BookingRepository bookingRepository,
            SlotRepository slotRepository) 
    {

        this.paymentRepository = paymentRepository;

        this.bookingRepository = bookingRepository;

        this.slotRepository = slotRepository;
    }


    @Transactional
    public Payment createPayment(Long bookingId, String paymentMethod) 
    {

    	Booking booking = bookingRepository.findById(bookingId)
    	        .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (paymentRepository.existsByBookingId(bookingId)) 
        {

            throw new PaymentAlreadyExistsException(
                    "Payment already exists for this booking"
            );
        }


        if (!"PENDING".equals(booking.getStatus())) 
        {

            throw new InvalidPaymentStatusException(
                    "Payment can only be initiated for pending booking"
            );
        }


        Payment payment = new Payment();


        payment.setBooking(booking);

        payment.setAmount(booking.getAmount());

        payment.setPaymentMethod(paymentMethod);


        payment.setTransactionId(
                "TXN-" + UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase()
        );


        payment.setPaymentStatus("PENDING");

        payment.setPaymentDate(LocalDateTime.now());


        booking.setStatus("PAYMENT_PENDING");


        bookingRepository.save(booking);


        return paymentRepository.save(payment);
    }




    @Transactional
    public Payment verifyPayment(Long paymentId)
    {
    	Payment payment = paymentRepository.findById(paymentId)
    	        .orElseThrow(() ->
    	                new PaymentNotFoundException("Payment not found"));


        if ("SUCCESS".equals(payment.getPaymentStatus())) 
        {

            throw new PaymentAlreadyVerifiedException(
                    "Payment already verified"
            );
        }


        if (!"PENDING".equals(payment.getPaymentStatus())) 
        {

        	throw new InvalidPaymentStatusException(
        		    "Payment cannot be verified"
        		);
        }


        Booking booking = payment.getBooking();


        payment.setPaymentStatus("SUCCESS");


        booking.setStatus("CONFIRMED");


        bookingRepository.save(booking);


        return paymentRepository.save(payment);
    }



    @Transactional
    public Payment failPayment(Long paymentId)
    {

    	Payment payment = paymentRepository.findById(paymentId)
    	        .orElseThrow(() ->
    	                new PaymentNotFoundException("Payment not found"));

        if (!"PENDING".equals(payment.getPaymentStatus()))
        {

            throw new InvalidPaymentStatusException(
                    "Payment can only be failed when payment is pending"
            );
        }


        Booking booking = payment.getBooking();

      payment.setPaymentStatus("FAILED");


        booking.setStatus("CANCELLED");


        Slot slot = slotRepository.findByIdForUpdate(
                booking.getSlot().getId()
        )
        .orElseThrow(() ->
                new RuntimeException("Slot not found"));


        slot.setStatus(Slot.AVAILABLE);


        slotRepository.save(slot);

        bookingRepository.save(booking);

        return paymentRepository.save(payment);
    }

}

