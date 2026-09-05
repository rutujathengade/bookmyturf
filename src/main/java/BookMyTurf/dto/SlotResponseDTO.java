package BookMyTurf.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class SlotResponseDTO 
{
	private Long id;
	private Long turfId;
	private LocalDate slotDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private Double price;
	private String status;
	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

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

	
	public void setStartTime(LocalTime startTime) 
	{
		this.startTime = startTime;
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

	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		this.status = status;
	}

}
