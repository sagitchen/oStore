package oso.server;


import com.lmax.disruptor.EventHandler;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-20
 */
public class StoreClearEventHandler implements EventHandler<StoreEvent> {
    @Override
    public void onEvent(StoreEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.clear();
    }
}
