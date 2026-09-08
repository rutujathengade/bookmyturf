package BookMyTurf.dto;
import java.time.LocalDateTime;
public class PaymentResponseDTO 
{
	private Long id;
	private Long bookingId;
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
	
	public Long getBookingId()
	{
		return bookingId;
	}
	
	public void setBookingId(Long bookingId)
	{
		this.bookingId = bookingId;
	}
	
	public String getTransactionId()
	{
		return transactionId;
	}

	public void setTransactionId(String transactionId) 
	{
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
	
	public void setPaymentMethod(String paymentMethod) 
	{
		this.paymentMethod = paymentMethod;
	}
	
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
