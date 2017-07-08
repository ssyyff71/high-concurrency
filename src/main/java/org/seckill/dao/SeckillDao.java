package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by Sophie on 2017/7/8.
 */
public interface SeckillDao {
    /**
     * 通过id查询商品信息
     *
     * @param seckillId
     * @return
     */
    Seckill selectBySeckillId(long seckillId);

    /**
     * 减库存
     *
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据偏移量查询
     *
     * @param off
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("off") int off, @Param("limit") int limit);
}
