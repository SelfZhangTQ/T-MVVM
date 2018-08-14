package com.code.mvvm.core.data.pojo.course;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CourseTypeVo implements Serializable {

    /**
     * errno : 0
     * data : [{"id":1,"name":"色彩","s_catalog":[{"id":101,"name":"静物单体"},{"id":100,"name":"组合静物"},{"id":1003,"name":"场景"},{"id":102,"name":"头像"},{"id":1001,"name":"半身像"},{"id":1002,"name":"全身像"},{"id":103,"name":"风景"}]},{"id":4,"name":"素描","s_catalog":[{"id":101,"name":"静物单体"},{"id":100,"name":"组合静物"},{"id":1003,"name":"场景"},{"id":102,"name":"头像"},{"id":1001,"name":"半身像"},{"id":1002,"name":"全身像"},{"id":103,"name":"风景"},{"id":123,"name":"单体几何"},{"id":124,"name":"组合几何"},{"id":125,"name":"单体静物"},{"id":126,"name":"组合静物"},{"id":127,"name":"石膏五官"},{"id":4001,"name":"石膏解剖"},{"id":128,"name":"石膏像"},{"id":4002,"name":"人物局部"},{"id":129,"name":"头像"},{"id":130,"name":"半身像"},{"id":4003,"name":"全身像"},{"id":131,"name":"人体解剖"},{"id":4004,"name":"场景"},{"id":4005,"name":"风景建筑"},{"id":4006,"name":"动物"}]},{"id":5,"name":"速写","s_catalog":[{"id":101,"name":"静物单体"},{"id":100,"name":"组合静物"},{"id":1003,"name":"场景"},{"id":102,"name":"头像"},{"id":1001,"name":"半身像"},{"id":1002,"name":"全身像"},{"id":103,"name":"风景"},{"id":123,"name":"单体几何"},{"id":124,"name":"组合几何"},{"id":125,"name":"单体静物"},{"id":126,"name":"组合静物"},{"id":127,"name":"石膏五官"},{"id":4001,"name":"石膏解剖"},{"id":128,"name":"石膏像"},{"id":4002,"name":"人物局部"},{"id":129,"name":"头像"},{"id":130,"name":"半身像"},{"id":4003,"name":"全身像"},{"id":131,"name":"人体解剖"},{"id":4004,"name":"场景"},{"id":4005,"name":"风景建筑"},{"id":4006,"name":"动物"},{"id":133,"name":"人物速写"},{"id":5001,"name":"人物半身速写"},{"id":134,"name":"人物局部速写"},{"id":135,"name":"动态快写"},{"id":136,"name":"人体结构"},{"id":137,"name":"场景速写"},{"id":138,"name":"命题速写"},{"id":5002,"name":"风景速写"},{"id":5003,"name":"动物"},{"id":5004,"name":"道具"}]},{"id":2,"name":"设计","s_catalog":[{"id":101,"name":"静物单体"},{"id":100,"name":"组合静物"},{"id":1003,"name":"场景"},{"id":102,"name":"头像"},{"id":1001,"name":"半身像"},{"id":1002,"name":"全身像"},{"id":103,"name":"风景"},{"id":123,"name":"单体几何"},{"id":124,"name":"组合几何"},{"id":125,"name":"单体静物"},{"id":126,"name":"组合静物"},{"id":127,"name":"石膏五官"},{"id":4001,"name":"石膏解剖"},{"id":128,"name":"石膏像"},{"id":4002,"name":"人物局部"},{"id":129,"name":"头像"},{"id":130,"name":"半身像"},{"id":4003,"name":"全身像"},{"id":131,"name":"人体解剖"},{"id":4004,"name":"场景"},{"id":4005,"name":"风景建筑"},{"id":4006,"name":"动物"},{"id":133,"name":"人物速写"},{"id":5001,"name":"人物半身速写"},{"id":134,"name":"人物局部速写"},{"id":135,"name":"动态快写"},{"id":136,"name":"人体结构"},{"id":137,"name":"场景速写"},{"id":138,"name":"命题速写"},{"id":5002,"name":"风景速写"},{"id":5003,"name":"动物"},{"id":5004,"name":"道具"},{"id":2001,"name":"设计基础"},{"id":107,"name":"单体装饰画"},{"id":2003,"name":"黑白装饰画"},{"id":2002,"name":"彩色装饰画"},{"id":109,"name":"单体创意速写"},{"id":110,"name":"命题创意速写"},{"id":111,"name":"字体设计"},{"id":112,"name":"设计素描"},{"id":113,"name":"设计色彩"},{"id":114,"name":"平面设计"},{"id":115,"name":"立体构成"}]}]
     */

    public int errno;
    public List<DataBean> data;
    public static class DataBean implements Serializable {
        /**
         * id : 1
         * name : 色彩
         * s_catalog : [{"id":101,"name":"静物单体"},{"id":100,"name":"组合静物"},{"id":1003,"name":"场景"},{"id":102,"name":"头像"},{"id":1001,"name":"半身像"},{"id":1002,"name":"全身像"},{"id":103,"name":"风景"}]
         */

        public String id;
        public String name;
        public boolean isCheck = false;
        public ArrayList<SCatalogBean> s_catalog;

        public static class SCatalogBean implements Serializable {
            /**
             * id : 101
             * name : 静物单体
             */

            public String id;
            public String name;
            public boolean isChecked;

        }


    }
}
