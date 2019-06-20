package oso.server;

import com.lmax.disruptor.EventFactory;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-20
 */
public class StoreEventFactory implements EventFactory<StoreEvent> {
    @Override
    public StoreEvent newInstance() {
        return new StoreEvent();
    }
}
