package cn.micit.service.impl;

import cn.micit.domain.CurrentPosition;
import cn.micit.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultPositionService implements PositionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPositionService.class);
    @Override
    public void processPositionInfo(long id, CurrentPosition currentposition, boolean sendPositionsToDistributionService) {
        LOGGER.info("logging here");
    }
}
