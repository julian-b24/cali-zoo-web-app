package co.edu.icesi.calizoowebapp.controller;

import co.edu.icesi.calizoowebapp.api.AfricanLionAPI;
import co.edu.icesi.calizoowebapp.dto.AfricanLionDTO;
import co.edu.icesi.calizoowebapp.mapper.AfricanLionMapper;
import co.edu.icesi.calizoowebapp.repository.AfricanLionRespository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class AfricanLionController implements AfricanLionAPI {

    //Add service field
    private AfricanLionMapper africanLionMapper;

    @Override
    public AfricanLionDTO getLion(String lionName) {
        return null;
    }

    @Override
    public AfricanLionDTO createLion(AfricanLionDTO africanLionDTO) {
        return null;
    }

    @Override
    public List<AfricanLionDTO> getLions() {
        return null;
    }
}
