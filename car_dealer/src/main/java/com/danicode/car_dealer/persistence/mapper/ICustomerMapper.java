package com.danicode.car_dealer.persistence.mapper;

import com.danicode.car_dealer.domain.dto.Customer;
import com.danicode.car_dealer.persistence.entity.CustomerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {

    Customer toCustomer(CustomerEntity entity);

    CustomerEntity toCustomerEntity(Customer customer);

    List<Customer> toCustomersList(List<CustomerEntity> customerEntities);
}
