package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.State;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * Created by Sophie on 2017/7/9.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private final static Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Resource
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    public List<Seckill> queryAll() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill queryById(long id) {
        return seckillDao.selectBySeckillId(id);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date now = new Date();

        if (now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()) {
            return new Exposer(false, now, startTime.getTime(), endTime.getTime(), seckillId);
        }
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    public String getMd5(long seckillId) {
        String salt = "saobcskknacb";
        String md5 = seckillId + '/' + salt;
        return DigestUtils.md5DigestAsHex(md5.getBytes());
    }

    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws RepeatKillException, SeckillException, SeckillClosedException {
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }

        //执行秒杀逻辑
        Date now = new Date();

        try {
            int update = seckillDao.reduceNumber(seckillId, now);
            if (update <= 0) {
                throw new SeckillClosedException("seckill is closed");
            } else {
                int insert = successKilledDao.insertDetail(seckillId, userPhone);

                if (insert <= 0) {
                    throw new RepeatKillException("seckill repeated");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, State.SUCCESS, successKilled);
                }
            }
        } catch (SeckillClosedException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (SeckillException e3) {
            logger.error(e3.getMessage(), e3);
            throw new SeckillException("seckill inner error :" + e3.getMessage());
        }

    }
}
