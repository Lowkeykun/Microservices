package com.jerome.loans.mapper;

import com.jerome.loans.domain.LoansModel;
import com.jerome.loans.dto.LoansDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoansMapper {

    LoansMapper INSTANCE = Mappers.getMapper(LoansMapper.class);

    LoansDto toDto(LoansModel loansModel);
    LoansModel toEntity(LoansDto loansDto);

    void updateLoanFromDto(LoansDto loansDto, @MappingTarget LoansModel loansModel);

}
