package org.seckill.enums;

/**
 * Created by Sophie on 2017/7/9.
 */
public enum State {
    SUCCESS(1, "秒杀成功"),
    END(0, "重复秒杀"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATE_REWRITE(-3, "数据篡改");

    private int state;

    private String stateInfo;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }


    State(int state, String stateInfo) {

        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static State stateOf(int index) {
        for (State state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
