package com.mmc.sic.service.modules.repository;

import org.springframework.stereotype.Repository;
import com.mmc.sic.service.api.base.BaseMapper;
import com.mmc.sic.service.modules.repository.mapper.SicHistoryRecordMapper;
import com.mmc.sic.service.modules.domain.SicHistoryRecord;
import com.mmc.sic.service.modules.repository.mapper.example.SicHistoryRecordExample;
import com.mmc.sic.service.api.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 *对数据库操作的统一入口，该文件只会生成一次，在执行Generator时不会覆盖掉新增加的代码
 *
 */
@Repository
public class SicHistoryRecordRepository extends BaseRepository<SicHistoryRecord, SicHistoryRecordExample> {

    @Autowired
    private SicHistoryRecordMapper mapper;

    @Override
    protected BaseMapper<SicHistoryRecord, SicHistoryRecordExample> getMapper() {
        return mapper;
    }

}