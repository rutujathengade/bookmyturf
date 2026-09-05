package BookMyTurf.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import BookMyTurf.entity.Slot;


public interface SlotRepository extends JpaRepository<Slot, Long>
{
	boolean existsByTurfIdAndSlotDateAndStartTimeAndEndTime(
	        Long turfId,
	        LocalDate slotDate,
	        LocalTime startTime,
	        LocalTime endTime
	);

	boolean existsByTurfIdAndSlotDateAndStartTimeAndEndTimeAndIdNot(
	        Long turfId,
	        LocalDate slotDate,
	        LocalTime startTime,
	        LocalTime endTime,
	        Long id
	);
}