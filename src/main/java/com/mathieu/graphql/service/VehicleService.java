package com.mathieu.graphql.service;

import com.mathieu.graphql.dao.entity.Vehicle;
import com.mathieu.graphql.dao.repository.VehicleRepository;
import io.leangen.graphql.spqr.spring.util.ConcurrentMultiRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.FluxSink;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final ConcurrentMultiRegistry<String, FluxSink<Vehicle>> subscribers= new ConcurrentMultiRegistry<>();
    public VehicleService(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate) {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setModelCode(modelCode);
        vehicle.setBrandName(brandName);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        vehicle.setLaunchDate(LocalDate.parse(launchDate, formatter));
        vehicle = this.vehicleRepository.save(vehicle);
        Vehicle finalVehicle = vehicle;
        subscribers.get(vehicle.getId()).forEach(subscriber -> subscriber.next(finalVehicle));
        return vehicle;
    }

    @Transactional(readOnly = true)
    public List<Vehicle> getAllVehicles(final int count) {
        return this.vehicleRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Vehicle> getVehicle(final String id) {
        return this.vehicleRepository.findById(id);
    }

    public ConcurrentMultiRegistry<String, FluxSink<Vehicle>> getSubscribers() {
        return subscribers;
    }
    @Transactional
    public Vehicle updateVehicle(String id, String modelCode) {
        Optional<Vehicle> vehicleOptional = getVehicle(id);
        if(vehicleOptional.isPresent()){
            Vehicle vehicle = vehicleRepository.save(vehicleOptional.get().setModelCode(modelCode));
            subscribers.get(vehicle.getId()).forEach(subscriber -> subscriber.next(vehicle));
            return vehicle;
        }
        return null;
    }
}
