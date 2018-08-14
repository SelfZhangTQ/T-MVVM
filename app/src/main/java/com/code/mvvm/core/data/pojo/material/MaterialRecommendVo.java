package com.code.mvvm.core.data.pojo.material;

import com.code.mvvm.core.data.pojo.BaseObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MaterialRecommendVo extends BaseObject implements Serializable{

    public MaterialReData data;

    public static class MaterialReData implements Serializable{
        public ArrayList<MatreialSubjectVo> content;
    }

}
