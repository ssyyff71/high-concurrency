package org.seckill.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Sophie on 2017/7/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/mybatis-spring.xml")
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void selectBySeckillId() throws Exception {
        long seckillId = 1000L;
        Seckill seckill = seckillDao.selectBySeckillId(seckillId);
        System.out.println(seckill);
    }

    @Test
    public void reduceNumber() throws Exception {
        long seckillId = 1000L;
        Date killTime = new Date();
        int i = seckillDao.reduceNumber(seckillId, killTime);
        System.out.println(i);
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(1, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }

}