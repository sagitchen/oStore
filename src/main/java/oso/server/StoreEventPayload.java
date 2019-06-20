package oso.server;

import lombok.Data;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-20
 */
@Data
public class StoreEventPayload {
    private byte[] content;

    private int offset;

    private int size;
}
