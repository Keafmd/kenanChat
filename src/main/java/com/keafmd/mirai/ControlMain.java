package com.keafmd.mirai;

/**
 * Keafmd
 *
 * @ClassName: Main
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2021-08-05 10:16
 */
;
import com.keafmd.model.Response;
import com.keafmd.service.QingYunkeRobotServiceImpl;
;

import java.io.IOException;
import java.util.Scanner;

public class ControlMain {
    public static void main(String[] args) throws IOException {
        //创建青云客的机器人实现类
        QingYunkeRobotServiceImpl qingyunkeRobotService = new QingYunkeRobotServiceImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("快给你的小助手取个名字吧，按回车键确定！！！！");
        String name = scanner.nextLine();
        System.out.println("hi,我是您的小助手 " + name + ", 直接对我下达指令");

        while (true) {
            String input = scanner.nextLine();
            //退出条件：用户输入886
            if ("886".equalsIgnoreCase(input)) {
                System.out.println("欢迎下次使用，拜拜");
                scanner.close();
                return;
            }
            Response response = qingyunkeRobotService.answer(input);
            System.out.println(name + ":" + new String(response.getContent().getBytes(), "UTF-8").replace("{br}", "\n"));
        }
    }
}
