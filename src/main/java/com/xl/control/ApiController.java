package com.xl.control;

import com.xl.dao.CustomerRepository;
import com.xl.vo.Customer;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
@RestController
@RequestMapping("api")
@Api(value= "api的类")
public class ApiController {
    @Autowired
    private CustomerRepository repository;

    @ApiOperation(value = "value :获取mongodb中的list ",notes = "notes :获取mongodb中的list")
    @GetMapping("list")
    public  List<Customer> list(@ApiParam("传入要查询的name") String name){
        List<Customer> customers = repository.findByLastName(name);
        return customers;
    }


    @ApiOperation(value="更新信息", notes="根据url的id来指定更新图书信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "顾客id", required = true, dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "customer", value = "姓名", required = true, dataType = "Customer")
    })
    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    public String putUser(@PathVariable String id,@RequestBody Customer customer) {

        return "success";
    }

    @GetMapping("ttt")
    @ApiIgnore
    public String ttt(){
        return "2321312";
    }
}
