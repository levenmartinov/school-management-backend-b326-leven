package com.techproed.schoolmanagementbackendb326.payload.mappers;


import com.techproed.schoolmanagementbackendb326.entity.concretes.business.EducationTerm;
import com.techproed.schoolmanagementbackendb326.payload.request.business.EducationTermRequest;
import com.techproed.schoolmanagementbackendb326.payload.response.business.EducationTermResponse;
import org.mapstruct.*;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        //with this parameter, MapStruct will always check source properties if they have null value or not.
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        //If a source bean property equals null, the target bean property will be ignored and retain its existing value. So, we will be able to perform partial update.
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EducationTermMapper {

    EducationTerm mapEducationTermRequestToEducationTerm(EducationTermRequest educationTermRequest);

    //TODO check the usage
    EducationTerm updateEducationTermWithEducationTermRequest(EducationTermRequest educationTermRequest, @MappingTarget EducationTerm educationTerm);

    EducationTermResponse mapEducationTermToEducationTermResponse( EducationTerm educationTerm);
}





/*

@Component
public class EducationTermMapper {

    */
/**
 * @param educationTermRequest DTO from Postman or FE
 * @return EducationTerm Entity
 *//*

    public EducationTerm mapEducationTermRequestToEducationTerm(EducationTermRequest educationTermRequest){
        return EducationTerm.builder()
                .term(educationTermRequest.getTerm())
                .startDate(educationTermRequest.getStartDate())
                .endDate(educationTermRequest.getEndDate())
                .lastRegistrationDate(educationTermRequest.getLastRegistrationDate())
                .build();
    }

    */
/**
 *
 * @param educationTerm Entity fetched from DB
 * @return EducationTermResponse DTO
 *//*

    public EducationTermResponse mapEducationTermToEducationTermResponse(EducationTerm educationTerm){
        return EducationTermResponse.builder()
                .id(educationTerm.getId())
                .term(educationTerm.getTerm())
                .startDate(educationTerm.getStartDate())
                .endDate(educationTerm.getEndDate())
                .lastRegistrationDate(educationTerm.getLastRegistrationDate())
                .build();
    }
}
*/
