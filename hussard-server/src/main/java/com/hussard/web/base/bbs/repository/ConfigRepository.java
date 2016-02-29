package com.hussard.web.base.bbs.repository;

import com.hussard.web.base.bbs.domain.Config;

import java.util.List;

/**
 * Created by user on 2016-02-16.
 */

public interface ConfigRepository {

    List findConfigList();

    Config findConfigByBbsId(int bbsId);

    void saveConfig(Config config);

    void updateConfig(Config config);

}
