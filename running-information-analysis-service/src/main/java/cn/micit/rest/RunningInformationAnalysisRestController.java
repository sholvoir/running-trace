package cn.micit.rest;

import cn.micit.domain.RunningInformation;
import cn.micit.domain.RunningInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RunningInformationAnalysisRestController {
    private RunningInformationRepository runningInformationRepository;
    private final int PAGE_SIZE = 2;

    @Autowired
    public RunningInformationAnalysisRestController(RunningInformationRepository runningInformationRepository) {
        this.runningInformationRepository = runningInformationRepository;
    }

    @RequestMapping(value = "/running-information", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createRunningInformation(@RequestBody RunningInformation runningInformation) {
        runningInformationRepository.save(runningInformation);
    }

    @RequestMapping(value = "/running-information/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteRunningInformation(@PathVariable Long id) {
        runningInformationRepository.deleteById(id);
    }

    @RequestMapping(value = "/running-information/user-name/{userName}", method = RequestMethod.GET)
    public List<RunningInformation> findByUserName(@PathVariable String userName) {
        return runningInformationRepository.findByUserName(userName);
    }

    @RequestMapping(value = "/running-information/running-id/{runningId}", method = RequestMethod.GET)
    public List<RunningInformation> findByRunningId(@PathVariable String runningId) {
        return runningInformationRepository.findByRunningId(runningId);
    }

    @RequestMapping(value = "/running-information", method = RequestMethod.GET)
    public Page<RunningInformation> allRunningInformation(@RequestParam(name = "page") int page) {
        return runningInformationRepository.findAll(PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "heartRate")));
    }
}
