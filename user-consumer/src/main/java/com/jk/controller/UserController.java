/**
 * Copyright (C), 2019, 公司
 * FileName: UserController
 * Author:   zgm
 * Date:     2019/8/9 11:26
 * Description: a
 * History:
 * zgm          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.LogModel;
import com.jk.model.PermissionModel;
import com.jk.model.RoleModel;
import com.jk.model.UserModel;
import com.jk.service.UserService;
import com.jk.util.DataGridResult;
import com.jk.util.ExportExcel;
import com.jk.util.PageUtil;
import com.jk.util.TreeNoteUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author zgm
 * @create 2019/8/9
 * @since 1.0.0
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Reference
    private UserService userService;
    @Resource
    private RedisTemplate<String, PermissionModel> redisTemplate;



    @RequestMapping("getTreeAll")
    @ResponseBody
    public   List<PermissionModel>  getTreeAll(HttpServletRequest request){
        List<PermissionModel> list = new ArrayList<PermissionModel>();
        UserModel user = (UserModel) request.getSession().getAttribute("user");
        String key="tree"+user.getId();
        if(redisTemplate.hasKey(key)){
            System.out.println("=====走缓存=======");
            list=redisTemplate.opsForList().range(key, 0, -1);
        }else{
            System.out.println("=====走数据库=======");
            list = userService.getTreeAll(user.getId());
            list = TreeNoteUtil.getFatherNode(list);
            for (PermissionModel permissionModel : list) {
                redisTemplate.opsForList().rightPush(key, permissionModel);
            }
            redisTemplate.expire(key, 30, TimeUnit.MINUTES);
        }
        return list;
    }
    @RequestMapping("toshow")
    public String toshow() {
        return "show";
    }

    @RequestMapping("tomenulist")
    public String tomenulist() {
        return "menulist";
    }
    @RequestMapping("torolelist")
    public String torolelist() {
        return "rolelist";
    }
    @RequestMapping("touserlist")
    public String touserlist() {
        return "userlist";
    }
    @RequestMapping("tologshow")
    public String tologshow() {
        return "logshow";
    }
    @RequestMapping("login")
    public String login() {
        return "login";
    }
    @RequestMapping("logshow")
    public String logshow() {
        return "logshow";
    }

    @RequestMapping("queryPermission")
    @ResponseBody
    public DataGridResult queryPermission(Integer page, Integer rows){
        PageUtil pageUtil = userService.queryPermission(page,rows);
        DataGridResult result = new DataGridResult();
        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());
        return result;
    }
    @RequestMapping("queryRole")
    public  @ResponseBody DataGridResult  queryRole(Integer page, Integer rows){
        PageUtil pageUtil = userService.queryRole(page,rows);
        DataGridResult result = new DataGridResult();
        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());
        return result;
    }
    @RequestMapping("queryUser")
    @ResponseBody
    public  DataGridResult queryUser(Integer page,Integer rows){
        PageUtil pageUtil =userService.queryUser(page,rows);
        DataGridResult result = new DataGridResult();
        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());
        return result;
    }
    @RequestMapping("getPermissionByRId")
    @ResponseBody
    public List<PermissionModel> getPermissionByRId(Integer roleId){
        List<PermissionModel> list=userService.getPermissionByRId(roleId);
        list = TreeNoteUtil.getFatherNode(list);
        return list;
    }
    @RequestMapping("updateRolePermiss")
    public  @ResponseBody String updateRolePermiss(Integer[] perids,Integer roleId){

        int i = userService.updateRolePermiss(perids,roleId);
        if(i<1){
            return "0";
        }
        return "1";
    }
    /**
     * 获取 用户 角色
     * @param userId
     * @return
     */
    @RequestMapping("getRoleByUserId")
    public  @ResponseBody List<RoleModel>  getRoleByUserId(Integer userId){
        List<RoleModel> list = userService.getRoleByUserId(userId);
        return list;
    }
    @RequestMapping("updateUserRole")
    public  @ResponseBody int  updateUserRole(Integer[]  roleIds, Integer uidTwo){
        int i =  userService.updateUserRole(roleIds,uidTwo);
        return i;
    }
    //登录
    @RequestMapping("loginUser")
    @ResponseBody
    public  String loginUser(UserModel userModel,HttpServletRequest request){
        String str = "0";
        List<UserModel> list = userService.loginUser(userModel);
        if(list == null || list.size() == 0){
            //代表 登录失败
            str ="0";
        }else{
            //登录成功
            str ="1";
            request.getSession().setAttribute("user", list.get(0));
        }
        return str;
    }

    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response){
        String title="1902b";
        String[] rowName={"编号","账号","密码"};
        List<Object[]> dataList = new ArrayList<>();
        List<UserModel> list=userService.queryUser2();
        for(UserModel userModel:list){
            Object[] obj = new Object[rowName.length];
            obj[0]=userModel.getId();
            obj[1]=userModel.getUsername();
            obj[2]=userModel.getUserpass();
            dataList.add(obj);
        }
        ExportExcel exportExcel =new ExportExcel(title,rowName,dataList,response);
        try {
            exportExcel.export();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(Cell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case Cell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }
    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(XSSFCell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case HSSFCell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }
    @RequestMapping("importExcel")
    public String importExcel(MultipartFile file, HttpServletResponse response){
        //获得上传文件上传的类型
        String contentType = file.getContentType();
        //上传文件的名称
        String fileName = file.getOriginalFilename();
        //获得文件的后缀名
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件的新的路径
        //生成新的文件名称
        String filePath = "./src/main/resources/templates/fileupload/";
        //创建list集合接收excel中读取的数据
        List<UserModel> list =new ArrayList<UserModel>();
        try {
            uploadFile(file.getBytes(), filePath, fileName);
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            //通过文件忽的WorkBook
            FileInputStream fileInputStream = new FileInputStream(filePath+fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            //通过workbook对象获得sheet页 有可能不止一个sheet
            for(int i=0 ;i<workbook.getNumberOfSheets();i++){
                //获得里面的每一个sheet对象
                Sheet sheetAt = workbook.getSheetAt(i);
                //通过sheet对象获得每一行
                for(int j=3;j<sheetAt.getPhysicalNumberOfRows();j++){
                    //创建一个book对象接收excel的数据
                    UserModel userModel=new UserModel();
                    //获得每一行的数据
                    Row row = sheetAt.getRow(j);

                    //获得每一个单元格的数据

                    if(row.getCell(1)!=null && !"".equals(row.getCell(1))){
                        userModel.setUsername(this.getCellValue(row.getCell(1)));
                    }
                    if(row.getCell(2)!=null && !"".equals(row.getCell(2))){
                        userModel.setUserpass(this.getCellValue(row.getCell(2)));
                    }

                    /*if(row.getCell(5)!=null && !"".equals(row.getCell(5))){
                        String cellValue = this.getCellValue(row.getCell(4));
                        if("小说".equals(cellValue)){
                            book.setBooktypeid(1);
                        }else{
                            book.setBooktypeid(2);
                        }

                    }*/
                    list.add(userModel);
                }
            }
            userService.addUser(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "show";
    }
    //上传文件的方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
    @RequestMapping("queryLogList")
    @ResponseBody
    public DataGridResult queryLogList(Integer page, Integer rows, LogModel logModel, HttpServletRequest request){
        DataGridResult result = new DataGridResult();
        UserModel userModel = (UserModel) request.getSession().getAttribute("user");
        PageUtil pageUtil=userService.queryLogList(page,rows,logModel,userModel);
        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());
        return result;
    }

}
