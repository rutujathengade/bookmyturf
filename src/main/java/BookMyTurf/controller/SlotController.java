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

import BookMyTurf.dto.SlotRequestDTO;
import BookMyTurf.dto.SlotResponseDTO;
import BookMyTurf.entity.Slot;
import BookMyTurf.entity.Turf;
import BookMyTurf.repository.TurfRepository;
import BookMyTurf.service.SlotService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/slots")
public class SlotController 
{

    private final SlotService slotService;
    private final TurfRepository turfRepository;

    public SlotController(SlotService slotService, TurfRepository turfRepository)
    {
        this.slotService = slotService;
        this.turfRepository = turfRepository;
    }

    @PostMapping
    public SlotResponseDTO createSlot(
            @Valid @RequestBody SlotRequestDTO request) 
    {

        Turf turf = turfRepository.findById(request.getTurfId())
                .orElseThrow(() -> new RuntimeException("Turf not found"));

        Slot slot = new Slot();

        slot.setTurf(turf);
        slot.setSlotDate(request.getSlotDate());
        slot.setStartTime(request.getStartTime());
        slot.setEndTime(request.getEndTime());
        slot.setPrice(request.getPrice());
        slot.setStatus(Slot.AVAILABLE);

        Slot savedSlot = slotService.saveSlot(slot);

        SlotResponseDTO response = new SlotResponseDTO();

        response.setId(savedSlot.getId());
        response.setTurfId(savedSlot.getTurf().getId());
        response.setSlotDate(savedSlot.getSlotDate());
        response.setStartTime(savedSlot.getStartTime());
        response.setEndTime(savedSlot.getEndTime());
        response.setPrice(savedSlot.getPrice());
        response.setStatus(savedSlot.getStatus());

        return response;
    }

    @GetMapping
    public List<SlotResponseDTO> getAllSlots() 
    {

        List<Slot> slots = slotService.getAllSlots();

        return slots.stream().map(slot -> 
        {

            SlotResponseDTO response = new SlotResponseDTO();

            response.setId(slot.getId());
            response.setTurfId(slot.getTurf().getId());
            response.setSlotDate(slot.getSlotDate());
            response.setStartTime(slot.getStartTime());
            response.setEndTime(slot.getEndTime());
            response.setPrice(slot.getPrice());
            response.setStatus(slot.getStatus());

            return response;

        }).toList();
    }

    @PutMapping("/{id}")
    public SlotResponseDTO updateSlot(
            @PathVariable Long id,
            @Valid @RequestBody SlotRequestDTO request) 
    {

        Turf turf = turfRepository.findById(request.getTurfId())
                .orElseThrow(() -> new RuntimeException("Turf not found"));

        Slot slot = new Slot();

        slot.setTurf(turf);
        slot.setSlotDate(request.getSlotDate());
        slot.setStartTime(request.getStartTime());
        slot.setEndTime(request.getEndTime());
        slot.setPrice(request.getPrice());
        slot.setStatus(Slot.AVAILABLE);

        Slot updatedSlot = slotService.updateSlot(id, slot);

        SlotResponseDTO response = new SlotResponseDTO();

        response.setId(updatedSlot.getId());
        response.setTurfId(updatedSlot.getTurf().getId());
        response.setSlotDate(updatedSlot.getSlotDate());
        response.setStartTime(updatedSlot.getStartTime());
        response.setEndTime(updatedSlot.getEndTime());
        response.setPrice(updatedSlot.getPrice());
        response.setStatus(updatedSlot.getStatus());

        return response;
    }

    @DeleteMapping("/{id}")
    public String deleteSlot(@PathVariable Long id) 
    {

        slotService.deleteSlot(id);

        return "Slot deleted successfully";
    }
}