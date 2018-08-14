package com.code.mvvm.core.data.pojo.material;


import java.util.List;

/**
 * @author：zhangtianqiu on 18/6/20 13:49
 */
public class MatreialListVo {
    public List<MatreialSubjectVo> matreialsubject;

    public MatreialListVo(List<MatreialSubjectVo> matreialsubject) {
        this.matreialsubject = matreialsubject;
    }

    public void setMatreialsubject(List<MatreialSubjectVo> matreialsubject) {
        this.matreialsubject = matreialsubject;
    }
}
