package com.htkapp.core.springAop.methodService.methodServiceImpl;

import com.htkapp.core.springAop.methodService.MoreMethodsService;

public class MoreMethodsServiceImpl implements MoreMethodsService {

    @Override
    public void pushMesToUser() {
        System.out.println("哈哈");
    }

}
