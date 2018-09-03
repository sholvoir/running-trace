package cn.micit.service;

import cn.micit.domain.CurrentPosition;

public interface PositionService {
    void processPositionInfo(long id, CurrentPosition currentPosition, boolean sendPositionsToDistributionService);
}
