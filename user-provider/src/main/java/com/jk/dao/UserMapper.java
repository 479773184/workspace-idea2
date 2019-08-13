package com.jk.dao;

import com.jk.model.*;

import java.util.List;

public interface UserMapper {

    List<PermissionModel> getTreeAll(Integer id);

    List<PermissionModel> queryPermission();

    List<RoleModel> queryRole();

    List<UserModel> queryUser();

    List<String> getPermissionByRId(Integer roleId);

    List<PermissionModel> getPermissionAll();

    int deleteRolePermissByRid(Integer roleId);

    int insert(RolePermissModel rpm);

    List<Integer> getRoleByUserId(Integer userId);

    int deleteRoleById(Integer uidTwo);

    int insert2(UserRoleModel urm);

    List<UserModel> loginUser(UserModel userModel);


    List<UserModel> queryUser2();

    void addUser(List<UserModel> list);
}