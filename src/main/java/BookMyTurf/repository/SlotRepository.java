package BookMyTurf.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;

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

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Slot s WHERE s.id = :id")
    Optional<Slot> findByIdForUpdate(@Param("id") Long id);
}