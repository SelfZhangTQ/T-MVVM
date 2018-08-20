package com.code.mvvm.core.data.pojo.article;

import com.code.mvvm.core.data.pojo.BaseVo;

import java.util.ArrayList;
import java.util.List;

public class ArticleSubjectVo extends BaseVo {


	/**
	 * subject_info : {"newsid":"15789","lecture_level1":"1","lecture_level2":"101","status":"0","publishtime":"0","thumbtype":"3","is_in_list":"1","stick_date":"0","newstype":"0","catid":"0","title":"专题11","thumb":"727512,727513,727514","keywords":"你好,我好,大家好","desc":"","listorder":"0","username":"管理员","ctime":"1494233260","utime":"1494234262","hits":"0","cmtcount":"0","img":[{"rid":727512,"img":"https://img.meiyuanbang.com/cms/lecture/2017-05-08/F7FAFB7B5907F5BDDE75070FABAD1D3F.jpg","description":null,"resource_type":0},{"rid":727513,"img":"https://img.meiyuanbang.com/cms/lecture/2017-05-08/A65A1AF23C36363213E0578433D8C450.jpg","description":null,"resource_type":0},{"rid":727514,"img":"https://img.meiyuanbang.com/cms/lecture/2017-05-08/9999EBC304029C69B1D5A09574419DD2.jpg","description":null,"resource_type":0}],"url":"https://testm.meiyuanbang.com/lecture?id=15789"}
	 * tag_list : [{"lecture_tagid":"1","newsid":"15789","tag_title":"清华美院","status":"1","listorder":"0","ctime":null,"lecture_list":[{"newsid":"10082","lecture_level1":"4","lecture_level2":"129","status":"1","publishtime":"0","thumbtype":"1","is_in_list":"1","stick_date":"0","newstype":"1","catid":"0","title":"1","thumb":"36875","keywords":"素描,头像","desc":"","listorder":"0","username":"管理员","ctime":null,"utime":"1446879101","hits":"4211","cmtcount":"0","img":[{"rid":36875,"img":"http://img.meiyuanbang.com/cms/lecture/2015-11-07/04F01FE71CB9AA7A771DCC1B7A7C2F0A.jpg","description":null,"resource_type":0}],"url":"https://testm.meiyuanbang.com/lecture?id=10082","tag_news_id":"1","lecture_tagid":"1"},{"newsid":"10083","lecture_level1":"1","lecture_level2":"100","status":"1","publishtime":"0","thumbtype":"1","is_in_list":"1","stick_date":"0","newstype":"1","catid":"0","title":"1","thumb":"36876","keywords":"色彩,构图","desc":"大部分同学一想到画色彩，可能首先考虑的是色彩的搭配，却忽略了构图。要知道，构图其实是画好一张色彩的重要基础。","listorder":"0","username":"管理员","ctime":null,"utime":"1446879845","hits":"1733","cmtcount":"0","img":[{"rid":36876,"img":"http://img.meiyuanbang.com/cms/lecture/2015-11-07/C05FF7C4FB6516E9C4E9824CB051BD0A.jpg","description":null,"resource_type":0}],"url":"https://testm.meiyuanbang.com/lecture?id=10083","tag_news_id":"2","lecture_tagid":"1"}]},{"lecture_tagid":"2","newsid":"15789","tag_title":"中央美院","status":"1","listorder":"0","ctime":null,"lecture_list":[{"newsid":"10079","lecture_level1":"1","lecture_level2":"100","status":"1","publishtime":"0","thumbtype":"1","is_in_list":"1","stick_date":"0","newstype":"1","catid":"0","title":"1","thumb":"36872","keywords":"色彩,笔触","desc":"","listorder":"0","username":"管理员","ctime":null,"utime":"1447221624","hits":"2288","cmtcount":"2","img":[{"rid":36872,"img":"http://img.meiyuanbang.com/cms/lecture/2015-11-07/0C45D1B36947348127A89DCFD9009E45.jpg","description":null,"resource_type":0}],"url":"https://testm.meiyuanbang.com/lecture?id=10079","tag_news_id":"3","lecture_tagid":"2"}]},{"lecture_tagid":"3","newsid":"15789","tag_title":"鲁美","status":"1","listorder":"0","ctime":null,"lecture_list":[{"newsid":"10091","lecture_level1":"5","lecture_level2":"134","status":"1","publishtime":"0","thumbtype":"1","is_in_list":"1","stick_date":"0","newstype":"1","catid":"0","title":"1","thumb":"36884","keywords":"速写,细节","desc":"当一大堆第一眼看上去差不多的速写试卷摆在老师眼前，他下一步会选择哪一个，很大程度上决定于你的细节刻画如何！ ","listorder":"0","username":"管理员","ctime":null,"utime":"1447227520","hits":"633","cmtcount":"0","img":[{"rid":36884,"img":"http://img.meiyuanbang.com/cms/lecture/2015-11-11/565B4EA0383D96F196986BED0FEB7A7E.jpg","description":null,"resource_type":0}],"url":"https://testm.meiyuanbang.com/lecture?id=10091","tag_news_id":"4","lecture_tagid":"3"}]},{"lecture_tagid":"4","newsid":"15789","tag_title":"广美","status":"1","listorder":"0","ctime":null,"lecture_list":[{"newsid":"10088","lecture_level1":"5","lecture_level2":"133","status":"1","publishtime":"0","thumbtype":"1","is_in_list":"1","stick_date":"0","newstype":"1","catid":"0","title":"1","thumb":"36881","keywords":"速写,线条","desc":"","listorder":"0","username":"管理员","ctime":null,"utime":"1447223348","hits":"2117","cmtcount":"2","img":[{"rid":36881,"img":"http://img.meiyuanbang.com/cms/lecture/2015-11-07/17C1E20E806C74A53424EE42B0236976.jpg","description":null,"resource_type":0}],"url":"https://testm.meiyuanbang.com/lecture?id=10088","tag_news_id":"5","lecture_tagid":"4"}]},{"lecture_tagid":"5","newsid":"15789","tag_title":"川美","status":"1","listorder":"0","ctime":null,"lecture_list":[{"newsid":"10087","lecture_level1":"4","lecture_level2":"129","status":"1","publishtime":"0","thumbtype":"1","is_in_list":"1","stick_date":"0","newstype":"1","catid":"0","title":"1","thumb":"36880","keywords":"素描,头像","desc":"在素描学习中，我们常常听到老师说\u201c不要抄调子！要结合形体结构\u201d。那么，究竟怎样才能快速的画出明确又贴合形体的明暗呢？","listorder":"0","username":"管理员","ctime":null,"utime":"1446886242","hits":"2159","cmtcount":"1","img":[{"rid":36880,"img":"http://img.meiyuanbang.com/cms/lecture/2015-11-07/BF6127720C982E0A828C4F4CFD0F0E34.jpg","description":null,"resource_type":0}],"url":"https://testm.meiyuanbang.com/lecture?id=10087","tag_news_id":"6","lecture_tagid":"5"}]}]
	 */

	private DataBean data;

	public static class DataBean {
		/**
		 * newsid : 15789
		 * lecture_level1 : 1
		 * lecture_level2 : 101
		 * status : 0
		 * publishtime : 0
		 * thumbtype : 3
		 * is_in_list : 1
		 * stick_date : 0
		 * newstype : 0
		 * catid : 0
		 * title : 专题11
		 * thumb : 727512,727513,727514
		 * keywords : 你好,我好,大家好
		 * desc :
		 * listorder : 0
		 * username : 管理员
		 * ctime : 1494233260
		 * utime : 1494234262
		 * hits : 0
		 * cmtcount : 0
		 * img : [{"rid":727512,"img":"https://img.meiyuanbang.com/cms/lecture/2017-05-08/F7FAFB7B5907F5BDDE75070FABAD1D3F.jpg","description":null,"resource_type":0},{"rid":727513,"img":"https://img.meiyuanbang.com/cms/lecture/2017-05-08/A65A1AF23C36363213E0578433D8C450.jpg","description":null,"resource_type":0},{"rid":727514,"img":"https://img.meiyuanbang.com/cms/lecture/2017-05-08/9999EBC304029C69B1D5A09574419DD2.jpg","description":null,"resource_type":0}]
		 * url : https://testm.meiyuanbang.com/lecture?id=15789
		 */

		private SubjectInfoBean subject_info;
		/**
		 * lecture_tagid : 1
		 * newsid : 15789
		 * tag_title : 清华美院
		 * status : 1
		 * listorder : 0
		 * ctime : null
		 * lecture_list : [{"newsid":"10082","lecture_level1":"4","lecture_level2":"129","status":"1","publishtime":"0","thumbtype":"1","is_in_list":"1","stick_date":"0","newstype":"1","catid":"0","title":"1","thumb":"36875","keywords":"素描,头像","desc":"","listorder":"0","username":"管理员","ctime":null,"utime":"1446879101","hits":"4211","cmtcount":"0","img":[{"rid":36875,"img":"http://img.meiyuanbang.com/cms/lecture/2015-11-07/04F01FE71CB9AA7A771DCC1B7A7C2F0A.jpg","description":null,"resource_type":0}],"url":"https://testm.meiyuanbang.com/lecture?id=10082","tag_news_id":"1","lecture_tagid":"1"},{"newsid":"10083","lecture_level1":"1","lecture_level2":"100","status":"1","publishtime":"0","thumbtype":"1","is_in_list":"1","stick_date":"0","newstype":"1","catid":"0","title":"1","thumb":"36876","keywords":"色彩,构图","desc":"大部分同学一想到画色彩，可能首先考虑的是色彩的搭配，却忽略了构图。要知道，构图其实是画好一张色彩的重要基础。","listorder":"0","username":"管理员","ctime":null,"utime":"1446879845","hits":"1733","cmtcount":"0","img":[{"rid":36876,"img":"http://img.meiyuanbang.com/cms/lecture/2015-11-07/C05FF7C4FB6516E9C4E9824CB051BD0A.jpg","description":null,"resource_type":0}],"url":"https://testm.meiyuanbang.com/lecture?id=10083","tag_news_id":"2","lecture_tagid":"1"}]
		 */

		private List<TagListBean> tag_list;
		public static class SubjectInfoBean {
			private String newsid;
			private String lecture_level1;
			private String lecture_level2;
			private String status;
			private String publishtime;
			private String thumbtype;
			private String is_in_list;
			private String stick_date;
			private String newstype;
			private String catid;
			private String title;
			private String thumb;
			private String keywords;
			private String desc;
			private String listorder;
			private String username;
			private String ctime;
			private String utime;
			private String hits;
			private String cmtcount;
			private String url;
			private int fav;
			/**
			 * rid : 727512
			 * img : https://img.meiyuanbang.com/cms/lecture/2017-05-08/F7FAFB7B5907F5BDDE75070FABAD1D3F.jpg
			 * description : null
			 * resource_type : 0
			 */

			private List<ImgBean> img;

			public String getNewsid() {
				return newsid;
			}

			public void setNewsid(String newsid) {
				this.newsid = newsid;
			}

			public String getLecture_level1() {
				return lecture_level1;
			}

			public void setLecture_level1(String lecture_level1) {
				this.lecture_level1 = lecture_level1;
			}

			public String getLecture_level2() {
				return lecture_level2;
			}

			public void setLecture_level2(String lecture_level2) {
				this.lecture_level2 = lecture_level2;
			}

			public String getStatus() {
				return status;
			}

			public void setStatus(String status) {
				this.status = status;
			}

			public String getPublishtime() {
				return publishtime;
			}

			public void setPublishtime(String publishtime) {
				this.publishtime = publishtime;
			}

			public String getThumbtype() {
				return thumbtype;
			}

			public void setThumbtype(String thumbtype) {
				this.thumbtype = thumbtype;
			}

			public String getIs_in_list() {
				return is_in_list;
			}

			public void setIs_in_list(String is_in_list) {
				this.is_in_list = is_in_list;
			}

			public String getStick_date() {
				return stick_date;
			}

			public void setStick_date(String stick_date) {
				this.stick_date = stick_date;
			}

			public String getNewstype() {
				return newstype;
			}

			public void setNewstype(String newstype) {
				this.newstype = newstype;
			}

			public String getCatid() {
				return catid;
			}

			public void setCatid(String catid) {
				this.catid = catid;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public String getThumb() {
				return thumb;
			}

			public void setThumb(String thumb) {
				this.thumb = thumb;
			}

			public String getKeywords() {
				return keywords;
			}

			public void setKeywords(String keywords) {
				this.keywords = keywords;
			}

			public String getDesc() {
				return desc;
			}

			public void setDesc(String desc) {
				this.desc = desc;
			}

			public String getListorder() {
				return listorder;
			}

			public void setListorder(String listorder) {
				this.listorder = listorder;
			}

			public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}

			public String getCtime() {
				return ctime;
			}

			public void setCtime(String ctime) {
				this.ctime = ctime;
			}

			public String getUtime() {
				return utime;
			}

			public void setUtime(String utime) {
				this.utime = utime;
			}

			public String getHits() {
				return hits;
			}

			public void setHits(String hits) {
				this.hits = hits;
			}

			public int getFav() {
				return fav;
			}

			public void setFav(int fav) {
				this.fav = fav;
			}

			public String getCmtcount() {
				return cmtcount;
			}

			public void setCmtcount(String cmtcount) {
				this.cmtcount = cmtcount;
			}

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public List<ImgBean> getImg() {
				return img;
			}

			public void setImg(List<ImgBean> img) {
				this.img = img;
			}

			public static class ImgBean {
				private int rid;
				private String img;
				private Object description;
				private int resource_type;

				public int getRid() {
					return rid;
				}

				public void setRid(int rid) {
					this.rid = rid;
				}

				public String getImg() {
					return img;
				}

				public void setImg(String img) {
					this.img = img;
				}

				public Object getDescription() {
					return description;
				}

				public void setDescription(Object description) {
					this.description = description;
				}

				public int getResource_type() {
					return resource_type;
				}

				public void setResource_type(int resource_type) {
					this.resource_type = resource_type;
				}
			}
		}

		public static class TagListBean {
			private String lecture_tagid;
			private String newsid;
			private String tag_title;
			private String status;
			private String listorder;
			private Object ctime;
			/**
			 * newsid : 10082
			 * lecture_level1 : 4
			 * lecture_level2 : 129
			 * status : 1
			 * publishtime : 0
			 * thumbtype : 1
			 * is_in_list : 1
			 * stick_date : 0
			 * newstype : 1
			 * catid : 0
			 * title : 1
			 * thumb : 36875
			 * keywords : 素描,头像
			 * desc :
			 * listorder : 0
			 * username : 管理员
			 * ctime : null
			 * utime : 1446879101
			 * hits : 4211
			 * cmtcount : 0
			 * img : [{"rid":36875,"img":"http://img.meiyuanbang.com/cms/lecture/2015-11-07/04F01FE71CB9AA7A771DCC1B7A7C2F0A.jpg","description":null,"resource_type":0}]
			 * url : https://testm.meiyuanbang.com/lecture?id=10082
			 * tag_news_id : 1
			 * lecture_tagid : 1
			 */

			private ArrayList<LectureListBean> lecture_list;

			public String getLecture_tagid() {
				return lecture_tagid;
			}

			public void setLecture_tagid(String lecture_tagid) {
				this.lecture_tagid = lecture_tagid;
			}

			public String getNewsid() {
				return newsid;
			}

			public void setNewsid(String newsid) {
				this.newsid = newsid;
			}

			public String getTag_title() {
				return tag_title;
			}

			public void setTag_title(String tag_title) {
				this.tag_title = tag_title;
			}

			public String getStatus() {
				return status;
			}

			public void setStatus(String status) {
				this.status = status;
			}

			public String getListorder() {
				return listorder;
			}

			public void setListorder(String listorder) {
				this.listorder = listorder;
			}

			public Object getCtime() {
				return ctime;
			}

			public void setCtime(Object ctime) {
				this.ctime = ctime;
			}

			public ArrayList<LectureListBean> getLecture_list() {
				return lecture_list;
			}

			public void setLecture_list(ArrayList<LectureListBean> lecture_list) {
				this.lecture_list = lecture_list;
			}

			public static class LectureListBean {
				private String newsid;
				private String lecture_level1;
				private String lecture_level2;
				private String status;
				private String publishtime;
				private String thumbtype;
				private String is_in_list;
				private String stick_date;
				private String newstype;
				private String content_type;
				private String catid;
				private String title;
				private String thumb;
				private String keywords;
				private String desc;
				private String listorder;
				private String username;
				private Object ctime;
				private String utime;
				private String hits;
				private String cmtcount;
				private String url;
				private String tag_news_id;
				private String lecture_tagid;

				public String getContent_type() {
					return content_type;
				}

				public void setContent_type(String content_type) {
					this.content_type = content_type;
				}

				/**
				 * rid : 36875
				 * img : http://img.meiyuanbang.com/cms/lecture/2015-11-07/04F01FE71CB9AA7A771DCC1B7A7C2F0A.jpg
				 * description : null
				 * resource_type : 0
				 */


				private List<ImgBean> img;

				public String getNewsid() {
					return newsid;
				}

				public void setNewsid(String newsid) {
					this.newsid = newsid;
				}

				public String getLecture_level1() {
					return lecture_level1;
				}

				public void setLecture_level1(String lecture_level1) {
					this.lecture_level1 = lecture_level1;
				}

				public String getLecture_level2() {
					return lecture_level2;
				}

				public void setLecture_level2(String lecture_level2) {
					this.lecture_level2 = lecture_level2;
				}

				public String getStatus() {
					return status;
				}

				public void setStatus(String status) {
					this.status = status;
				}

				public String getPublishtime() {
					return publishtime;
				}

				public void setPublishtime(String publishtime) {
					this.publishtime = publishtime;
				}

				public String getThumbtype() {
					return thumbtype;
				}

				public void setThumbtype(String thumbtype) {
					this.thumbtype = thumbtype;
				}

				public String getIs_in_list() {
					return is_in_list;
				}

				public void setIs_in_list(String is_in_list) {
					this.is_in_list = is_in_list;
				}

				public String getStick_date() {
					return stick_date;
				}

				public void setStick_date(String stick_date) {
					this.stick_date = stick_date;
				}

				public String getNewstype() {
					return newstype;
				}

				public void setNewstype(String newstype) {
					this.newstype = newstype;
				}

				public String getCatid() {
					return catid;
				}

				public void setCatid(String catid) {
					this.catid = catid;
				}

				public String getTitle() {
					return title;
				}

				public void setTitle(String title) {
					this.title = title;
				}

				public String getThumb() {
					return thumb;
				}

				public void setThumb(String thumb) {
					this.thumb = thumb;
				}

				public String getKeywords() {
					return keywords;
				}

				public void setKeywords(String keywords) {
					this.keywords = keywords;
				}

				public String getDesc() {
					return desc;
				}

				public void setDesc(String desc) {
					this.desc = desc;
				}

				public String getListorder() {
					return listorder;
				}

				public void setListorder(String listorder) {
					this.listorder = listorder;
				}

				public String getUsername() {
					return username;
				}

				public void setUsername(String username) {
					this.username = username;
				}

				public Object getCtime() {
					return ctime;
				}

				public void setCtime(Object ctime) {
					this.ctime = ctime;
				}

				public String getUtime() {
					return utime;
				}

				public void setUtime(String utime) {
					this.utime = utime;
				}

				public String getHits() {
					return hits;
				}

				public void setHits(String hits) {
					this.hits = hits;
				}

				public String getCmtcount() {
					return cmtcount;
				}

				public void setCmtcount(String cmtcount) {
					this.cmtcount = cmtcount;
				}

				public String getUrl() {
					return url;
				}

				public void setUrl(String url) {
					this.url = url;
				}

				public String getTag_news_id() {
					return tag_news_id;
				}

				public void setTag_news_id(String tag_news_id) {
					this.tag_news_id = tag_news_id;
				}

				public String getLecture_tagid() {
					return lecture_tagid;
				}

				public void setLecture_tagid(String lecture_tagid) {
					this.lecture_tagid = lecture_tagid;
				}

				public List<ImgBean> getImg() {
					return img;
				}

				public void setImg(List<ImgBean> img) {
					this.img = img;
				}

				public static class ImgBean {
					private int rid;
					private String img;
					private Object description;
					private int resource_type;

					public int getRid() {
						return rid;
					}

					public void setRid(int rid) {
						this.rid = rid;
					}

					public String getImg() {
						return img;
					}

					public void setImg(String img) {
						this.img = img;
					}

					public Object getDescription() {
						return description;
					}

					public void setDescription(Object description) {
						this.description = description;
					}

					public int getResource_type() {
						return resource_type;
					}

					public void setResource_type(int resource_type) {
						this.resource_type = resource_type;
					}
				}
			}
		}
	}
}
