package com.xjtushilei;

import com.xjtushilei.utils.mail.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignByQuartzApplicationTests {

    private static Logger log = LoggerFactory.getLogger(SignByQuartzApplicationTests.class);

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMail() {
        try {
            MailUtil.sendMail("619983341@qq.com", "米建红的提醒记录", "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head> \n" +
                    "<meta charset=\"utf-8\"> \n" +
                    "<title>菜鸟教程(runoob.com)</title> \n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<a href=\"http://www.runoob.com/\">访问菜鸟教程</a>\n" +
                    "\t<img border=\"0\" src=\"http://www.runoob.com/images/pulpit.jpg\" alt=\"Pulpit rock\" width=\"304\" height=\"228\">\n" +
                    "\n" +
                    "<table border=\"1\">\n" +
                    "<tr>\n" +
                    "  <td>\n" +
                    "   <p>这是一个段落</p>\n" +
                    "   <p>这是另一个段落</p>\n" +
                    "  </td>\n" +
                    "  <td>这个单元格包含一个表格:\n" +
                    "   <table border=\"1\">\n" +
                    "   <tr>\n" +
                    "     <td>A</td>\n" +
                    "     <td>B</td>\n" +
                    "   </tr>\n" +
                    "   <tr>\n" +
                    "     <td>C</td>\n" +
                    "     <td>D</td>\n" +
                    "   </tr>\n" +
                    "   </table>\n" +
                    "  </td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "  <td>这个单元格包含一个列表\n" +
                    "   <ul>\n" +
                    "    <li>apples</li>\n" +
                    "    <li>bananas</li>\n" +
                    "    <li>pineapples</li>\n" +
                    "   </ul>\n" +
                    "  </td>\n" +
                    "  <td>HELLO</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");
            log.info("发送成功！");
        } catch (IOException | MessagingException e) {
            log.error(e.toString(), e);
        }
    }
}
