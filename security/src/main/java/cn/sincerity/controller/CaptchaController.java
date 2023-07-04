package cn.sincerity.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * CaptchaController: 验证码控制器
 *
 * @author Ht7_Sincerity
 * @date 2023/6/30
 */
@RestController
public class CaptchaController {

    @Resource
    private Producer kaptcha;

    @GetMapping("/captcha.png")
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/png");
        String code = kaptcha.createText();
        session.setAttribute("captcha", code);

        BufferedImage bufferedImage = kaptcha.createImage(code);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
    }
}
