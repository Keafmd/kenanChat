package com.keafmd.mirai;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import com.keafmd.model.Response;
import com.keafmd.service.QingYunkeRobotServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Keafmd
 *
 * @ClassName: MultiPersonChat
 * @Description: 多人聊天
 * @author: 牛哄哄的柯南
 * @date: 2021-08-05 10:37
 */
public class MultiPersonChat {

    private final static Logger logger = LoggerFactory.getLogger(MultiPersonChat.class);
    public static void main(String[] args) {
        long serviceNumber1 = 674155030;
        long serviceNumber2 = 1796463945;
        Bot bot = BotFactory.INSTANCE.newBot(2787732627L, "200003050");
        bot.login();
        List<Long> serviceList = new ArrayList();
        serviceList.add(serviceNumber1);
        serviceList.add(serviceNumber2);
        serviceList.add(2961382983L);
        serviceList.add(2057736907L);
//        MultiPersonChat.serviceObject(bot, serviceList);
        MultiPersonChat.serviceObject(bot);


    }

    public static void serviceObject(Bot bot, List serviceNumbers) {
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, (event) -> {


            //if (event.getSender().getId() == (long)serviceNumbers.get(0)) {
            if (serviceNumbers.contains(event.getSender().getId())) {
                try {
                    event.getSubject().sendMessage(new MessageChainBuilder()
                            .append(automaticResponse(event.getMessage()))
                            .build()
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void serviceObject(Bot bot) {
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, (event) -> {

            try {
                event.getSubject().sendMessage(new MessageChainBuilder()
                        .append(automaticResponse(event.getMessage()))
                        .build()
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static MessageChain automaticResponse(MessageChain news) throws IOException {
        String ss = news.contentToString();
        StringBuffer resBuffer = new StringBuffer();
        QingYunkeRobotServiceImpl qingYunkeRobotService = new QingYunkeRobotServiceImpl();

        logger.info(ss);

        if (ss.contains("你好")) {
            resBuffer.append("你好,我是柯南,一个卡哇伊的智能机器人,是章贺龙爸爸创造了我");
        } else if (ss.contains("我是章贺龙")) {
            resBuffer.append("感谢爸爸创造了我！");
        } else if (ss.contains("keafmd")) {
            resBuffer.append("牛哄哄的柯南！");
        } else if (ss.contains("博客地址")) {
            resBuffer.append("https://keafmd.blog.csdn.net/");
        } else {
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


