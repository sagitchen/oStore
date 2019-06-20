package oso.server;


import com.lmax.disruptor.EventHandler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-20
 */
public class StoreEventHandler implements EventHandler<StoreEvent> {
    public static StoreHandler storeHandler = StoreHandler.getInstance();
    @Override
    public void onEvent(StoreEvent event, long sequence, boolean endOfBatch) throws Exception {
        if (event.getEventType() == EventType.READ) {
            File file1 = new File("/Users/weichen/data/01.jpg");
            byte[] image1 = storeHandler.read(event.getPayload().getOffset(), event.getPayload().getSize());
            BufferedOutputStream out1 = new BufferedOutputStream(new FileOutputStream(file1));
            out1.write(image1);
            out1.flush();
        } else if (event.getEventType() == EventType.WRITE) {
            storeHandler.write(event.getPayload().getContent());
        }
    }
}
