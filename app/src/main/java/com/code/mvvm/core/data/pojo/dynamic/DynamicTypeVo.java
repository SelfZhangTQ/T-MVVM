package com.code.mvvm.core.data.pojo.dynamic;

import com.code.mvvm.core.data.pojo.BaseVo;
import com.code.mvvm.core.data.pojo.article.ArticleSubjectVo;
import com.code.mvvm.core.data.pojo.correct.WorkInfoVo;
import com.code.mvvm.core.data.pojo.followdraw.FollowDrawInfoVo;
import com.code.mvvm.core.data.pojo.image.ImageVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DynamicTypeVo extends BaseVo implements Serializable {

    public Data data;

    public static class Data {
        public ArrayList<CollectionItemObject> content;
    }

    public static class CollectionItemObject implements Serializable {
        public String fid;
        public String uid;
        public String tid;
        public String ctime;
        public int type;
        public WorkInfo tweet_info;
        public SubjectInfo material_info;
        public LectureInfo lecture_info;
        public ArticileInfo activity_article_info;
        public LessonInfo course_info;
        public LiveInfo live_info;
        public ArticleSubjectVo.DataBean.SubjectInfoBean lecture_subject_info;
        public FollowDrawInfoVo lesson_info;
    }


    /**
     * 帖子
     */
    public static class WorkInfo implements Serializable {
        public String resource_id;
        public String dtime;
        public String uid;
        public String tid;
        public String is_del;
        public String img;
        public String s_catalog_id;
        public String flag;
        public String f_catalog_id;
        public String f_catalog;
        public List<String> tags;
        public String utime;
        public String title;
        public String ctime;
        public String comment_num;
        public String content;
        public ImageVo imgs;
        public String hits;
        public String s_catalog;
        public int type;
        public String correctid;
        public int lessonid;
        public WorkInfoVo correct;
        public int picnum;
        public String sname;
        public String avatar;
        public int ukind;
        public String intro;
        public int ukind_verify;
        public int featureflag;
        public String genderid;
        public String provinceid;
        public String professionid;
        public String gender;
        public String province;
        public String profession;
        public String gradeid;
        public String remain_coin;
        public Share share;
        public Comment comment;
        public Praise praise;
        public int follow_type;
        public int istag;
        public int fav;
        /**
         * 评论集合
         */
        public int is_digest;
        public int is_recommand;
        public int is_top;
        /**
         * 图片集合
         */
        public ArrayList<ImageVo> imgs_list;

        public String fid;
    }

    /**
     * 专题
     */
    public static class SubjectInfo {
        public String imgcount;
        public String status;
        public String title;
        public String subjectid;
        public String ctime;
        public String hits;
        public String rids;
        public ImageVo picurl;
    }

    /**
     * 文章
     */
    public static class LectureInfo {
        public String newsid;
        public String catid;
        public String title;
        public String thumb;
        public String thumbtype;
        public String keywords;
        public String desc;
        public String listorder;
        public String status;
        public String username;
        public String ctime;
        public String utime;
        public String url;
        public String hits;
        public String content_type;
        public ArrayList<Img> img;
    }

    /**
     * 活动文章
     */
    public static class ArticileInfo {
        public String hits;
        public String title;
        public String listorder;
        public String cover_type;
        public String ctime;
        public String desc;
        public String catid;
        public String thumb;
        public String newsid;
        public String activity_type;
        public String articleid;
        public String status;
        public String username;
        public String reserve1;
        public String copyfrom;
        public String supportcount;
        public String keywords;
        public String cmtcount;
        public String utime;
        public String reserve2;
        public String reserve3;
        public String fav;
        public String share_url;
        public ArrayList<Img> imgs;
        public String url;
    }

    /**
     * 课程
     */
    public static class LessonInfo implements Serializable {
        public String cmtcount;
        public String share_desc;
        public String share_title;
        public String status;
        public String f_catalog_id;
        public String share_img;
        public String teacheruid;
        public String hits;
        public String title;
        public String courseid;
        public String s_catalog_id;
        public String ctime;
        public String hits_basic;
        public String desc;
        public String thumb_url;
        public String f_catalog;
        public String supportcount;
        public String s_catalog;
        public String video_legth;
        public String is_free;
        public String buy_type;
        public String buy_status;
        public UserinfoBean userinfo;
        public String video_price;
    }

    /**
     * 直播
     */
    public static class LiveInfo implements Serializable {
        public String live_price;
        public String live_display_url;
        public String end_time;
        public String status;
        public String username;
        public String f_catalog_id;
        public String liveid;
        public String live_push_url;
        public String recording_price;
        public String videoid;
        public String start_time;
        public String ctime;
        public String supportcount;
        public String teacher_desc;
        public String cmtcount;
        public String share_desc;
        public String live_thumb_url;
        public String share_title;
        public String live_title;
        public String share_img;
        public String teacheruid;
        public String adminuid;
        public String hits;
        public String live_content;
        public String s_catalog_id;
        public String hits_basic;
        public String recording_thumb_url;
        public Integer live_status;
        public UserinfoBean userinfo;
        public VideoinfoBean videoinfo;
        public String buy_status;
        public String live_sign_url;
        public String live_url;
        public String fav;
        public List<ScanusersBean> scanusers;
        public String live_sign_status;
    }

    /**
     * 视频信息
     */
    public static class VideoinfoBean implements Serializable {

        public String m3u8url;
        public String filename;
        public String status;
        public String subtype;
        public String runid;
        public String video_length;
        public String videoid;
        public String maintype;
        public String ctime;
        public String video_type;
        public String coverpic;
        public String sourceurl;
        public String desc;
    }

    /**
     * 围观用户信息
     */
    public static class ScanusersBean implements Serializable {

        public String genderid;
        public String role_type;
        public String uid;
        public String provinceid;
        public String city;
        public String school;
        public String featureflag;
        public String professionid;
        public String avatar;
        public String sname;
        public String intro;
        public String ukind_verify;
        public String ukind;
        public String province;
        public String gender;
        public String profession;
    }

    /**
     * 用户信息
     */
    public static class UserinfoBean implements Serializable {

        public String genderid;
        public String role_type;
        public String uid;
        public String provinceid;
        public String city;
        public String school;
        public String featureflag;
        public String professionid;
        public String avatar;
        public String sname;
        public String intro;
        public String ukind_verify;
        public String ukind;
        public String province;
        public String gender;
        public String profession;
    }

    /**
     * 图片对象
     */
    public static class Img {
        public String img;
        public String description;
        public String resource_type;
        public String rid;
    }

    /**
     * 转发
     */
    public static class Share {
        public int num;

        public String url;

    }

    /**
     * 评论
     */
    public static class Comment {
        public int num;
    }

    /**
     * 点赞
     */
    public static class Praise {
        public int num;

        public boolean flag;

    }
}
