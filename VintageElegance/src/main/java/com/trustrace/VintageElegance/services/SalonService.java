package com.trustrace.VintageElegance.services;

import com.trustrace.VintageElegance.model.Salon;
import com.trustrace.VintageElegance.repository.SalonRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalonService {
    @Autowired
    private SalonRepository salonRepository;

    public Salon createSalon(Salon salon) {
        return salonRepository.save(salon);
    }
    public Page<Salon> getSalons(int page, int size, String sortBy) {
        return salonRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    public Optional<Salon> getSalonById(String id) {
        return salonRepository.findById(id);
    }

    public void deleteSalon(String id) {
        salonRepository.deleteById(id);
    }

    public List<String> getUniqueStates() {
        return salonRepository.findDistinctStates()
                .stream()
                .map(Salon::getState)  // Extract only the state field
                .distinct()            // Remove duplicates
                .collect(Collectors.toList());
    }


    public List<String> getCities(String state) {
        return salonRepository.findCitiesByState(state)
                .stream()
                .map(Salon::getCity)
                .distinct()
                .collect(Collectors.toList());
    }


    public List<String> getOutlets(String city) {
        return salonRepository.findOutletsByCity(city)
                .stream()
                .map(Salon::getOutletName)
                .distinct()
                .collect(Collectors.toList());
    }


    public List<Map<String, Object>> getServicesByGender(String outletName, String gender) {
        Salon salon = salonRepository.findServicesByOutlet(outletName);
        if (salon == null || salon.getServices() == null) {
            return Collections.emptyList();
        }
        List<Map<String, Object>> filteredServices = salon.getServices().stream()
                .filter(service -> service.getGender().equalsIgnoreCase(gender))
                .map(service -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", service.getName());
                    map.put("cost", service.getCost());
                    return map;
                })
                .collect(Collectors.toList());
        return filteredServices;
    }


public Map<String, List<String>> getStylistsByOutlet(String outletName) {
    Salon salon = salonRepository.findStylistsByOutlet(outletName);
    if (salon == null) {
        throw new ResourceNotFoundException("Outlet not found: " + outletName);
    }
    Map<String, List<String>> response = new HashMap<>();
    response.put("maleStylists", salon.getMaleStylists());
    response.put("femaleStylists", salon.getFemaleStylists());
    return response;
}

    public Salon updateSalon(String id, Salon updatedSalon) {
        Optional<Salon> existingSalonOpt = salonRepository.findById(id.trim());
        if (!existingSalonOpt.isPresent()) {
            throw new ResourceNotFoundException("Salon not found with id: " + id);
        }

        Salon existingSalon = existingSalonOpt.get();
        existingSalon.setState(updatedSalon.getState());
        existingSalon.setCity(updatedSalon.getCity());
        existingSalon.setOutletName(updatedSalon.getOutletName());
        existingSalon.setServices(updatedSalon.getServices());
        existingSalon.setMaleStylists(updatedSalon.getMaleStylists());
        existingSalon.setFemaleStylists(updatedSalon.getFemaleStylists());
        existingSalon.setModerator(updatedSalon.getModerator());

        return salonRepository.save(existingSalon);
    }


}