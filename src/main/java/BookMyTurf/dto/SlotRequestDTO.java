package BookMyTurf.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class SlotRequestDTO 
{
	@NotNull
	private Long turfId;
	
	@NotNull
	private LocalDate slotDate;
	
	@NotNull
	private LocalTime startTime;
	
	@NotNull
	private LocalTime endTime;
	
	@NotNull
	@DecimalMin(value = "0.0", message = "Price cannot be negative")
	private Double price;
	
	public Long getTurfId()
	{
		return turfId;
	}

	public void setTurfId(Long turfId) 
	{
		this.turfId = turfId;
	}

	public LocalDate getSlotDate() 
	{
		return slotDate;
	}

	public void setSlotDate(LocalDate slotDate) 
	{
		this.slotDate = slotDate;
	}

	public LocalTime getStartTime() 
	{
		return startTime;
	}

	public void setStartTime(LocalTime starTime) 
	{
		this.startTime = starTime;
	}

	public LocalTime getEndTime() 
	{
		return endTime;
	}

	public void setEndTime(LocalTime endTime) 
	{
		this.endTime = endTime;
	}

	public Double getPrice() 
	{
		return price;
	}

	public void setPrice(Double price)
	{
		this.price = price;
	}	
	
}
