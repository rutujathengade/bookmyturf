package BookMyTurf.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingResponseDTO 
{
	private Long id;
	private Long customerId;
	private Long turfId;
	private Long slotId;
	private LocalDate bookingDate;
	private Double amount;
	private String status;
	private String bookingCode;
	private LocalDateTime createdAt;
	
	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(Long customerId) 
	{
		this.customerId = customerId;
	}
	
	public Long getTurfId()
	{
		return turfId;
	}
	
	public void setTurfId(Long turfId)
	{
		this.turfId = turfId;
	}

	public Long getSlotId()
	{
		return slotId;
	}
	
	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}
	/**
	 * @return the bookingDate
	 */
	public LocalDate getBookingDate() 
	{
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
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
