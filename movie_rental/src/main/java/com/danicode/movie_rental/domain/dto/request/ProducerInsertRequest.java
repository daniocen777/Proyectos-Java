package com.danicode.movie_rental.domain.dto.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class ProducerInsertRequest implements Serializable {
    @NotBlank(message = "Company name shouldn't be NULL OR EMPTY")
    private String companyName;

    @NotBlank(message = "Country shouldn't be NULL OR EMPTY")
    private String country;

    public ProducerInsertRequest() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
