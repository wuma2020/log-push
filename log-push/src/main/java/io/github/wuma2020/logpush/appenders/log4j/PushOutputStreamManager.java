package io.github.wuma2020.logpush.appenders.log4j;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.OutputStreamManager;

import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * Copyright (C), 2022-2023, wuma2020
 * Author: wuma2020
 * Date: 2023/2/5 20:27
 * FileName: ManagerOut
 * Description:
 */
public class PushOutputStreamManager extends OutputStreamManager {
    /**
     * Instantiates a new Push output stream manager.
     *
     * @param os          the os
     * @param streamName  the stream name
     * @param layout      the layout
     * @param writeHeader the write header
     */
    protected PushOutputStreamManager(OutputStream os, String streamName, Layout<?> layout, boolean writeHeader) {
        super(os, streamName, layout, writeHeader);
    }

    /**
     * Instantiates a new Push output stream manager.
     *
     * @param os          the os
     * @param streamName  the stream name
     * @param layout      the layout
     * @param writeHeader the write header
     * @param bufferSize  the buffer size
     */
    protected PushOutputStreamManager(OutputStream os, String streamName, Layout<?> layout, boolean writeHeader, int bufferSize) {
        super(os, streamName, layout, writeHeader, bufferSize);
    }

    /**
     * Instantiates a new Push output stream manager.
     *
     * @param os          the os
     * @param streamName  the stream name
     * @param layout      the layout
     * @param writeHeader the write header
     * @param byteBuffer  the byte buffer
     */
    protected PushOutputStreamManager(OutputStream os, String streamName, Layout<?> layout, boolean writeHeader, ByteBuffer byteBuffer) {
        super(os, streamName, layout, writeHeader, byteBuffer);
    }

    /**
     * Instantiates a new Push output stream manager.
     *
     * @param loggerContext  the logger context
     * @param os             the os
     * @param streamName     the stream name
     * @param createOnDemand the create on demand
     * @param layout         the layout
     * @param writeHeader    the write header
     * @param byteBuffer     the byte buffer
     */
    protected PushOutputStreamManager(LoggerContext loggerContext, OutputStream os, String streamName, boolean createOnDemand, Layout<? extends Serializable> layout, boolean writeHeader, ByteBuffer byteBuffer) {
        super(loggerContext, os, streamName, createOnDemand, layout, writeHeader, byteBuffer);
    }
}
