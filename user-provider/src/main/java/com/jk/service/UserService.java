package com.jk.service;

import com.jk.model.LogModel;
import com.jk.model.PermissionModel;
import com.jk.model.RoleModel;
import com.jk.model.UserModel;
import com.jk.util.PageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {


    List<PermissionModel> getTreeAll(Integer id);
    PageUtil queryPermission(Integer page, Integer rows);
    PageUtil queryRole(Integer page, Integer rows);
    PageUtil queryUser(Integer page, Integer rows);
    List<PermissionModel> getPermissionByRId(Integer roleId);
    int updateRolePermiss(Integer[] perids, Integer roleId);
    List<RoleModel> getRoleByUserId(Integer userId);
    int updateUserRole(Integer[] roleIds, Integer uidTwo);
    List<UserModel> loginUser(UserModel userModel);
    List<UserModel> queryUser2();
    void addUser(List<UserModel> list);
    PageUtil queryLogList(Integer page, Integer rows, LogModel logModel, UserModel userModel);
}