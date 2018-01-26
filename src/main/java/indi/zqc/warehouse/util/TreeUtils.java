package indi.zqc.warehouse.util;

import indi.zqc.warehouse.model.TreeInterface;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Title : TreeUtils.java
 * Package : indi.zqc.warehouse.util
 * Description : 树工具
 * Create on : 2018/1/26 16:44
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class TreeUtils {

    private TreeUtils() {
    }

    public static List<TreeInterface> gradingRecursion(TreeInterface[] trees, String parentid) {
        if (parentid == null) {
            parentid = StringUtils.EMPTY;
        }
        List<TreeInterface> parent = new ArrayList<>();
        List<TreeInterface> children = new ArrayList<>();
        for (TreeInterface tree : trees) {// 父子分离
            if (tree.getPid() == null || tree.getPid().equals(parentid)) {// 父
                parent.add(tree);
            } else {// 子
                children.add(tree);
            }
        }
        if (children.size() == 0) {// 无子，孤独终老
            return parent;
        }
        // 有子，传宗接代，直到断子绝孙
        List<TreeInterface> tempList;
        for (TreeInterface tree : parent) {
            tempList = gradingRecursion(children.toArray(new TreeInterface[children.size()]), tree.getNodeId());// 找儿子
            if (tempList != null && tempList.size() != 0) {// 找到儿子
                tree.setTrees(tempList);
            }
        }
        return parent;
    }
}
