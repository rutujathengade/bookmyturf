package BookMyTurf.service;

import org.springframework.stereotype.Service;

import BookMyTurf.entity.Turf;
import BookMyTurf.repository.TurfRepository;
import java.util.List;
@Service
public class TurfService
{

    private final TurfRepository turfRepository;

    public TurfService(TurfRepository turfRepository) 
    {
        this.turfRepository = turfRepository;
    }

    public Turf saveTurf(Turf turf)
    {
    	turf.setStatus("ACTIVE");
    	return turfRepository.save(turf);
    }
    
    public List<Turf> getAllTurfs()
    {
    	return turfRepository.findAll();
    }

    public Turf updateTurf(Long id, Turf updatedTurf)
    {
    	Turf existingTurf = turfRepository.findById(id).orElseThrow(() -> new RuntimeException("Turf not found"));
    	
    	 existingTurf.setName(updatedTurf.getName());
    	    existingTurf.setDescription(updatedTurf.getDescription());
    	    existingTurf.setAddress(updatedTurf.getAddress());
    	    existingTurf.setCity(updatedTurf.getCity());
    	    existingTurf.setLatitude(updatedTurf.getLatitude());
    	    existingTurf.setLongitude(updatedTurf.getLongitude());

    	    return turfRepository.save(existingTurf);

    }
    
    
    public void deleteTurf(Long id)
    {
    	if(!turfRepository.existsById(id))
    	{
    		throw new RuntimeException("Turf not found");
    	}
    	turfRepository.deleteById(id);
    }
}
