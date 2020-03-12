package com.mathieu.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.mathieu.graphql.dao.entity.Vehicle;
import com.mathieu.graphql.service.VehicleService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@GraphQLApi
@Component
public class VehicleMutation {

    @Autowired
    private VehicleService vehicleService;

    @GraphQLMutation
    public Vehicle createVehicle(@GraphQLNonNull final String type, @GraphQLNonNull final String modelCode,final String brandName, final String launchDate) {
        return this.vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }
    @GraphQLMutation
    public Vehicle updateVehicle(@GraphQLNonNull final String id,@GraphQLNonNull final String modelCode){
        return this.vehicleService.updateVehicle(id,modelCode);
    }
}
