package com.mathieu.graphql.subscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.mathieu.graphql.dao.entity.Vehicle;
import com.mathieu.graphql.service.VehicleService;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@GraphQLApi
@Component
public class VehiclePublisher{
    @Autowired
    private VehicleService vehicleService;

    @GraphQLSubscription
    public Publisher<Vehicle> vehiclePublisher(@GraphQLNonNull String vehicleId) {
        System.out.println("test");
        return Flux.create(subscriber -> vehicleService.getSubscribers().add(vehicleId, subscriber.onDispose(() -> vehicleService.getSubscribers().remove(vehicleId, subscriber))), FluxSink.OverflowStrategy.LATEST);
    }
}