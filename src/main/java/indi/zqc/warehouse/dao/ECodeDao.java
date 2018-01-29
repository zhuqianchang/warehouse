package indi.zqc.warehouse.dao;

import indi.zqc.warehouse.model.ECode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Title : ECodeDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 枚举码DAO
 * Create on : 2018/1/28 13:17
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface ECodeDao {

    List<ECode> selectECodeList(@Param("eCodeType") String eCodeType);

    ECode selectECode(@Param("eCodeType") String eCodeType, @Param("eCode") String eCode);
}
