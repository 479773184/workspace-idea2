/**
 * Copyright (C), 2019, 金科教育
 * FileName: BookController
 * Author:   zyl
 * Date:     2019/8/12 14:21
 * History:
 * zyl          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.controller;

import com.jk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author zgm
 * @create 2019/8/12
 * @since 1.0.0
 */
@Controller
public class BookController {


    @Autowired
    private BookService bookService;
    @RequestMapping("toShow")
    public String toShow(){
        return "show";
    }
    @RequestMapping("tomm")
    public String tomm(){
        return "mm";
    }
    @RequestMapping("tozhou")
    public String tozhou(){
        return "zhou";
    }

    @RequestMapping("queryBook")
    @ResponseBody
    public List<Map<String,Object>> queryBook(){
       List<Map<String,Object>> list= bookService.queryBook();
        List<Map<String,Object>> list1=new ArrayList<>();
        for (Map<String,Object> map1:list) {
            System.err.println(11111);
            System.err.println(222);
            Map<String,Object> map=new HashMap<>();
            Integer  object = Integer.parseInt(map1.get("年").toString()) ;
            if(object==2017){
                map.put("name","2017") ;
                int [] aa =new int[]{200,600,900};
                map.put("data",aa);
            }else if(object==2018){
                map.put("name","2018") ;
                int [] aa =new int[]{300,200,700};
                map.put("data",aa);
            }else {
                map.put("name","2019") ;
                int [] aa =new int[]{100,200,400};
                map.put("data",aa);
            }
            map.put("name",map1.get("年").toString()) ;
            list1.add(map);
        }
       return list1;
    }
    @RequestMapping("queryMM")
    @ResponseBody
    public List<Map<String,Object>> queryMM(){
        List<Map<String,Object>> list= bookService.queryMM();
        List<Map<String,Object>> list1=new ArrayList<>();
        for (Map<String,Object> map1:list) {
            Map<String,Object> map=new HashMap<>();
            map.put("name",map1.get("月"));
            map.put("y",map1.get("总个数"));
            list1.add(map);
        }
        return list1;
    }
    /**
     * 柱状图
     * @return
     */
    @RequestMapping("queryZhu")
    @ResponseBody
    public List<Map<String,Object>> queryZhu(){
        List<Map<String,Object>> list = bookService.queryZhu();
        List<Map<String,Object>> list1 = new ArrayList<>();
        for (Map<String,Object> map1:list
        ) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name",map1.get("周"));
            map.put("data",new int[]{Integer.parseInt(map1.get("总个数").toString())});
            list1.add(map);
        }
        return list1;
    }

}
