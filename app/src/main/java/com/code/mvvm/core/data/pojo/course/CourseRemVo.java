package com.code.mvvm.core.data.pojo.course;

import com.code.mvvm.core.data.pojo.BaseVo;
import com.code.mvvm.core.data.pojo.banner.BannerVo;
import com.code.mvvm.core.data.pojo.user.UserInfoVo;

import java.util.List;


public class CourseRemVo extends BaseVo {

    public DataBean data;

    public static class DataBean {
        public List<BannerVo> top_adv;

        public List<LiveRecommendBean> live_recommend;

        public List<LiveOnlineBean> live_online;

        public List<CourseRecommendBean> course_recommend;
    }


    public static class LiveRecommendBean {
        public String status;
        public String start_time;
        public String live_content;
        public String live_thumb_url;
        public String s_catalog_id;
        public String ctime;
        public String live_price;
        public String supportcount;
        public String live_title;
        public String share_desc;
        public String adminuid;
        public String liveid;
        public String recording_thumb_url;
        public String hits_basic;
        public String videoid;
        public String live_push_url;
        public String hits;
        public String live_sign_count;
        public String teacheruid;
        public String teacher_desc;
        public String buy_status;
        public String share_img;
        public String username;
        public String recording_price;
        public String f_catalog_id;
        public String end_time;
        public String cmtcount;
        public String share_title;
        public String live_display_url;
        public String live_status;
        public UserInfoVo userinfo;
        public List<ScanusersBean> scanusers;
        public VideoinfoBean videoinfo;
        public String live_sign_url;
        public String live_url;
        public int fav;
    }

    public static class LiveOnlineBean {
        public String status;
        public String start_time;
        public String live_content;
        public String live_thumb_url;
        public String s_catalog_id;
        public String ctime;
        public String live_price;
        public String supportcount;
        public String live_title;
        public String share_desc;
        public String adminuid;
        public String liveid;
        public String recording_thumb_url;
        public String hits_basic;
        public String videoid;
        public String live_push_url;
        public String hits;
        public String teacheruid;
        public String teacher_desc;
        public String share_img;
        public String username;
        public String recording_price;
        public String f_catalog_id;
        public String end_time;
        public String cmtcount;
        public String share_title;
        public String live_display_url;
        public String live_status;
        public UserInfoVo userinfo;
        public VideoinfoBean videoinfo;
        public String live_sign_url;
        public String live_url;
        public int fav;
        public List<ScanusersBean> scanusers;
    }

    public static class CourseRecommendBean {
        public String recommendid;
        public String f_catalog_id;
        public String s_catalog_id;
        public String ctime;
        public String sort_id;
        public String f_catalog;
        public String s_catalog;
        public List<CourseInfoVo> course_list;
    }

    public static class VideoinfoBean {
    }

    public static class ScanusersBean {
        public String school;
        public String uid;
        public String avatar;
        public String city;
        public String provinceid;
        public String featureflag;
        public String genderid;
        public String ukind;
        public String ukind_verify;
        public String sname;
        public String intro;
        public String role_type;
        public String professionid;
        public String province;
        public String gender;
        public String profession;

    }
}
