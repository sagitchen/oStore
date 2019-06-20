package oso.server;

import lombok.Data;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-20
 */
@Data
public class StoreEvent {
    private EventType eventType;

    private StoreEventPayload payload;

    public void clear() {
        payload = null;
    }
}
