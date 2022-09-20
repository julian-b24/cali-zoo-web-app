package co.edu.icesi.calizoowebapp.api;

import co.edu.icesi.calizoowebapp.dto.AfricanLionDTO;
import co.edu.icesi.calizoowebapp.dto.AfricanLionQueryResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/african-lion")
public interface AfricanLionAPI {

    @GetMapping("/{lionName}")
    public AfricanLionQueryResponseDTO getLion(@PathVariable String lionName);

    @PostMapping()
    public AfricanLionDTO createLion(@RequestBody AfricanLionDTO africanLionDTO);

    @GetMapping
    public List<AfricanLionDTO> getLions();
}
