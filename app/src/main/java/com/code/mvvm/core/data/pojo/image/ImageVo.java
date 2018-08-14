package com.code.mvvm.core.data.pojo.image;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageVo implements Serializable
{
    /** 缩略图 */
    public ImageInfoVo t;

    /** 小图 */
    public ImageInfoVo s;

    public String tid;

    /**
     * 列表图
     */
    public ImageInfoVo l;

    /** 原图 */
    public ImageInfoVo n;

    public String content;

    public String resource_id;

    public ArrayList<TagInfos> taginfos;

    /**
     * 图片标签
     *
     * @Description
     * @author wudi
     * @date 2015年11月3日 下午6:55:08
     * @version V1.3.1
     */
    public static class TagInfos implements Serializable
    {
        public String tagid;

        public String rid;

        public String tagcontent;

        public String tagtype;

        public String avatar;

        public String uid;

        public int totalh;

        public int ukind;

        public int ukind_verify;

        public int totalw;

        public int tagx;

        public int tagy;

        public String ctime;

        public boolean equals(Object o)
        {
            if (!(o instanceof TagInfos))
            {
                return false;
            }
            else
            {
                TagInfos infos = (TagInfos) o;
                return infos.rid.equals(rid)
                        && infos.tagcontent.equals(tagcontent)
                        && infos.uid.equals(uid) && infos.totalh == (totalh)
                        && infos.ukind == (ukind)
                        && infos.ukind_verify == (ukind_verify)
                        && infos.totalw == (totalw) && infos.tagx == (tagx)
                        && infos.tagy == (tagy);
            }
        }

    }

    @Override
    public String toString()
    {
        return "ImageVo{" + "t=" + t + ", s=" + s + ", s=" + l + ", n=" + n
                + ", content='" + content + '\'' + '}';
    }
}
