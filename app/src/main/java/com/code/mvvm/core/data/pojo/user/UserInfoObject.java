package com.code.mvvm.core.data.pojo.user;

import com.code.mvvm.core.data.pojo.BaseVo;

import java.io.Serializable;
import java.util.List;

public class UserInfoObject extends BaseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    public UserInfoContent data;

    public static class UserInfoContent implements Serializable {
        private static final long serialVersionUID = 1L;

        public String correct_fee;

        public String uid = "";

        public String sname = "";

        public String avatar = "";

        public String province = "";

        public String profession = "";

        public String city = "";

        public String area_name = "";

        public String intro = "";

        public String school = "";


        public String ukind = "0";

        public String ukind_verify = "0";

        public String featureflag ="0";// featureflag & 1 ==1是批改老师

        public String follower_num; // 关注的人数量

        public String followee_num; // 粉丝数量

        public String approval_num; // 赞过的数量

        public String tweet_num; // 作品的数量

        public String recent_news_num; // 动态数量

        public String team_member_num; // 小组成员数量

        public String follow_type;

        public String role_type;


        public String token; // 登陆token

        public String provinceid;

        public String area_id;

        public String city_id;

        public String school_id;

        public String school_name;

        public String genderid;

        public String coachflag;


        /**
         * 老师：5
         */
        public String professionid;

        public String umobile;

        public String gradeid;

        public String total_coin;

        public String remain_coin;

        public String addcoincount;

        public String correctnum;

        public Coin cointask;

        public ShareData share;

        public List<CorrectCount> correct_count;

    }

    public static class CorrectCount implements Serializable {


        private String subid;
        private String name;
        private String count;
        private List<CatalogBean> catalog;

        public String getSubid() {
            return subid;
        }

        public void setSubid(String subid) {
            this.subid = subid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<CatalogBean> getCatalog() {
            return catalog;
        }

        public void setCatalog(List<CatalogBean> catalog) {
            this.catalog = catalog;
        }

        public static class CatalogBean implements Serializable {


            private String subid;
            private String name;

            public String getSubid() {
                return subid;
            }

            public void setSubid(String subid) {
                this.subid = subid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class Coin implements Serializable {
        public String cointcount;
        public String tasktype;
    }

    public static class ShareData implements Serializable {
        public String title;
        public String desc;
        public String img;
        public String url;
    }
}
