package BookMyTurf.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class BookingRequestDTO
{

    @NotNull
    private Long customerId;

    @NotNull
    private Long turfId;

    @NotNull
    private Long slotId;

    @NotNull
    private LocalDate bookingDate;

    public Long getCustomerId() 
    {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getTurfId()
    {
        return turfId;
    }

    public void setTurfId(Long turfId) {
        this.turfId = turfId;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) 
    {
        this.slotId = slotId;
    }

    public LocalDate getBookingDate() 
    {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) 
    {
        this.bookingDate = bookingDate;
    }
}
