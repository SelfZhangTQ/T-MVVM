package com.code.mvvm.core.data.pojo.dynamic;

import com.code.mvvm.core.data.pojo.article.ArticleInfoVo;
import com.code.mvvm.core.data.pojo.correct.WorkInfoVo;
import com.code.mvvm.core.data.pojo.correct.WorksListVo;
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawInfoVo;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.core.data.pojo.material.MatreialSubjectVo;
import com.code.mvvm.core.data.pojo.user.UserInfoVo;

public class DynamicInfoVo {
    public String feedid;
    public String uid;
    public String action_type;
    public String subjecttype;
    public String subjectid;
    public String title;
    public String ctime;
    public UserInfoVo userinfo;
    public WorksListVo.Works tweet_info;
    public WorkInfoVo correct_info;
    public MatreialSubjectVo material_info;
    public ArticleInfoVo lecture_info;
    public LiveRecommendVo live_info;
    public CourseInfoVo course_info;
    public FollowDrawInfoVo lesson_info;
}
