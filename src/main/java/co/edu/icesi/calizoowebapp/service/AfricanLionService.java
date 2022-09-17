package co.edu.icesi.calizoowebapp.service;

import co.edu.icesi.calizoowebapp.model.AfricanLion;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AfricanLionService {

    public AfricanLion getLion(@PathVariable String lionName);

    public AfricanLion createLion(@RequestBody AfricanLion africanLion);

    public List<AfricanLion> getLions();
}
