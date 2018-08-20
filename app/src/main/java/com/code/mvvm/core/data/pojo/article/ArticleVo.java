package com.code.mvvm.core.data.pojo.article;

import com.code.mvvm.core.data.pojo.BaseVo;

import java.util.List;


public class ArticleVo extends BaseVo {

    public DataBean data;

    public static class DataBean {
        /**
         * newsid : 15745
         * lecture_level1 : 4
         * lecture_level2 : 123
         * status : 0
         * publishtime : 0
         * thumbtype : 1
         * is_in_list : 1
         * stick_date : 1493892189
         * newstype : 1
         * catid : 0
         * title :  这个不错！卫栖梧群二翁群翁群翁群翁群翁发斯蒂芬斯群翁群翁群
         * thumb : 727360
         * keywords : 你好,我好,大家好
         * desc : 清华大学
         * listorder : 0
         * username : 管理员
         * ctime : 1492582171
         * utime : 1493984626
         * hits : 492
         * cmtcount : 0
         * img : [{"rid":727360,"img":"https://img.meiyuanbang.com/cms/lecture/2017-04-19/807C9AD24C3392B0E18E5494A55BD49A.png","description":null,"resource_type":0}]
         * url : https://testm.meiyuanbang.com/lecture?id=15745
         */

        public List<ArticleInfoVo> top_lecture;
        /**
         * newsid : 15637
         * lecture_level1 : 2
         * lecture_level2 : 110
         * status : 0
         * publishtime : 0
         * thumbtype : 1
         * is_in_list : 1
         * stick_date : 0
         * newstype : 1
         * catid : 0
         * title : 精彩视频独家呈现|北京工业大学设计考题示范
         * thumb : 725480
         * keywords : 设计，命题创意速写，北京工业大学
         * desc : 八爪鱼北京工业大学示范直播课
         * listorder : 0
         * username : 雪松
         * ctime : 1486610645
         * utime : 1486624295
         * hits : 382
         * cmtcount : 0
         * img : [{"rid":725480,"img":"https://img.meiyuanbang.com/cms/lecture/2017-02-09/3258F96F8344A12A78D5841EEA71A0E0.jpg","description":null,"resource_type":0}]
         * url : https://testm.meiyuanbang.com/lecture?id=15637
         */

        public List<ArticleInfoVo> list;
    }
}
