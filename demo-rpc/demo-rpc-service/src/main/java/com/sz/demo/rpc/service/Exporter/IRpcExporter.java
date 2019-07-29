package com.sz.demo.rpc.service.Exporter;

public interface IRpcExporter {

    /**
     *  启动服务
     * @throws Exception
     */
    void start() throws Exception;


    /**
     *  服务注册
     * @param inter 服务接口
     * @param impl  服务实现类
     */
    void register(Class inter,Class impl);
}
