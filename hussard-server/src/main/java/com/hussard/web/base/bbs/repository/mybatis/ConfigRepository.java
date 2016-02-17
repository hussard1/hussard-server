package com.hussard.web.base.bbs.repository.mybatis;


import com.hussard.web.base.bbs.domain.Config;

import java.util.List;


public interface ConfigRepository {

    List<Config> findConfigList();

	Config findConfigByBbsId(int bbsId);

	void saveConfig(Config config);

	void updateConfig(Config config);
}
