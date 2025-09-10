package com.example.demoAppv2.util;


import com.example.demoAppv2.dto.PatientDTO;
import com.example.demoAppv2.repository.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;



@Mapper(componentModel = "spring")
public interface PatientMapper {

   // PatientMapper INSTANCIA = Mappers.getMapper(PatientMapper.class);

    @Mapping(source= "fullName", target= "nombre")
    @Mapping(source= "document", target= "documento")
    PatientDTO   patientToPatientDO(Patient p);


    @Mapping(source = "nombre", target= "fullName")
    @Mapping(source = "documento", target= "document")
    Patient patientDtoToPatient(PatientDTO p);

}
