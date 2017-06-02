package com.xjtushilei.utils.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * 邮件内容模板生产工厂类
 */
public class TemplateFactory {
    private static Logger log = LoggerFactory.getLogger(TemplateFactory.class);

    // 模板生成配置
    private static TemplateEngine templateEngine = new TemplateEngine();


    public static String generateHtmlFromFtl(String templateName, Map<String, String> model) {
        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process(templateName, context);
        return html;
    }
}
