package oso.server;

import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.RingBuffer;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-20
 */
public class StoreEventProducer {
    private final RingBuffer<StoreEvent> ringBuffer;

    public StoreEventProducer(RingBuffer<StoreEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorTwoArg<StoreEvent, EventType, StoreEventPayload> TRANSLATOR =
            (event, sequence, type, payload) -> {
                event.setEventType(type);
                event.setPayload(payload);
            };

    public void onData(StoreRequestContext storeRequestContext) {
        StoreEventPayload payload = new StoreEventPayload();
        payload.setContent(storeRequestContext.getContent());
        payload.setOffset(storeRequestContext.getOffset());
        payload.setSize(storeRequestContext.getSize());

        ringBuffer.publishEvent(TRANSLATOR, storeRequestContext.getType(), payload);
    }
}
