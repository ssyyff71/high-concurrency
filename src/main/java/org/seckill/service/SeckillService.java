package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by Sophie on 2017/7/9.
 */
public interface SeckillService {
    /**
     * 查询全部秒杀记录
     *
     * @return
     */
    List<Seckill> queryAll();

    /**
     * 查询单个秒杀记录
     *
     * @param id
     * @return
     */
    Seckill queryById(long id);

    /**
     * 是否显示秒杀的url，秒杀开启就显示，未开启就显示开始的时间和系统时间
     *
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);

    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws RepeatKillException, SeckillException, SeckillClosedException;

}
