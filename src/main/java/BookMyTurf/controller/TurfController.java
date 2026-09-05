package BookMyTurf.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import BookMyTurf.dto.TurfRequestDTO;
import BookMyTurf.dto.TurfResponseDTO;
import BookMyTurf.entity.Turf;
import BookMyTurf.entity.User;
import BookMyTurf.service.TurfService;
import BookMyTurf.repository.UserRepository;
@RestController
@RequestMapping("/api/turfs")
public class TurfController
{

    private final TurfService turfService;
    private final UserRepository userRepository;
    
    public TurfController(TurfService turfService,
            UserRepository userRepository)
    {
        this.turfService = turfService;
        this.userRepository = userRepository;
    }
    
    @PostMapping
    public Turf createTurf(@RequestBody TurfRequestDTO request) {

    	User owner = userRepository.findById(request.getOwnerId()).orElseThrow(() -> new RuntimeException("Owner not found"));
   
      	
        Turf turf = new Turf();
      	turf.setOwner(owner);
      	
        turf.setName(request.getName());
        turf.setDescription(request.getDescription());
        turf.setAddress(request.getAddress());
        turf.setCity(request.getCity());
        turf.setLatitude(request.getLatitude());
        turf.setLongitude(request.getLongitude());

        return turfService.saveTurf(turf);
    }
    
    @GetMapping
    public List<TurfResponseDTO> getAllTurfs() {

        List<Turf> turfs = turfService.getAllTurfs();

        return turfs.stream()
                .map(turf -> {

                    TurfResponseDTO response = new TurfResponseDTO();

                    response.setId(turf.getId());
                    response.setName(turf.getName());
                    response.setDescription(turf.getDescription());
                    response.setAddress(turf.getAddress());
                    response.setCity(turf.getCity());
                    response.setLatitude(turf.getLatitude());
                    response.setLongitude(turf.getLongitude());
                    response.setStatus(turf.getStatus());
                    response.setOwnerId(turf.getOwner().getId());

                    return response;
                })
                .toList();
    }
    @PutMapping("/{id}")
    public TurfResponseDTO updateTurf(@PathVariable Long id, @RequestBody Turf turf)
    {

        Turf updatedTurf = turfService.updateTurf(id, turf);

        TurfResponseDTO response = new TurfResponseDTO();

        response.setId(updatedTurf.getId());
        response.setName(updatedTurf.getName());
        response.setDescription(updatedTurf.getDescription());
        response.setAddress(updatedTurf.getAddress());
        response.setCity(updatedTurf.getCity());
        response.setLatitude(updatedTurf.getLatitude());
        response.setLongitude(updatedTurf.getLongitude());
        response.setStatus(updatedTurf.getStatus());
        response.setOwnerId(updatedTurf.getOwner().getId());

        return response;
    }
    
    @DeleteMapping("/{id}")
    public String deleteTurf(@PathVariable Long id)
    {
    	
    	turfService.deleteTurf(id);
    	
    	return "Turf deleted successfully";
    }
}