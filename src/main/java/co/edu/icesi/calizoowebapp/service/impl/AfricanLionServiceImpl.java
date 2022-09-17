package co.edu.icesi.calizoowebapp.service.impl;

import co.edu.icesi.calizoowebapp.model.AfricanLion;
import co.edu.icesi.calizoowebapp.repository.AfricanLionRespository;
import co.edu.icesi.calizoowebapp.service.AfricanLionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AfricanLionServiceImpl implements AfricanLionService {

    public final AfricanLionRespository africanLionRespository;

    @Override
    public AfricanLion getLion(String lionName) {
        return null;
    }

    @Override
    public AfricanLion createLion(AfricanLion africanLion) {
        return null;
    }

    @Override
    public List<AfricanLion> getLions() {
        return null;
    }
}
