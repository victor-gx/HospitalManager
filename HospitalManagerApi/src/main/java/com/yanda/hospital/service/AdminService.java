package com.yanda.hospital.service;

import com.yanda.hospital.pojo.Admin;

public interface AdminService {
    /**
     * 登录数据校验
     * */
    Admin login(int aId, String aPassword);
}
