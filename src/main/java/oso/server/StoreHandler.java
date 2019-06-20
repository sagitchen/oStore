package oso.server;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-18
 */
public class StoreHandler {
    private static final String STORE_FILE_NAME = "/Users/weichen/data/store-01";

    private static RandomAccessFile randomAccessFile;

    private static int GLOBAL_OFFSET = 0;

    private static StoreHandler STORE_HANDLER_INSTANCE = new StoreHandler();

    private StoreHandler() {
        try {
            randomAccessFile = new RandomAccessFile(new File(STORE_FILE_NAME), "rw");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StoreHandler getInstance() {
        return STORE_HANDLER_INSTANCE;
    }

    public WriteResult write(byte[] content) {
        WriteResult writeResult = null;

        try {
            MappedByteBuffer buffer =
                    randomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, GLOBAL_OFFSET, content.length);
            buffer.put(content);

            writeResult = new WriteResult();
            writeResult.setOffset(GLOBAL_OFFSET);
            writeResult.setSize(content.length);

            GLOBAL_OFFSET += content.length;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return writeResult;
    }

    public byte[] read(int readOffset, int size) {
        byte[] result = null;
        try {
            MappedByteBuffer buffer =
                    randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, readOffset, size);
            result = new byte[size];
            for (int i = 0; i < size; i++) {
                result[i] = buffer.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
