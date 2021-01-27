package com.jutixueyuan.api;

import com.jutixueyuan.annotation.IncludeSwagger;
import com.jutixueyuan.bean.Emp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄药师
 * @date 2021-01-26 15:38
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
//@IncludeSwagger
@Api(description = "emp的模块的api文档")
@RestController
@RequestMapping("emp")
public class EmpController {

    /**
     * @ApiParam(name = ,value = ,required =
     *    name 是参数的名称
     *    value 值
     *    required 是否是必须
     * @param id
     * @param name
     * @return
     */
    @ApiOperation(value ="这个方法是获取emp对象")
    @PostMapping("getemp")
    public Emp getemp(@ApiParam(name ="id" ,value ="编号" ,required = true) Integer id, String name) {

        Emp emp = new Emp();
        emp.setId(id);
        emp.setName(name);
        emp.setInfo("你们都喜欢丝袜");
        emp.setAge(22);
        return emp;
    }
}
