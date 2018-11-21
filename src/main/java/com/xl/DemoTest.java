package com.xl;

import com.xl.dao.AccountMapper;
import com.xl.dao.BookMapper;
import com.xl.dao.CustomerRepository;
import com.xl.dao.RedisDao;
import com.xl.service.DemoService;
import com.xl.vo.Acoount;
import com.xl.vo.Book;
import com.xl.vo.Customer;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest  {
    @Autowired
    private DemoVO demoVO;
    @Autowired
    private ForezpVO forezpVO;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DemoService demoService;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void test(){
        System.out.println("hihhhhhi "+demoVO);
    }
    @Test
    public void forezp(){
        System.out.println("forezpVO "+forezpVO);
    }

    @Test
    public void testQuery(){
        List<Acoount> list = jdbcTemplate.query("select * from account ", new Object[]{}, new BeanPropertyRowMapper(Acoount.class));
            System.out.println("account :"+list);
    }
    @Test
    public void testMybatisQuery(){
        Acoount acoount =new Acoount();
        acoount.setName("bbb");
        System.out.println("list :"+accountMapper.list(acoount));
    }

    @Test
    public void testMybatisUpdate(){
        demoService.update();
//        accountMapper.update(1);
    }
    @Autowired
    private CustomerRepository repository;


    @Test
    public void testMongodb()  {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }

    }

    @Test
    public void testCache(){
        long start = new Date().getTime();
        System.out.println("开始。。。");
        Book book = bookMapper.getBook("月亮与六便士");
        System.out.println("book :"+book);
        Book book2 = bookMapper.getBook("白夜行");
        System.out.println("book2 :"+book2);

        Book book3 =bookMapper. getBook("月亮与六便士");
        System.out.println("book3 :"+book3);
        Book book4 =bookMapper. getBook("白夜行");
        System.out.println("book4 :"+book4);

        Book book5 = bookMapper.getBook("月亮与六便士");
        System.out.println("book5 :"+book5);
        Book book6 =bookMapper. getBook("白夜行");
        System.out.println("book6 :"+book6);
        System.out.println("end 。。。"+(new Date().getTime()-start)/1000);
    }


    @Autowired
    private RedisDao redisDao;
    @Test
    public void testRedis(){
        redisDao.setKey("name","forezp");
        redisDao.setKey("age","11");
        String name = redisDao.getValue("name");
        System.out.println("name :"+name);
    }

    @Test
    public void testRedis2(){
      List<String> strings = Lists.newArrayList();
      strings.add("张三");
        strings.add("李四");
        strings.add("王五");
        redisTemplate.opsForValue().set("list",strings);
        Object value2 = redisTemplate.opsForValue().get("list");
        System.out.println("list ："+value2);

    }

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void testRestTemplate(){
        String quote = restTemplate.getForObject(
                "http://gturnquist-quoters.cfapps.io/api/random", String.class);
        log.info(quote.toString());

    }

    @Test
//    @Transactional
    public void testTr(){
        Acoount acoount = new Acoount();
        acoount.setName("测试1");
        accountMapper.insert(acoount);
       List<Acoount> acoounts =  accountMapper.list(acoount);
       System.out.println("acoounts :"+acoounts);
       acoount.setId(acoounts.get(0).getId());
        acoount.setMoney(2132);
        accountMapper.update(acoount);
    }

}
