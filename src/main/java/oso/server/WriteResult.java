package oso.server;

import lombok.Data;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-19
 */
@Data
public class WriteResult {
    private String fileName;
    private int offset;
    private int size;
}
