package com.mmc.sic.service.modules.repository;

import org.springframework.stereotype.Repository;
import com.mmc.sic.service.api.base.BaseMapper;
import com.mmc.sic.service.modules.repository.mapper.SicInfoMapper;
import com.mmc.sic.service.modules.domain.SicInfo;
import com.mmc.sic.service.modules.repository.mapper.example.SicInfoExample;
import com.mmc.sic.service.api.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 *对数据库操作的统一入口，该文件只会生成一次，在执行Generator时不会覆盖掉新增加的代码
 *
 */
@Repository
public class SicInfoRepository extends BaseRepository<SicInfo, SicInfoExample> {

    @Autowired
    private SicInfoMapper mapper;

    @Override
    protected BaseMapper<SicInfo, SicInfoExample> getMapper() {
        return mapper;
    }

}