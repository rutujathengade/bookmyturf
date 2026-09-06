package BookMyTurf.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "bookings",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_booking_code",
            columnNames = "booking_code"
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
public class Booking 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "turf_id", nullable = false)
    private Turf turf;

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private Slot slot;

    private LocalDate bookingDate;

    private Double amount;

    private String status;

    private String bookingCode;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() 
    {
        createdAt = LocalDateTime.now();
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() 
    {
        return customer;
    }

    public void setCustomer(User customer) 
    {
        this.customer = customer;
    }

    public Turf getTurf() 
    {
        return turf;
    }

    public void setTurf(Turf turf) 
    {
        this.turf = turf;
    }

    public Slot getSlot()
    {
        return slot;
    }

    public void setSlot(Slot slot) 
    {
        this.slot = slot;
    }

    public LocalDate getBookingDate() 
    {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) 
    {
        this.bookingDate = bookingDate;
    }

    public Double getAmount() 
    {
        return amount;
    }

    public void setAmount(Double amount) 
    {
        this.amount = amount;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getBookingCode() 
    {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode)
    {
        this.bookingCode = bookingCode;
    }

    public LocalDateTime getCreatedAt() 
    {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) 
    {
        this.createdAt = createdAt;
    }
}