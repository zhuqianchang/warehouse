package indi.zqc.warehouse.model;

import java.util.List;

/**
 * Title : TreeInterface.java
 * Package : indi.zqc.warehouse.model
 * Description : 树结构基本接口
 * Create on : 2018/1/26 16:26
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public interface TreeInterface {

    void setTrees(List trees);

    String getPid();

    String getNodeId();

    boolean isLeaf();

    boolean isChecked();
}
