package vn.simulator.consumer;

public interface IGateway {

    IGateway useRestful();

    IGateway useMq();

    void start();
}
