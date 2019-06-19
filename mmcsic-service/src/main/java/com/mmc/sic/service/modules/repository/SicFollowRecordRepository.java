package com.mmc.sic.service.modules.repository;

import org.springframework.stereotype.Repository;
import com.mmc.sic.service.api.base.BaseMapper;
import com.mmc.sic.service.modules.repository.mapper.SicFollowRecordMapper;
import com.mmc.sic.service.modules.domain.SicFollowRecord;
import com.mmc.sic.service.modules.repository.mapper.example.SicFollowRecordExample;
import com.mmc.sic.service.api.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 *对数据库操作的统一入口，该文件只会生成一次，在执行Generator时不会覆盖掉新增加的代码
 *
 */
@Repository
public class SicFollowRecordRepository extends BaseRepository<SicFollowRecord, SicFollowRecordExample> {

    @Autowired
    private SicFollowRecordMapper mapper;

    @Override
    protected BaseMapper<SicFollowRecord, SicFollowRecordExample> getMapper() {
        return mapper;
    }

}