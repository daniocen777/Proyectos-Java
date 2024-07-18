package com.danicode.movie_rental.persistence.mapper;

import com.danicode.movie_rental.domain.dto.pojo.Producer;
import com.danicode.movie_rental.domain.dto.response.ProducerResponse;
import com.danicode.movie_rental.persistence.entity.ProducerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    @Mappings({
            @Mapping(source = "producerId", target = "producerId"),
            @Mapping(source = "companyName", target = "companyName"),
            @Mapping(source = "country", target = "country")
    })
    Producer toProducer(ProducerEntity producerEntity);

    @InheritInverseConfiguration
    @Mapping(target = "movies", ignore = true)
    ProducerEntity toProducerEntity(Producer producer);

    List<Producer> toProducersList(List<ProducerEntity> producerEntities);
}
