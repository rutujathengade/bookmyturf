package BookMyTurf.service;

import org.springframework.stereotype.Service;
import BookMyTurf.exception.InvalidSlotDateException;
import java.util.List;
import BookMyTurf.exception.InvalidSlotTimeException;
import BookMyTurf.entity.Slot;
import BookMyTurf.exception.SlotAlreadyExistsException;
import BookMyTurf.exception.SlotNotFoundException;
import BookMyTurf.repository.SlotRepository;

@Service
public class SlotService 
{
	 private final SlotRepository slotRepository;

	    public SlotService(SlotRepository slotRepository) 
	    {
	        this.slotRepository = slotRepository;
  }
	    
	    public Slot saveSlot(Slot slot)
	    {
	    	
	    	if (slot.getStartTime().isAfter(slot.getEndTime())
	    	        || slot.getStartTime().equals(slot.getEndTime()))
	    	{
	    	    throw new InvalidSlotTimeException("Start time must be before end time");
	    	}
	    	
	    	if (slot.getSlotDate().isBefore(java.time.LocalDate.now())) 
	    	{
	    	    throw new InvalidSlotDateException("Slot date cannot be in the past");
	    	}
	    	
	    	  boolean exists = slotRepository
	    	            .existsByTurfIdAndSlotDateAndStartTimeAndEndTime
	    	            (
	    	                    slot.getTurf().getId(),
	    	                    slot.getSlotDate(),
	    	                    slot.getStartTime(),
	    	                    slot.getEndTime()
	    	            );

	    	    if (exists) 
	    	    {

	    	        throw new SlotAlreadyExistsException("Slot already exists");
	    	    }
	    	return slotRepository.save(slot);
	    }
	    
	    public List<Slot> getAllSlots()
	    {
	    	return slotRepository.findAll();
	    }
	    
	    public Slot updateSlot(Long id, Slot updatedSlot) 
	    {
	    	
	    	Slot existingSlot = slotRepository.findById(id)
	    	        .orElseThrow(() -> new RuntimeException("Slot not found"));
	    	
	    	
	    	 if (updatedSlot.getStartTime().isAfter(updatedSlot.getEndTime())
	    	            || updatedSlot.getStartTime().equals(updatedSlot.getEndTime())) 
	    	 {
	    	        throw new InvalidSlotTimeException("Start time must be before end time");
	    	    }
	    	 
	    	 if (updatedSlot.getSlotDate().isBefore(java.time.LocalDate.now())) 
	    	 {
	    		    throw new InvalidSlotDateException("Slot date cannot be in the past");
	    	}
	    	 
	    	 boolean exists = slotRepository
	    		        .existsByTurfIdAndSlotDateAndStartTimeAndEndTimeAndIdNot(
	    		                updatedSlot.getTurf().getId(),
	    		                updatedSlot.getSlotDate(),
	    		                updatedSlot.getStartTime(),
	    		                updatedSlot.getEndTime(),
	    		                id
	    		        );

	    		if (exists)
	    		{
	    		    throw new SlotAlreadyExistsException("Slot already exists");
	    		}
	    	 
	    	 
	    	existingSlot.setSlotDate(updatedSlot.getSlotDate());
	    	existingSlot.setStartTime(updatedSlot.getStartTime());
	    	existingSlot.setEndTime(updatedSlot.getEndTime());
	    	existingSlot.setPrice(updatedSlot.getPrice());
	    	existingSlot.setStatus(updatedSlot.getStatus());
	    	
	    	return slotRepository.save(existingSlot);
	    }
	    
	    public void deleteSlot(Long id)
	    {
	        if (!slotRepository.existsById(id))
	        {
	 
	            throw new SlotNotFoundException("Slot not found");
	        }

	        slotRepository.deleteById(id);
	    }
}
