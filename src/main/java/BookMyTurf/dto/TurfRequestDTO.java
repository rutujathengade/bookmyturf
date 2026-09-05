package BookMyTurf.dto;

public class TurfRequestDTO 
{
	private String name;
	private String description;
	private String address;
	private String city;
	private Double latitude;
	private Double longitude;
	private Long ownerId;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getCity() 
	{
		return city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public Double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	public Double getLongitude() 
	{
		return longitude;
	}

	public void setLongitude(Double longitude) 
	{
		this.longitude = longitude;
	}
	
	public Long getOwnerId() 
	{
		return ownerId;
	}

	public void setOwnerId(Long ownerId) 
	{
		this.ownerId = ownerId;
	}
}
