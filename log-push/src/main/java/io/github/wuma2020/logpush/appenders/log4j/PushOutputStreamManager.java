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
    protected PushOutputStreamManager(OutputStream os, String streamName, Layout<?> layout, boolean writeHeader) {
        super(os, streamName, layout, writeHeader);
    }

    protected PushOutputStreamManager(OutputStream os, String streamName, Layout<?> layout, boolean writeHeader, int bufferSize) {
        super(os, streamName, layout, writeHeader, bufferSize);
    }

    protected PushOutputStreamManager(OutputStream os, String streamName, Layout<?> layout, boolean writeHeader, ByteBuffer byteBuffer) {
        super(os, streamName, layout, writeHeader, byteBuffer);
    }

    protected PushOutputStreamManager(LoggerContext loggerContext, OutputStream os, String streamName, boolean createOnDemand, Layout<? extends Serializable> layout, boolean writeHeader, ByteBuffer byteBuffer) {
        super(loggerContext, os, streamName, createOnDemand, layout, writeHeader, byteBuffer);
    }
}
