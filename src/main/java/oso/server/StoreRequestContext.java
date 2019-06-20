package oso.server;

import lombok.Data;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-20
 */
@Data
public class StoreRequestContext {
    private EventType type;

    private byte[] content;

    private int offset;

    private int size;
}
