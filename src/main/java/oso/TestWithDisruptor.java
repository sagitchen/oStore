package oso;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import oso.server.*;

import java.io.File;
import java.io.FileInputStream;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-20
 */
public class TestWithDisruptor {
    public static void main(String[] args) throws Exception {
        // The factory for the event
        StoreEventFactory factory = new StoreEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
        Disruptor<StoreEvent> disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        // Connect the handler
        disruptor.handleEventsWith(new StoreEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<StoreEvent> ringBuffer = disruptor.getRingBuffer();

        StoreEventProducer producer = new StoreEventProducer(ringBuffer);

        File file = new File("/Users/weichen/Documents/image.jpg");

        byte[] content = new byte[(int)file.length()];
        FileInputStream in = new FileInputStream(file);
        int size;
        while ((size = in.read(content)) != -1) {
            System.out.println(size);
        }

        StoreRequestContext writeContent = new StoreRequestContext();
        writeContent.setType(EventType.WRITE);
        writeContent.setContent(content);
        producer.onData(writeContent);
        Thread.sleep(1000L);

        StoreRequestContext readContent = new StoreRequestContext();
        readContent.setType(EventType.READ);
        readContent.setOffset(0);
        readContent.setSize(content.length);
        producer.onData(readContent);

        Thread.sleep(1000L);
    }
}
