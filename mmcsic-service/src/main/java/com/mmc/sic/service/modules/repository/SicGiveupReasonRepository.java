package com.mmc.sic.service.modules.repository;

import org.springframework.stereotype.Repository;
import com.mmc.sic.service.api.base.BaseMapper;
import com.mmc.sic.service.modules.repository.mapper.SicGiveupReasonMapper;
import com.mmc.sic.service.modules.domain.SicGiveupReason;
import com.mmc.sic.service.modules.repository.mapper.example.SicGiveupReasonExample;
import com.mmc.sic.service.api.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 *对数据库操作的统一入口，该文件只会生成一次，在执行Generator时不会覆盖掉新增加的代码
 *
 */
@Repository
public class SicGiveupReasonRepository extends BaseRepository<SicGiveupReason, SicGiveupReasonExample> {

    @Autowired
    private SicGiveupReasonMapper mapper;

    @Override
    protected BaseMapper<SicGiveupReason, SicGiveupReasonExample> getMapper() {
        return mapper;
    }

}