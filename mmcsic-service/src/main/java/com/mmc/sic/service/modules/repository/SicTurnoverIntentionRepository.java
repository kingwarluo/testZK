package com.mmc.sic.service.modules.repository;

import org.springframework.stereotype.Repository;
import com.mmc.sic.service.api.base.BaseMapper;
import com.mmc.sic.service.modules.repository.mapper.SicTurnoverIntentionMapper;
import com.mmc.sic.service.modules.domain.SicTurnoverIntention;
import com.mmc.sic.service.modules.repository.mapper.example.SicTurnoverIntentionExample;
import com.mmc.sic.service.api.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 *对数据库操作的统一入口，该文件只会生成一次，在执行Generator时不会覆盖掉新增加的代码
 *
 */
@Repository
public class SicTurnoverIntentionRepository extends BaseRepository<SicTurnoverIntention, SicTurnoverIntentionExample> {

    @Autowired
    private SicTurnoverIntentionMapper mapper;

    @Override
    protected BaseMapper<SicTurnoverIntention, SicTurnoverIntentionExample> getMapper() {
        return mapper;
    }

}