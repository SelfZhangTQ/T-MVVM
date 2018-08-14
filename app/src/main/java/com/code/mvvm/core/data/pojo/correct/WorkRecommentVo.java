package com.code.mvvm.core.data.pojo.correct;

import com.code.mvvm.core.data.pojo.BaseObject;
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;

import java.util.ArrayList;
import java.util.List;

public class WorkRecommentVo extends BaseObject {

    public Data data;

    public static class Data {
        public ArrayList<WorkInfoVo> content;
        public List<CourseInfoVo> course;
        public List<LiveRecommendVo> live;
    }

}
