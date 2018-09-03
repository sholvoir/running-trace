package cn.micit.rest;

import cn.micit.domain.SupplyLocation;
import cn.micit.domain.SupplyLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupplyLocationRestController {
    private SupplyLocationRepository repository;

    @Autowired
    public SupplyLocationRestController(SupplyLocationRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/bulk/supplyLocations", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<SupplyLocation> locations) {
        repository.saveAll(locations);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAll() {
        repository.deleteAll();
    }
}
