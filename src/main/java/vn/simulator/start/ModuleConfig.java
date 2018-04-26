package vn.simulator.start;

import vn.simulator.start.config.CommonConfig;
import vn.simulator.start.config.DbConfig;
import vn.simulator.start.config.RestfulConfig;

public class ModuleConfig {

    private CommonConfig commonConfig;
    private DbConfig firstDatabase;
    private DbConfig secondDatabase;
    private RestfulConfig restfulConfig;

    public CommonConfig getCommonConfig() {
        return commonConfig;
    }

    public DbConfig getFirstDatabase() {
        return firstDatabase;
    }

    public DbConfig getSecondDatabase() {
        return secondDatabase;
    }

    public RestfulConfig getRestfulConfig() {
        return restfulConfig;
    }
}
