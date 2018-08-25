package com.code.mvvm.core.data.pojo.image;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageVo implements Serializable
{
    public ImageInfoVo t;

    public ImageInfoVo s;

    public String tid;

    public ImageInfoVo l;

    public ImageInfoVo n;

    public String content;

    public String resource_id;

    public ArrayList<TagInfo> taginfos;

    public static class TagInfo implements Serializable
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

        @Override
        public boolean equals(Object o)
        {
            if (!(o instanceof TagInfo))
            {
                return false;
            }
            else
            {
                TagInfo infos = (TagInfo) o;
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
