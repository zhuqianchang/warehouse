package indi.zqc.warehouse.dao;

import com.github.pagehelper.Page;
import indi.zqc.warehouse.model.Material;
import indi.zqc.warehouse.model.condition.MaterialCondition;
import org.apache.ibatis.annotations.Param;

/**
 * Title : MaterialDao.java
 * Package : indi.zqc.warehouse.dao
 * Description : 物料DAO
 * Create on : 2018/1/28 13:39
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface MaterialDao {

    int insertMaterial(Material material);

    int deleteMaterial(@Param("materialCode") String materialCode);

    int updateMaterial(Material material);

    Material selectMaterial(@Param("materialCode") String materialCode);

    Page<Material> selectMaterialPage(MaterialCondition condition);

}
