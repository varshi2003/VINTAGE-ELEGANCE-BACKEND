
package com.trustrace.VintageElegance.controller;
import com.trustrace.VintageElegance.repository.SalonRepository;
import com.trustrace.VintageElegance.model.Salon;
import com.trustrace.VintageElegance.services.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/salons")
@CrossOrigin(origins = "*")
public class SalonController {

    @Autowired
    private SalonService salonService;
    @Autowired
    private SalonRepository salonRepository;

    @PostMapping
    public Salon createSalon(@RequestBody Salon salon) {
        return salonService.createSalon(salon);
    }

    @GetMapping
    public Page<Salon> getSalons(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "outletName") String sortBy) {
        return salonRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }


    @GetMapping("/{id}")
    public Optional<Salon> getSalonById(@PathVariable String id) {
        return salonService.getSalonById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteSalon(@PathVariable String id) {
        salonService.deleteSalon(id);
        return "Salon deleted successfully";
    }

@GetMapping("/states")
public ResponseEntity<List<String>> getAllStates() {
    List<String> states = salonService.getUniqueStates();
    return ResponseEntity.ok(states);
}

@GetMapping("/cities/{state}")
public ResponseEntity<List<String>> getCitiesByState(@PathVariable String state) {
    List<String> cities = salonService.getCities(state);
    return ResponseEntity.ok(cities);
}


    @GetMapping("/outlets/{city}")
    public ResponseEntity<List<String>> getOutlets(@PathVariable String city) {
        List<String> outlets = salonService.getOutlets(city);
        return ResponseEntity.ok(outlets);
    }

    @GetMapping("/services/{outlet}/{gender}")
    public ResponseEntity<List<Map<String, Object>>> getServicesByGender(@PathVariable String outlet, @PathVariable String gender) {
        return ResponseEntity.ok(salonService.getServicesByGender(outlet, gender));
    }

    @GetMapping("/stylists/{outlet}")
    public ResponseEntity<Map<String, List<String>>> getStylistsByOutlet(@PathVariable String outlet) {
        return ResponseEntity.ok(salonService.getStylistsByOutlet(outlet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Salon> updateSalon(@PathVariable String id, @RequestBody Salon salon) {
        Salon updatedSalon = salonService.updateSalon(id, salon);
        return ResponseEntity.ok(updatedSalon);
    }


}
