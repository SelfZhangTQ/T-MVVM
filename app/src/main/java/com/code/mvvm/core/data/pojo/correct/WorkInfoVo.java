package com.code.mvvm.core.data.pojo.correct;


import com.code.mvvm.core.data.pojo.user.UserInfoVo;

import java.io.Serializable;

public class WorkInfoVo implements Serializable {

    public String correctid;

    public String tid;

    public String teacheruid;

    public String content;
    public String ctime;

    public String status;
    public UserInfoVo teacher_info;
    public String fav;

    public String title;

    public WorkPicVo source_pic;

    public WorkPicVo correct_pic;
    public String follow_type;
    public UserInfoVo tweet_info;

}
