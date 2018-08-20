package com.code.mvvm.core.data.pojo.live;

import com.code.mvvm.core.data.pojo.BaseVo;
import com.code.mvvm.core.data.pojo.user.UserInfoVo;

import java.util.List;


public class LiveDetailsVo extends BaseVo {


    /**
     * data : {"teacher_desc":"2017年3月27预告测试","hits_basic":"100","cmtcount":"0","live_content":"<p>2017年3月27预告测试<\/p>","live_price":"0.00","start_time":"1490600040","adminuid":"146138","hits":"100","recording_thumb_url":"https://img.meiyuanbang.com/cms/video/2017-03-27/0907E347A95CD2623D3917F72FDF4E3F.png","recording_price":"0.00","status":"1","teacheruid":"15965","s_catalog_id":"109","share_img":"https://img.meiyuanbang.com/cms/video/2017-03-27/1A3E7E03007DFC39EB3C3B445729CCD1.png","username":"管理员","live_title":"2017年3月27预告测试","share_desc":"2017年3月27预告测试","end_time":"1491032100","supportcount":"0","liveid":"43","f_catalog_id":"2","live_thumb_url":"https://img.meiyuanbang.com/cms/video/2017-03-27/81E8B10D78CD85709CAF3EF9C6B093E6.png","videoid":"","ctime":"1490600265","share_title":"2017年3月27预告测试","live_push_url":"rtmp://video-center.alivecdn.com/myb/livecn_43?vhost=live.meiyuanbang.com&auth_key=1491032100-0-0-c7cb457930b33f15304f133e439f31e6","live_display_url":"http://live.meiyuanbang.com/myb/livecn_43.m3u8?auth_key=1491032100-0-0-cde99da1f557921c5a8ce63f1ff56641","live_sign_count":"1","live_status":2,"userinfo":{"featureflag":"0","ukind_verify":"0","professionid":"4","intro":"","provinceid":"6","uid":"15965","role_type":"1","genderid":"0","school":"鲁迅美术学院","avatar":"https://img.meiyuanbang.com//user/2016-01-19/F3B151CC64202B94C7FA0ABC838D6602.jpg","sname":"\"熙\"","ukind":"1","city":"","province":"辽宁","gender":"男","profession":"大学"},"scanusers":[],"live_sign_status":"0","videoinfo":{},"live_url":"https://testm.meiyuanbang.com/video/live/live_trailer?liveid=43","live_sign_url":"https://testm.meiyuanbang.com/video/live/live_trailer?liveid=43&type=1","fav":0,"rtmp_url":"rtmp://live.meiyuanbang.com/myb/livecn_43?auth_key=1491032100-0-0-838b1a1c8968a02b5cda96ba123b2dda"}
     */


    public DataBean data;

    public static class DataBean {


        /**
         * teacher_desc : 2017年3月27预告测试
         * hits_basic : 100
         * cmtcount : 0
         * live_content : <p>2017年3月27预告测试</p>
         * live_price : 0.00
         * start_time : 1490600040
         * adminuid : 146138
         * hits : 100
         * recording_thumb_url : https://img.meiyuanbang.com/cms/video/2017-03-27/0907E347A95CD2623D3917F72FDF4E3F.png
         * recording_price : 0.00
         * status : 1
         * teacheruid : 15965
         * s_catalog_id : 109
         * share_img : https://img.meiyuanbang.com/cms/video/2017-03-27/1A3E7E03007DFC39EB3C3B445729CCD1.png
         * username : 管理员
         * live_title : 2017年3月27预告测试
         * share_desc : 2017年3月27预告测试
         * end_time : 1491032100
         * supportcount : 0
         * liveid : 43
         * f_catalog_id : 2
         * live_thumb_url : https://img.meiyuanbang.com/cms/video/2017-03-27/81E8B10D78CD85709CAF3EF9C6B093E6.png
         * videoid :
         * ctime : 1490600265
         * share_title : 2017年3月27预告测试
         * live_push_url : rtmp://video-center.alivecdn.com/myb/livecn_43?vhost=live.meiyuanbang.com&auth_key=1491032100-0-0-c7cb457930b33f15304f133e439f31e6
         * live_display_url : http://live.meiyuanbang.com/myb/livecn_43.m3u8?auth_key=1491032100-0-0-cde99da1f557921c5a8ce63f1ff56641
         * live_sign_count : 1
         * live_status : 2
         * userinfo : {"featureflag":"0","ukind_verify":"0","professionid":"4","intro":"","provinceid":"6","uid":"15965","role_type":"1","genderid":"0","school":"鲁迅美术学院","avatar":"https://img.meiyuanbang.com//user/2016-01-19/F3B151CC64202B94C7FA0ABC838D6602.jpg","sname":"\"熙\"","ukind":"1","city":"","province":"辽宁","gender":"男","profession":"大学"}
         * scanusers : []
         * live_sign_status : 0
         * videoinfo : {}
         * live_url : https://testm.meiyuanbang.com/video/live/live_trailer?liveid=43
         * live_sign_url : https://testm.meiyuanbang.com/video/live/live_trailer?liveid=43&type=1
         * fav : 0
         * rtmp_url : rtmp://live.meiyuanbang.com/myb/livecn_43?auth_key=1491032100-0-0-838b1a1c8968a02b5cda96ba123b2dda
         */

        public String playtype;
        public String teacher_desc;
        public String hits_basic;
        public String cmtcount;
        public String live_content;
        public String live_price;
        public String start_time;
        public String adminuid;
        public String hits;
        public String recording_thumb_url;
        public String recording_price;
        public String status;
        public String teacheruid;
        public String s_catalog_id;
        public String share_img;
        public String username;
        public String live_title;
        public String share_desc;
        public String end_time;
        public String supportcount;
        public String liveid;
        public String f_catalog_id;
        public String live_thumb_url;
        public String videoid;
        public String ctime;
        public String share_title;
        public String live_push_url;
        public String live_display_url;
        public String live_sign_count;
        public int live_status;
        public UserInfoVo userinfo;
        public String live_sign_status;
        public LiveInfoVo videoinfo;
        public String live_url;
        public String live_sign_url;
        public int fav;
        public String rtmp_url;
        public String buy_status;
        public String follow_type;
        public List<?> scanusers;
//        public List<HeadAdList.Content> adv_info;
        public ContactInfo customer_service;



    }

    public static class ContactInfo{
        public String qq;
        public String qq_name;
        public String qq_qun;
        public String qq_qun_name;
        }

}
