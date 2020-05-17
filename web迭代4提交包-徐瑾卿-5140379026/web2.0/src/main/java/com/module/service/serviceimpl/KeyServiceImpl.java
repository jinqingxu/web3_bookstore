package com.module.service.serviceimpl;

import com.module.service.KeyService;

/**
 * Created by piglet on 2017/4/6.
 */
public class KeyServiceImpl implements KeyService {
    private String privateKey;

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}
