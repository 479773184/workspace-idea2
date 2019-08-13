/**
 * Copyright (C), 2019, 公司
 * FileName: UserServiceImpl
 * Author:   zgm
 * Date:     2019/8/9 11:38
 * Description: a
 * History:
 * zgm          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.dao.UserMapper;
import com.jk.model.*;
import com.jk.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author zgm
 * @create 2019/8/9
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<PermissionModel> getTreeAll(Integer id) {

        return userMapper.getTreeAll(id);
    }

    @Override
    public PageUtil queryPermission(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<PermissionModel> list = userMapper.queryPermission();
        PageInfo<PermissionModel> pageInfo = new PageInfo<>(list);
        PageUtil pageUtil = new PageUtil((int)pageInfo.getTotal(),page,rows);
        pageUtil.setList(list);
        return pageUtil;
    }

    @Override
    public PageUtil queryRole(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<RoleModel> list = userMapper.queryRole();
        PageInfo<RoleModel> pageInfo = new PageInfo<>(list);
        PageUtil pageUtil = new PageUtil((int)pageInfo.getTotal(),page,rows);
        pageUtil.setList(list);
        return pageUtil;
    }

    @Override
    public PageUtil queryUser(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<UserModel> list=userMapper.queryUser();
        PageInfo<UserModel> pageInfo = new PageInfo<>(list);
        PageUtil pageUtil = new PageUtil((int) pageInfo.getTotal(),page,rows);
        pageUtil.setList(list);
        return pageUtil;
    }

    @Override
    public List<PermissionModel> getPermissionByRId(Integer roleId) {
        List<String> list=userMapper.getPermissionByRId(roleId);
        List<PermissionModel> listTwo=userMapper.getPermissionAll();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listTwo.size(); j++) {
                if(list.get(i).equals(listTwo.get(j).getId().toString())){
                    listTwo.get(j).setChecked("true");
                }
            }
        }
        return listTwo;
    }

    @Override
    public int updateRolePermiss(Integer[] perids, Integer roleId) {
        //先 删除 原有的 关联
        int i  = userMapper.deleteRolePermissByRid(roleId);
        //再建立  新的 关联
        if(i >= 0){
            for (int j = 0; j < perids.length; j++) {
                RolePermissModel rpm = new RolePermissModel();
                rpm.setPermissionId(perids[j]);
                rpm.setRoleId(roleId);
                i = userMapper.insert(rpm);
            }
        }
        return i;
    }

    @Override
    public List<RoleModel> getRoleByUserId(Integer userId) {
        // 先查 自己的
        List<Integer> list =   userMapper.getRoleByUserId(userId);
        // 再查所有的 角色
        List<RoleModel> listtwo =   userMapper.queryRole();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listtwo.size(); j++) {
                if(list.get(i) == listtwo.get(j).getId()){
                    listtwo.get(j).setChecked("true");
                }
            }
        }
        return listtwo;
    }

    @Override
    public int updateUserRole(Integer[] roleIds, Integer uidTwo) {
        //先 删除 该用户  原来 有的 角色
        int i = userMapper.deleteRoleById(uidTwo);
        //再新增 现在 要加的  角色
        if(i >=0){
            for (int j = 0; j < roleIds.length; j++) {
                UserRoleModel urm = new  UserRoleModel();
                urm.setRoleId(roleIds[j]);
                urm.setUserId(uidTwo.toString());
                i = userMapper.insert2(urm);
            }
        }
        return i;
    }

    @Override
    public List<UserModel>  loginUser(UserModel userModel) {

        return  userMapper.loginUser(userModel);
    }

    @Override
    public List<UserModel> queryUser2() {
        return userMapper.queryUser2();
    }

    @Override
    public void addUser(List<UserModel> list) {
        userMapper.addUser(list);
    }

    @Override
    public PageUtil queryLogList(Integer page, Integer rows, LogModel logModel, UserModel userModel) {
        Query query=new Query();
        Criteria criteria = new Criteria();
        //判断方法名搜索
        if(StringUtils.isNotEmpty(logModel.getName())){
            Pattern pattern = Pattern.compile("^.*"+logModel.getName()+".*$",Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
            query.addCriteria(criteria);
        }
        //判断时间搜索
        if(logModel.getStarDate()!=null && logModel.getEndDate()!=null){
            criteria.and("createtime").gte(logModel.getStarDate()).lte(logModel.getEndDate());
            query.addCriteria(criteria);
        }else if(logModel.getStarDate()!=null){
            criteria.and("createtime").gte(logModel.getStarDate());
            query.addCriteria(criteria);
        }else if(logModel.getEndDate()!=null){
            criteria.and("createtime").lte(logModel.getEndDate());
            query.addCriteria(criteria);
        }
        if(userModel!=null &&userModel.getId()!=1){
            criteria.and("userId").is(userModel.getId());
        }
        int count = (int) mongoTemplate.count(query, LogModel.class, "log");
        PageUtil pageUtil = new PageUtil(count,page,rows);
        Integer firstResultCount = pageUtil.getFirstResultCount();
        query.with(new Sort(Sort.Direction.DESC, "createtime"));
        query.skip(firstResultCount);
        query.limit(rows);

        List<LogModel> list = mongoTemplate.find(query, LogModel.class, "log");
        pageUtil.setList(list);
        return pageUtil;
    }


}
