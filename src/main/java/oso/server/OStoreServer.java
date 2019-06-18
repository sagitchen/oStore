package oso.server;

import lombok.Data;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-18
 */
@Data
public class OStoreServer {
    private String id;
    private String dataCenterId;
    private String rackId;
    private String instanceIp;
    private int capacityInKb;
    private int occupiedCapacityInKb;
}
