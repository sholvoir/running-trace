package cn.micit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RunningInformationRepository extends JpaRepository<RunningInformation, Long> {
    List<RunningInformation> findByUserName(@Param("userName") String userName);
    List<RunningInformation> findByRunningId(@Param("runningId") String runningId);
}
