package com.mmc.sic.service.modules.repository;

import org.springframework.stereotype.Repository;
import com.mmc.sic.service.api.base.BaseMapper;
import com.mmc.sic.service.modules.repository.mapper.SicExpireMapper;
import com.mmc.sic.service.modules.domain.SicExpire;
import com.mmc.sic.service.modules.repository.mapper.example.SicExpireExample;
import com.mmc.sic.service.api.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 *对数据库操作的统一入口，该文件只会生成一次，在执行Generator时不会覆盖掉新增加的代码
 *
 */
@Repository
public class SicExpireRepository extends BaseRepository<SicExpire, SicExpireExample> {

    @Autowired
    private SicExpireMapper mapper;

    @Override
    protected BaseMapper<SicExpire, SicExpireExample> getMapper() {
        return mapper;
    }

}