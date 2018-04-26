package vn.simulator.start.config;

public class CommonConfig {

    private Integer workerPoolSize;
    private Integer workerMaxExecuteTime;
    private Integer blockThreadCheckInterval;


    public Integer getWorkerPoolSize() {
        return workerPoolSize;
    }

    public CommonConfig setWorkerPoolSize(Integer workerPoolSize) {
        this.workerPoolSize = workerPoolSize;
        return this;
    }

    public Integer getWorkerMaxExecuteTime() {
        return workerMaxExecuteTime;
    }

    public CommonConfig setWorkerMaxExecuteTime(Integer workerMaxExecuteTime) {
        this.workerMaxExecuteTime = workerMaxExecuteTime;
        return this;
    }

    public Integer getBlockThreadCheckInterval() {
        return blockThreadCheckInterval;
    }

    public CommonConfig setBlockThreadCheckInterval(Integer blockThreadCheckInterval) {
        this.blockThreadCheckInterval = blockThreadCheckInterval;
        return this;
    }
}
