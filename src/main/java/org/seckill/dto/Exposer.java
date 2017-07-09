package org.seckill.dto;

import java.util.Date;

/**
 * Created by Sophie on 2017/7/9.
 */
public class Exposer {
    //是否开始秒杀
    private boolean exposed;

    //加密使用md5
    private String md5;

    //进入系统时间
    private Date now;

    //秒杀开始时间
    private long start;

    //秒杀结束时间
    private long end;

    //id为seckillId的商品的秒杀地址
    private long seckillId;

    public Exposer(boolean exposed, Date now, long start, long end, long seckillId) {
        this.exposed = exposed;

        this.now = now;
        this.start = start;
        this.end = end;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                ", seckillId=" + seckillId +
                '}';
    }

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
