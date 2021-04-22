package com.changgou.file.test;

import org.csource.fastdfs.ClientGlobal;

public class FastdfsClientTest {
    public void upload() throws Exception{
        ClientGlobal.init("D:\\changgou\\changgou-parent\\changgou-service\\changgou-service-file\\src\\main\\resources\\fdfs_client.conf");
    }
}
