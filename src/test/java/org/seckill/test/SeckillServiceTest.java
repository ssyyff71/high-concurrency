package org.seckill.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Sophie on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SeckillService seckillService;


    @Test
    public void queryAll() throws Exception {

        List<Seckill> seckills = seckillService.queryAll();
        for (Seckill seckill : seckills) {
            System.out.println(seckills);
        }
    }

    @Test
    public void queryById() throws Exception {
        long id = 1000L;
        Seckill seckill = seckillService.queryById(id);
        System.out.println(seckill);

    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        System.out.println(exposer);
    }

    @Test
    public void executeSeckill() throws Exception {
        long id = 1000L;
        long userPhone = 12345678991L;
        String md5 = "111nnnkksal;?";
        SeckillExecution seckillExecution = seckillService.executeSeckill(id, userPhone, md5);
        System.out.println(seckillExecution);
    }

}