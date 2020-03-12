package com.mathieu.graphql.dao.entity;

import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Accessors(
        chain = true
)
@EqualsAndHashCode
@Document
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GraphQLNonNull
    private String id;
    private String type;

    private String modelCode;

    private String brandName;

    private LocalDate launchDate;

    private transient String formattedDate;

    public String getModelCode() {
        return modelCode;
    }

    // Getter and setter
    public String getFormattedDate() {
        return getLaunchDate().toString();
    }
}
