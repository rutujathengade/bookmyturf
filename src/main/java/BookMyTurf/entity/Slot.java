package BookMyTurf.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.UniqueConstraint;
@Entity
@Table(
	    name = "slots",
	    uniqueConstraints = 
	{
	        @UniqueConstraint(
	      name = "uk_slot_unique",
	            columnNames = {
	                "turf_id",
	                "slot_date",
	                "start_time",
	              "end_time"
	            }
	        )
	    }
	)
@Getter
@Setter
@NoArgsConstructor
public class Slot 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "turf_id", nullable = false)
    private Turf turf;

    private LocalDate slotDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private Double price;

    private String status;
    
    public static final String AVAILABLE = "AVAILABLE";
    public static final String BLOCKED = "BLOCKED";
    public static final String BOOKED = "BOOKED";
    
    
    public Long getId() {
        return id;
    }

    public Turf getTurf() {
        return turf;
    }

    public void setTurf(Turf turf) {
        this.turf = turf;
    }

    public LocalDate getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(LocalDate slotDate) {
        this.slotDate = slotDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}