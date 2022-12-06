package cn.sincerity.common.message;

import org.apache.rocketmq.common.message.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 关于 RocketMQ 批量发送的消息分割器
 *
 * @author Ht7_Sincerity
 * @date 2022/11/10
 */
public class RocketMessageSplitter implements Iterator<List<Message>> {

    final List<Message> messages;

    int splitSize = 4 * 1024 * 1024;

    int currentIndex;

    public RocketMessageSplitter(List<Message> messages) {
        this.messages = messages;
    }

    public RocketMessageSplitter(List<Message> messages, int splitSize) {
        this.messages = messages;
        this.splitSize = splitSize;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < messages.size();
    }

    @Override
    public List<Message> next() {
        int size = 0;
        int toIndex = currentIndex;
        while (toIndex < messages.size()) {
            Message message = messages.get(toIndex);
            size += calculateSize(message);
            if (size > splitSize) {
                if (toIndex == currentIndex)
                    toIndex++;
                break;
            }
            toIndex++;
        }

        List<Message> subList = messages.subList(currentIndex, toIndex);
        currentIndex = toIndex;
        return subList;
    }

    int calculateSize(Message message) {
        if (message == null) {
            return 0;
        }
        int size = 20;
        size += message.getTopic().length();
        size += message.getBody().length;
        for (Map.Entry<String, String> entry : message.getProperties().entrySet()) {
            size += entry.getKey().length();
            size += entry.getValue().length();
        }
        return size;
    }
}
