package com.keafmd.mirai;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.BotConfiguration;
import com.keafmd.model.Response;
import com.keafmd.service.QingYunkeRobotServiceImpl;

import java.io.IOException;

public class JavaMain {
    public static void main(String[] args) {
        Bot bot = BotFactory.INSTANCE.newBot(2787732627L, "200003050");

        bot.login();

        JavaMain.afterLogin(bot);
    }

    public static void afterLogin(Bot bot) {
        long yourQQNumber = 674155030;
//        long yourQQNumber = 2961382983L;
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, (event) -> {
            if (event.getSender().getId() == yourQQNumber) {
                try {
                    event.getSubject().sendMessage(new MessageChainBuilder()

    //                        .append(new QuoteReply(event.getMessage()))
    //                        .append("Hi, you just said: '")
    //                        .append(event.getMessage())
                            .append(automaticResponse(event.getMessage()))
    //                        .append("'")
                            .build()
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static MessageChain automaticResponse(MessageChain news) throws IOException {
        String ss = news.contentToString();
        StringBuffer resBuffer = new StringBuffer();
        QingYunkeRobotServiceImpl qingYunkeRobotService = new QingYunkeRobotServiceImpl();

        if(ss.contains("你好")){
            resBuffer.append("你好，我是柯南，一个卡哇伊的智能机器人");
        }else if(ss.contains("我是章贺龙")) {
            resBuffer.append("感谢爸爸创造了我！");
        }else{
                Response answer = qingYunkeRobotService.answer(ss);
                String res = answer.getContent();
                resBuffer.append(res);
        }

        MessageChain reply = new MessageChainBuilder()
                .append(resBuffer.toString())
                .build();

        return reply;
    }
}

class WithConfiguration1 {
    public static void main(String[] args) {
        // 使用自定义配置
        Bot bot = BotFactory.INSTANCE.newBot(123456, "test", new BotConfiguration() {{
            fileBasedDeviceInfo(); // 使用 device.json 存储设备信息
            setProtocol(MiraiProtocol.ANDROID_PAD); // 切换协议
        }});
        bot.login();

        JavaMain.afterLogin(bot);
    }
}
class Test{
    public static void main(String[] args) throws IOException {
        QingYunkeRobotServiceImpl qingYunkeRobotService = new QingYunkeRobotServiceImpl();
        String ss = "北京天气";
        Response answer = qingYunkeRobotService.answer(ss);
        System.out.println(answer.getContent());
    }

}


