package com.trecyclerview.entity;

/**
 * @author：zhangtianqiu on 18/7/13 17:58
 */
public class FootInfo {
    public String desc;

    public int state;

    public FootInfo(int state) {
        this.state = state;
    }

    public FootInfo(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }
}
