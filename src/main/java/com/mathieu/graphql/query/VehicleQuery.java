package com.mathieu.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mathieu.graphql.dao.entity.Vehicle;
import com.mathieu.graphql.service.VehicleService;
import io.leangen.graphql.annotations.*;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@GraphQLApi
@Component
public class VehicleQuery {

    @Autowired
    private VehicleService vehicleService;
    @GraphQLQuery
    public List<Vehicle> getVehicles(final int count) {
        return this.vehicleService.getAllVehicles(count);
    }
    @GraphQLQuery
    public Optional<Vehicle> getVehicle(@GraphQLNonNull final String id) {
        return this.vehicleService.getVehicle(id);
    }
}
