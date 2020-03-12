package com.mathieu.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mathieu.graphql.dao.entity.Vehicle;
import com.mathieu.graphql.service.VehicleService;
import io.leangen.graphql.annotations.GraphQLMutation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleMutation implements GraphQLMutationResolver {

    @Autowired
    private VehicleService vehicleService;


    public Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate) {
        return this.vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }

    public Vehicle updateVehicle(final String id, final String modelCode){
        return this.vehicleService.updateVehicle(id,modelCode);
    }
}
