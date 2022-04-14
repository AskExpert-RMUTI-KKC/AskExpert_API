package rmuti.askexpert.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import rmuti.askexpert.model.response.ResVerify;
import rmuti.askexpert.model.table.VerifyData;

@Mapper(componentModel = "spring")
public interface ResVerifyMapper{
    ResVerify toResVerify(VerifyData data);
    
    List<ResVerify> toListResVerify(List<VerifyData> data);
}