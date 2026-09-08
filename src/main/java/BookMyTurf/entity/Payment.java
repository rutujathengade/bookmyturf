package BookMyTurf.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "payments",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_payment_booking",
            columnNames = "booking_id"
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    private String transactionId;

    private Double amount;

    private String paymentMethod;

    private String paymentStatus;

    private LocalDateTime paymentDate;

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }


    // Booking Getter and Setter
    public Booking getBooking() 
    {
        return booking;
    }

    public void setBooking(Booking booking) 
    {
        this.booking = booking;
    }


    public String getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount) 
    {
        this.amount = amount;
    }


    public String getPaymentMethod() 
    {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    // Payment Status Getter and Setter
    public String getPaymentStatus() 
    {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) 
    {
        this.paymentStatus = paymentStatus;
    }


    public LocalDateTime getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) 
    {
        this.paymentDate = paymentDate;
    }
}