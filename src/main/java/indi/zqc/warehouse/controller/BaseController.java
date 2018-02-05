package indi.zqc.warehouse.controller;

import com.alibaba.fastjson.JSONObject;
import indi.zqc.warehouse.enums.DWZCallbackType;
import indi.zqc.warehouse.enums.DWZStatusCode;
import indi.zqc.warehouse.exception.BusinessException;
import indi.zqc.warehouse.model.Common;
import indi.zqc.warehouse.model.DWZResult;
import indi.zqc.warehouse.util.DatabaseUtils;
import indi.zqc.warehouse.util.SecurityContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Title : BaseController.java
 * Package : indi.zqc.warehouse.controller
 * Description : 基础控制器
 * Create on : 2018/1/22 19:40
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
public abstract class BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected static final String NAVTABID = "navTabId";

    protected static final String PAGE = "page";

    @ExceptionHandler
    public String exp(HttpServletResponse response, Exception ex) {
        String jsonObject;
        if (ex instanceof BusinessException) {
            //业务异常
            jsonObject = JSONObject.toJSONString(ajaxError(ex.getMessage()));
        } else if(DatabaseUtils.isConstraintsError(ex.getMessage())){
            //数据库删除异常
            jsonObject = JSONObject.toJSONString(ajaxError("数据已被使用，不能删除"));
        }else {
            //其它异常
            jsonObject = JSONObject.toJSONString(ajaxError(ex.getMessage()));
            //记录日志
            logger.error(ex.getMessage(), ex);
        }
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(jsonObject);
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return null;
    }

    /**
     * 创建信息
     *
     * @param common
     */
    public void setCreateInfo(Common common) {
        common.setCreateDateTime(new Date());
        common.setCreateUser(SecurityContextUtils.getCurrentUserCode());
        this.setModifyInfo(common);
    }

    /**
     * 修改信息
     *
     * @param common
     */
    public void setModifyInfo(Common common) {
        common.setModifyDateTime(new Date());
        common.setModifyUser(SecurityContextUtils.getCurrentUserCode());
    }

    /**
     * 当前用户
     *
     * @return
     */
    public String getCurrentUserCode(){
        return SecurityContextUtils.getCurrentUserCode();
    }

    public String getForwardUrl(HttpServletRequest request) {
        return request.getContextPath();
    }

    public DWZResult ajaxDone() {
        return getDWZJsonObject(DWZStatusCode.OK, "操作成功", null, DWZCallbackType.EMPTY, null);
    }

    public DWZResult ajaxDone(String message) {
        return getDWZJsonObject(DWZStatusCode.OK, message, null, DWZCallbackType.EMPTY, null);
    }

    public DWZResult ajaxError(String message) {
        return getDWZJsonObject(DWZStatusCode.ERROR, message, null, DWZCallbackType.EMPTY, null);
    }

    public DWZResult navTabAjaxDone(String navTabId, DWZCallbackType callbackType, String forwardUrl) {
        return getDWZJsonObject(DWZStatusCode.OK, "操作成功", navTabId, callbackType, forwardUrl);
    }

    public DWZResult dialogAjaxDone() {
        return getDWZJsonObject(DWZStatusCode.OK, "操作成功", null, DWZCallbackType.CLOSECURRENT, null);
    }

    public DWZResult dialogAjaxDone(String message) {
        return getDWZJsonObject(DWZStatusCode.OK, message, null, DWZCallbackType.CLOSECURRENT, null);
    }

    private DWZResult getDWZJsonObject(DWZStatusCode statusCode, String message, String navTabId,
                                      DWZCallbackType callbackType, String forwardUrl) {
        if (callbackType == null) {
            callbackType = DWZCallbackType.EMPTY;
        }
        DWZResult dwzResult = new DWZResult();
        dwzResult.setStatusCode(statusCode.getKey());
        dwzResult.setMessage(message);
        dwzResult.setNavTabId(navTabId);
        dwzResult.setCallbackType(callbackType.getValue());
        dwzResult.setForwardUrl(forwardUrl);
        return dwzResult;
    }
}
