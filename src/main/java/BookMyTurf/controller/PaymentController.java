package BookMyTurf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import BookMyTurf.dto.PaymentRequestDTO;
import BookMyTurf.dto.PaymentResponseDTO;
import BookMyTurf.entity.Payment;
import BookMyTurf.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponseDTO createPayment(
            @RequestBody PaymentRequestDTO request) {

        Payment payment = paymentService.createPayment(
                request.getBookingId(),
                request.getPaymentMethod()
        );

        return mapToResponse(payment);
    }

    @PostMapping("/{paymentId}/verify")
    public PaymentResponseDTO verifyPayment(
            @PathVariable Long paymentId) {

        Payment payment = paymentService.verifyPayment(paymentId);

        return mapToResponse(payment);
    }

    private PaymentResponseDTO mapToResponse(Payment payment) {

        PaymentResponseDTO response = new PaymentResponseDTO();

        response.setId(payment.getId());
        response.setBookingId(payment.getBooking().getId());
        response.setTransactionId(payment.getTransactionId());
        response.setAmount(payment.getAmount());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setPaymentStatus(payment.getPaymentStatus());
        response.setPaymentDate(payment.getPaymentDate());

        return response;
    }
    
    @PostMapping("/{paymentId}/fail")
    public PaymentResponseDTO failPayment(
            @PathVariable Long paymentId) {

        Payment payment = paymentService.failPayment(paymentId);

        return mapToResponse(payment);
    }
}