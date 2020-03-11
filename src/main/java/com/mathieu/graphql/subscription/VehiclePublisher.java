package com.mathieu.graphql.subscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.mathieu.graphql.dao.entity.Vehicle;
import com.mathieu.graphql.service.VehicleService;
import io.leangen.graphql.annotations.GraphQLSubscription;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Component
public class VehiclePublisher implements GraphQLSubscriptionResolver {
    @Autowired
    private VehicleService vehicleService;

    @GraphQLSubscription
    public Publisher<Vehicle> vehiclePublisher(String vehicleId) {
        return Flux.create(subscriber -> vehicleService.getSubscribers().add(vehicleId, subscriber.onDispose(() -> vehicleService.getSubscribers().remove(vehicleId, subscriber))), FluxSink.OverflowStrategy.LATEST);
    }
}