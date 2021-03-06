package com.lf.mp.web;

import com.lf.mp.entity.User;
import com.lf.mp.entity.Users;
import com.lf.mp.vo.TestVo;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试一级接口分组
 * create by lishengbo on 2017-12-18
 * @description 测试分组
 * 当包含 description 的时候，取注解后面的内容，否则取整个文档注释的内容
 */
@RestController
@RequestMapping("/test")
@Api(tags = "Hello Controller测试")
public class HelloController {


    @Resource
    HttpServletRequest httpServletRequest;
    /**
     * 基本访问（单路径）测试类1
     * @param name
     */
    @RequestMapping(value = "/1",method = RequestMethod.GET)
    @ApiOperation(value = "测试1", notes = "根据ID获取用户信息详细描述")
    public String say(String name ){
        System.out.print("hello");
        getRequestPrefix(httpServletRequest);
        return "Hello world!";
    }

    /**
     * 获取url请求前缀
     * @explain http://localhost:8080/test
     * @param request request对象
     * @return
     */
    public static String getRequestPrefix (HttpServletRequest request) {
        // 网络协议
        String networkProtocol = request.getScheme();
        // 网络ip
        String ip = request.getServerName();
        // 端口号
        int port = request.getServerPort();
        int port2 = request.getRemotePort();
        // 项目发布名称
        String webApp = request.getContextPath();
        String urlPrefix = networkProtocol + "://" + ip + ":" + port + webApp+"|"+port2;
        System.err.println(urlPrefix);
        return urlPrefix;
    }

    /**
     * 基本访问（单路径）测试类2
     * @param users
     */
    @RequestMapping(value = "/2",method = RequestMethod.GET)
    public User say2(Users users){
        System.out.print("hello");
        return new User();
    }
    /**
     * 基本访问（单路径）测试类3
     * @param users
     */
    @RequestMapping(value = "/3",method = RequestMethod.POST)
    public List<String> say3(@RequestBody TestVo users ){
        System.out.print("hello");
        return new ArrayList<>();
    }
    /**
     * 基本访问（单路径）测试类4
     * @param users
     */
    @RequestMapping(value = "/4",method = RequestMethod.POST)
    public TestVo say4(@RequestBody List<Users> users ){
        System.out.print("hello");
        return new TestVo();
    }


    @ApiOperation(value = "删除用户", notes = "删除用户详细描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户的唯一标识", required = true),
            @ApiImplicitParam(name = "name", value = "用户名", required = false)
    })
    @ApiResponses({
            @ApiResponse(code = 401, message = "禁止访问")
    })
    @DeleteMapping("/{id}/{name}")
    public String deleteUsers(@PathVariable("id") Long id, @PathVariable("name") String name) {
        return "删除成功";
    }

}
