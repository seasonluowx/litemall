package www.qingxiangyx.litemall.core.system;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by liushaoming on 15/11/4.
 */
//所有ajax请求放回类型,封装json结果
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -7301291894175524606L;

    private int retCode;

    private T data;

    private String error;


    public Result(int retCode, T data) {
        this.retCode = retCode;
        this.data = data;
    }

    public Result(int retCode, String error) {
        this.retCode = retCode;
        this.error = error;
    }
}
