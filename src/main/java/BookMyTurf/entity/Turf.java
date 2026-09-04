package BookMyTurf.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "turfs")
public class Turf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    private String name;

    private String description;

    private String address;

    private String city;
    
    private Double latitude;
    
    private Double longitude;

    private String status;
    
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    


    	@PrePersist
    	protected void onCreate() {
    	    createdAt = LocalDateTime.now();
    	    updatedAt = LocalDateTime.now();
    	}

    	@PreUpdate
    	protected void onUpdate() {
    	    updatedAt = LocalDateTime.now();
    	}
}