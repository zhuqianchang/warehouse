package indi.zqc.warehouse.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import indi.zqc.warehouse.util.MenuUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Title : ButtonDirective.java
 * Package : indi.zqc.warehouse.freemarker
 * Description : 按钮权限控制
 * Create on : 2018/2/23 17:06
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public class ButtonDirective implements TemplateDirectiveModel {

    //菜单编号
    private static final String MENU_CODE = "menuCode";

    @Override
    public void execute(Environment env, Map map, TemplateModel[] templateModels, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        Writer out = env.getOut();
        String menuCode = null;
        if (map.containsKey(MENU_CODE) && map.get(MENU_CODE) != null) {
            menuCode = String.valueOf(map.get(MENU_CODE));
        }
        //当前用户拥有菜单权限
        if (StringUtils.isNotBlank(menuCode) && MenuUtils.containMenu(menuCode)) {
            body.render(out);
        } else {
            out.write("");
        }
    }
}
