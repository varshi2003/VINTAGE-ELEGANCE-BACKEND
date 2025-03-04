
package com.trustrace.VintageElegance.repository;

import com.trustrace.VintageElegance.model.Salon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SalonRepository extends MongoRepository<Salon, String> {
    @Query(value = "{}", fields = "{state: 1, _id: 0}")
    List<String> findAllStates();

    @Query(value = "{}", fields = "{state: 1, _id: 0}")
    List<Salon> findDistinctStates();

@Query(value = "{ 'state': ?0 }", fields = "{ 'city': 1, '_id': 0 }")
List<Salon> findCitiesByState(String state);

@Query(value = "{ 'city': ?0 }", fields = "{ 'outletName': 1, '_id': 0 }")
List<Salon> findOutletsByCity(String city);


    @Query(value = "{ 'outletName': ?0 }", fields = "{ 'services': 1, '_id': 0 }")
    Salon findServicesByOutlet(String outletName);

@Query(value = "{ 'outletName': ?0 }", fields = "{ 'maleStylists': 1, 'femaleStylists': 1, '_id': 0 }")
Salon findStylistsByOutlet(String outletName);

}
