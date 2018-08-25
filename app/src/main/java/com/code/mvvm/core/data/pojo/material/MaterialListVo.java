package com.code.mvvm.core.data.pojo.material;


import java.util.List;

/**
 * @author：tqzhang  on 18/6/20 13:49
 */
public class MaterialListVo {
    public List<MatreialSubjectVo> matreialsubject;

    public MaterialListVo(List<MatreialSubjectVo> matreialsubject) {
        this.matreialsubject = matreialsubject;
    }

    public void setMatreialsubject(List<MatreialSubjectVo> matreialsubject) {
        this.matreialsubject = matreialsubject;
    }
}
