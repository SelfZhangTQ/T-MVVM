package com.code.mvvm.core.data.pojo.home;

import com.code.mvvm.core.data.pojo.BaseVo;
import com.code.mvvm.core.data.pojo.article.ArticleInfoVo;
import com.code.mvvm.core.data.pojo.banner.BannerVo;
import com.code.mvvm.core.data.pojo.book.BookVo;
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.core.data.pojo.image.ImageInfoVo;
import com.code.mvvm.core.data.pojo.live.LiveRecommendVo;
import com.code.mvvm.core.data.pojo.material.MatreialSubjectVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author：tqzhang
 */
public class HomeListVo extends BaseVo implements Serializable {

    public DataBean data;

    public static class DataBean {
        public List<BannerVo> data;
        public List<CourseInfoVo> course;
        public List<ArticleInfoVo> lecture;
        public List<LessonBean> lesson;
        public List<BookVo> publishingbook;
        public List<MatreialSubjectVo> matreialsubject;
        public List<LiveRecommendVo> live_recommend;

        public static class LessonBean implements Serializable {
            /**
             * lessonid : 10074
             * title : 30分钟速写强化训练：蹲姿男骚年
             * desc :
             * maintype : 5
             * subtype : 133
             * hits : 0
             * cmtcount : 0
             * supportcount : 0
             * status : 2
             * username : 管理员
             * ctime : 1447597314
             * sectionids : 230,231,232,233,234
             */

            public String lessonid;
            public String title;
            public String desc;
            public String maintype;
            public String subtype;
            public String hits;
            public String cmtcount;
            public String supportcount;
            public String status;
            public String username;
            public String ctime;
            public String sectionids;
            public ImageItem imgs;

            public static class ImageItem implements Serializable {
                public ImageInfoVo l;
            }

        }

        public static class PublishingbookBean implements Serializable {
            /**
             * newsid : 14947
             * catid : 6
             * title : 对路速写2
             * thumb : 638508
             * keywords :
             * desc :
             * listorder : 0
             * status : 1
             * username : 高文谦
             * ctime : 1482976676
             * utime : 0
             * hits : null
             * cmtcount : 0
             * supportcount : null
             * copyfrom : 祁达 朱丹
             * reserve1 : null
             * reserve2 : null
             * reserve3 : null
             * bookid : 14
             * uid : 124166
             * buy_url :
             * f_catalog_id : 5
             * s_catalog_id : 133
             * publishing_name : 中国美术学院出版社
             * price : 79.00
             * img : {"n":{"url":"https://img.meiyuanbang.com/cms/activity/2016-12-29/B2AA80DB94E44084214F73ECDFC85F77.jpg","w":2006,"h":1447},"l":{"url":"https://img.meiyuanbang.com/cms/activity/2016-12-29/B2AA80DB94E44084214F73ECDFC85F77.jpg@400h_2o","h":400,"w":554}}
             * url : https://testm.meiyuanbang.com/publishing/book_detail?bookid=14
             * f_catalog : 速写
             * s_catalog : 人物速写
             */

            public String newsid;
            public String catid;
            public String title;
            public String thumb;
            public String keywords;
            public String desc;
            public String listorder;
            public String status;
            public String username;
            public String ctime;
            public String utime;
            public Object hits;
            public String cmtcount;
            public Object supportcount;
            public String copyfrom;
            public Object reserve1;
            public Object reserve2;
            public Object reserve3;
            public String bookid;
            public String uid;
            public String buy_url;
            public String f_catalog_id;
            public String s_catalog_id;
            public String publishing_name;
            public String price;
            public ImgBeanX img;
            public String url;
            public String f_catalog;
            public String s_catalog;


            public static class ImgBeanX {
                /**
                 * n : {"url":"https://img.meiyuanbang.com/cms/activity/2016-12-29/B2AA80DB94E44084214F73ECDFC85F77.jpg","w":2006,"h":1447}
                 * l : {"url":"https://img.meiyuanbang.com/cms/activity/2016-12-29/B2AA80DB94E44084214F73ECDFC85F77.jpg@400h_2o","h":400,"w":554}
                 */

                public NBean n;
                public LBean l;

                public static class NBean {
                    /**
                     * url : https://img.meiyuanbang.com/cms/activity/2016-12-29/B2AA80DB94E44084214F73ECDFC85F77.jpg
                     * w : 2006
                     * h : 1447
                     */

                    public String url;
                    public int w;
                    public int h;
                }

                public static class LBean {
                    /**
                     * url : https://img.meiyuanbang.com/cms/activity/2016-12-29/B2AA80DB94E44084214F73ECDFC85F77.jpg@400h_2o
                     * h : 400
                     * w : 554
                     */

                    public String url;
                    public int h;
                    public int w;
                }
            }
        }

        public static class Matreialsubject implements Serializable {

            public int subjectid;
            public String title;
            public PicurlBean picurl;
            public String rids;
            public int hits;
            public int ctime;
            public int status;
            public String stick_date;
            public String subject_typeid;
            public String material_desc;
            public int cmtcount;
            public int supportcount;

            public static class PicurlBean {
                /**
                 * n : {"h":981,"w":1954,"url":"http://img.meiyuanbang.com/cms/activity/2016-09-03/BB8A00419CF039D7809E6CDED06E1613.jpg"}
                 */

                public TBean t;

                public static class TBean {
                    public int h;
                    public int w;
                    public String url;
                }

                public LBean l;

                public static class LBean {
                    public int h;
                    public int w;
                    public String url;
                }

                public NBean n;

                public static class NBean {
                    public int h;
                    public int w;
                    public String url;
                }
            }
        }

        public static class LiveRecommendBean implements Serializable {
            /**
             * live_thumb_url : https://img.meiyuanbang.com/cms/video/2017-04-05/A03B291373943A7EC028BD101189FB6C.png
             * share_img : https://img.meiyuanbang.com/cms/video/2017-04-05/925C54E43050403A3D5739CB0FB1CCE4.png
             * s_catalog_id : 126
             * cmtcount : 0
             * recording_price : 0.02
             * start_time : 1526006880
             * live_content : <p>预告测试</p>
             * videoid :
             * f_catalog_id : 4
             * teacher_desc : 预告测试
             * hits : 100
             * hits_basic : 100
             * share_title : 预告测试
             * status : 1
             * live_price : 0.01
             * supportcount : 0
             * adminuid : 146138
             * live_display_url : http://live.meiyuanbang.com/myb/livecn_52.m3u8?auth_key=1527821280-0-0-1ec60c00d799dba1041567030eb7a6ba
             * ctime : 1491360610
             * live_push_url : rtmp://video-center.alivecdn.com/myb/livecn_52?vhost=live.meiyuanbang.com&auth_key=1527821280-0-0-ae61990185704d99d7888676b5dd093e
             * teacheruid : 1010
             * share_desc : 预告测试
             * end_time : 1527821280
             * advid : 31
             * liveid : 52
             * recording_thumb_url : https://img.meiyuanbang.com/cms/video/2017-04-05/F01BCAF1452AEAB64D919F40B0DDD25B.png
             * live_title : 预告测试
             * username : 管理员
             * live_sign_count : 23
             * live_status : 1
             * userinfo : {"avatar":"https://img.meiyuanbang.com//user/2015-09-29/54900B70DA735BCB511647F78B66B5D6.jpg","ukind_verify":"1","school":"北京周达画室","sname":"周达","ukind":"1","uid":"1010","featureflag":"0","city":"北京","professionid":"5","provinceid":"1","studio_type":"0","role_type":"1","genderid":"0","intro":"","province":"北京市","gender":"男","profession":"老师"}
             * scanusers : []
             * live_sign_status : 0
             * videoinfo : {}
             * buy_status : 1
             * live_url : https://testm.meiyuanbang.com/video/live/live_trailer?liveid=52
             * live_sign_url : https://testm.meiyuanbang.com/video/live/live_trailer?liveid=52&type=1
             * fav : 0
             * rtmp_url : rtmp://live.meiyuanbang.com/myb/livecn_52?auth_key=1527821280-0-0-ae61990185704d99d7888676b5dd093e
             * adv_info : [null]
             * follow_type : 0
             */

            public String live_thumb_url;
            public String share_img;
            public String s_catalog_id;
            public String cmtcount;
            public String recording_price;
            public String start_time;
            public String live_content;
            public String videoid;
            public String f_catalog_id;
            public String teacher_desc;
            public String hits;
            public String hits_basic;
            public String share_title;
            public String status;
            public String live_price;
            public String supportcount;
            public String adminuid;
            public String live_display_url;
            public String ctime;
            public String live_push_url;
            public String teacheruid;
            public String share_desc;
            public String end_time;
            public String advid;
            public String liveid;
            public String recording_thumb_url;
            public String live_title;
            public String username;
            public String live_sign_count;
            public int live_status;
            public UserinfoBean userinfo;
            public String live_sign_status;
            public VideoinfoBean videoinfo;
            public String buy_status;
            public String live_url;
            public String live_sign_url;
            public int fav;
            public String rtmp_url;
            public int follow_type;
            public List<Scanusers> scanusers;
//            public List<Null> adv_info;

            public static class Scanusers {
                public String ukind_verify;
                public String school;
                public String ukind;
                public String provinceid;
                public String intro;
                public String city;
                public String genderid;
                public String avatar;
                public String sname;
                public String uid;
                public String studio_type;
                public String role_type;
                public String featureflag;
                public String professionid;
                public String province;
                public String gender;
                public String profession;
            }

            public static class VideoinfoBean {
                public String status;
                public String desc;
                public String coverpic;
                public String m3u8url;
                public String subtype;
                public String filename;
                public String ctime;
                public String maintype;
                public String video_type;
                public String sourceurl;
                public String runid;
                public int video_size;
                public String video_length;
                public String videoid;
            }
        }

        public static class UserinfoBean implements Serializable {
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

        public static class LiveOnlineBean implements Serializable {
            /**
             * liveid : 84
             * teacheruid : 1
             * live_title : 东昱画室美院帮视频公开课测试
             * teacher_desc : 东昱名师李峰老师直播课测试
             * live_thumb_url : https://img.meiyuanbang.com/cms/video/2017-05-04/4340622E041CC07BB272059D81F7FC6F.jpg
             * recording_thumb_url : https://img.meiyuanbang.com/cms/video/2017-05-04/AF6C1FFD4F64B5C00DC3EDF6FF85FEBA.jpg
             * f_catalog_id : 4
             * s_catalog_id : 129
             * live_content : <p><img src="https://img.meiyuanbang.com/cms/ueditor2017-05-04/CA22B1A94CD0E182700D2FBD62DFC180.jpg" title="CA22B1A94CD0E182700D2FBD62DFC180.jpg" alt="微信图片_20170503141735.jpg"/></p>
             * live_price : 0.00
             * recording_price : 0.00
             * live_push_url : rtmp://video-center.alivecdn.com/myb/livecn_84?vhost=live.meiyuanbang.com&auth_key=1493887440-0-0-6a4000458036d3d6422d0c3405b03be3
             * live_display_url : http://live.meiyuanbang.com/myb/livecn_84.m3u8?auth_key=1493887440-0-0-13703cb2235cf0c30c81d92e9d23fb96
             * videoid : null
             * start_time : 1493881800
             * end_time : 1493887440
             * hits_basic : 0
             * hits : 0
             * supportcount : 0
             * cmtcount : 0
             * adminuid : 10434
             * username : 郭维佳
             * ctime : 1493881436
             * status : 1
             * share_title : 东昱名师李峰老师公开课测试
             * share_desc : 课程概述：东昱状元名师李峰教你如何雕琢一幅高分素描头像。、讲师介绍：李峰，毕业于中国美术学院，8年高考美术教学经验。教学成果：所教学生美院过线率达83.7%，斩获中国美院，四川美院和西安美院状元。个人参展：世纪之星油画创作展，巍巍太行山主题油画展。出版书物：《完美教学》《沙河画家》等。
             * share_img : https://img.meiyuanbang.com/cms/video/2017-05-04/200B9B01978B239D1330AC891BF2AB3D.jpg
             * advid : null
             * live_sign_count : 0
             * live_status : 2
             * userinfo : {"avatar":"https://img.meiyuanbang.com//user/2017-03-16/A284F4D589606BBD7A512A011D4720FC.jpg","ukind_verify":"1","school":"CAFA","sname":"美院帮老师测试","ukind":"1","uid":"1","featureflag":"0","city":"Beijing","professionid":"5","provinceid":"1","studio_type":"2","role_type":"1","genderid":"0","intro":"人努力，天帮忙！","province":"北京市","gender":"男","profession":"老师"}
             * scanusers : []
             * live_sign_status : 0
             * videoinfo : {}
             * buy_status : 1
             * live_url : https://testm.meiyuanbang.com/video/live/live_stream?liveid=84
             * live_sign_url : https://testm.meiyuanbang.com/video/live/live_stream?liveid=84&type=1
             * fav : 0
             * rtmp_url : rtmp://live.meiyuanbang.com/myb/livecn_84?auth_key=1493887440-0-0-6a4000458036d3d6422d0c3405b03be3
             * adv_info : []
             * follow_type : 0
             */

            public String liveid;
            public String teacheruid;
            public String live_title;
            public String teacher_desc;
            public String live_thumb_url;
            public String recording_thumb_url;
            public String f_catalog_id;
            public String s_catalog_id;
            public String live_content;
            public String live_price;
            public String recording_price;
            public String live_push_url;
            public String live_display_url;
            public Object videoid;
            public String start_time;
            public String end_time;
            public String hits_basic;
            public int hits;
            public String supportcount;
            public String cmtcount;
            public String adminuid;
            public String username;
            public String ctime;
            public String status;
            public String share_title;
            public String share_desc;
            public String share_img;
            public Object advid;
            public String live_sign_count;
            public int live_status;
            public UserinfoBean userinfo;
            public String live_sign_status;
            public int buy_status;
            public String live_url;
            public String live_sign_url;
            public int fav;
            public String rtmp_url;
            public int follow_type;
            public List<ScanusersBean> scanusers;
            public List<?> adv_info;

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
    }

}
