package io.github.wuma2020.logpush.appenders.log4j;

import io.github.wuma2020.logpush.utils.Log4jPushCommonUtils;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender;
import org.apache.logging.log4j.core.appender.FileManager;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.net.Advertiser;
import org.apache.logging.log4j.core.util.Booleans;
import org.apache.logging.log4j.core.util.Integers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Copyright (C), 2022-2023, wuma2020
 * Author: wuma2020
 * Date: 2023/2/5 18:28
 * FileName: Log4jConsolePush
 * Description: 自定义console appender
 */
@Plugin(
        name = "File",
        category = "Core",
        elementType = "appender",
        printObject = true
)
public final class Log4jFilePush extends AbstractOutputStreamAppender<FileManager> {
    /**
     * The constant PLUGIN_NAME.
     */
    public static final String PLUGIN_NAME = "File";
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    private final String fileName;
    private final Advertiser advertiser;
    private final Object advertisement;

    /**
     * Create appender log 4 j file push.
     *
     * @param <B>              the type parameter
     * @param fileName         the file name
     * @param append           the append
     * @param locking          the locking
     * @param name             the name
     * @param immediateFlush   the immediate flush
     * @param ignoreExceptions the ignore exceptions
     * @param bufferedIo       the buffered io
     * @param bufferSizeStr    the buffer size str
     * @param layout           the layout
     * @param filter           the filter
     * @param advertise        the advertise
     * @param advertiseUri     the advertise uri
     * @param config           the config
     * @return the log 4 j file push
     * @deprecated
     */
    @Deprecated
    public static <B extends Log4jFilePush.Builder<B>> Log4jFilePush createAppender(final String fileName, final String append, final String locking, final String name, final String immediateFlush, final String ignoreExceptions, final String bufferedIo, final String bufferSizeStr, final Layout<? extends Serializable> layout, final Filter filter, final String advertise, final String advertiseUri, final Configuration config) {
        return ((Log4jFilePush.Builder) ((Log4jFilePush.Builder) ((Log4jFilePush.Builder) ((Log4jFilePush.Builder) ((Log4jFilePush.Builder) ((Log4jFilePush.Builder) ((Log4jFilePush.Builder) ((Log4jFilePush.Builder) newBuilder().withAdvertise(Boolean.parseBoolean(advertise)).withAdvertiseUri(advertiseUri).withAppend(Booleans.parseBoolean(append, true)).withBufferedIo(Booleans.parseBoolean(bufferedIo, true))).withBufferSize(Integers.parseInt(bufferSizeStr, 8192))).setConfiguration(config)).withFileName(fileName).setFilter(filter)).setIgnoreExceptions(Booleans.parseBoolean(ignoreExceptions, true))).withImmediateFlush(Booleans.parseBoolean(immediateFlush, true))).setLayout(layout)).withLocking(Boolean.parseBoolean(locking)).setName(name)).build();
    }

    /**
     * New builder b.
     *
     * @param <B> the type parameter
     * @return the b
     */
    @PluginBuilderFactory
    public static <B extends Log4jFilePush.Builder<B>> B newBuilder() {
        return (B) (new Builder()).asBuilder();
    }

    private Log4jFilePush(final String name, final Layout<? extends Serializable> layout, final Filter filter, final FileManager manager, final String filename, final boolean ignoreExceptions, final boolean immediateFlush, final Advertiser advertiser, final Property[] properties) {
        super(name, layout, filter, ignoreExceptions, immediateFlush, properties, manager);
        if (advertiser != null) {
            Map<String, String> configuration = new HashMap(layout.getContentFormat());
            configuration.putAll(manager.getContentFormat());
            configuration.put("contentType", layout.getContentType());
            configuration.put("name", name);
            this.advertisement = advertiser.advertise(configuration);
        } else {
            this.advertisement = null;
        }

        this.fileName = filename;
        this.advertiser = advertiser;
    }

    @Override
    public void append(LogEvent event) {
        super.append(event);

        Log4jPushCommonUtils.push(event);
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return this.fileName;
    }

    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        this.setStopping();
        super.stop(timeout, timeUnit, false);
        if (this.advertiser != null) {
            this.advertiser.unadvertise(this.advertisement);
        }

        this.setStopped();
        return true;
    }

    /**
     * The type Builder.
     *
     * @param <B> the type parameter
     */
    public static class Builder<B extends Log4jFilePush.Builder<B>> extends AbstractOutputStreamAppender.Builder<B> implements org.apache.logging.log4j.core.util.Builder<Log4jFilePush> {
        @PluginBuilderAttribute
        @Required
        private String fileName;
        @PluginBuilderAttribute
        private boolean append = true;
        @PluginBuilderAttribute
        private boolean locking;
        @PluginBuilderAttribute
        private boolean advertise;
        @PluginBuilderAttribute
        private String advertiseUri;
        @PluginBuilderAttribute
        private boolean createOnDemand;
        @PluginBuilderAttribute
        private String filePermissions;
        @PluginBuilderAttribute
        private String fileOwner;
        @PluginBuilderAttribute
        private String fileGroup;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
        }

        public Log4jFilePush build() {
            boolean bufferedIo = this.isBufferedIo();
            int bufferSize = this.getBufferSize();
            if (this.locking && bufferedIo) {
                Log4jFilePush.LOGGER.warn("Locking and buffering are mutually exclusive. No buffering will occur for {}", this.fileName);
                bufferedIo = false;
            }

            if (!bufferedIo && bufferSize > 0) {
                Log4jFilePush.LOGGER.warn("The bufferSize is set to {} but bufferedIo is false: {}", bufferSize, bufferedIo);
            }

            Layout<? extends Serializable> layout = this.getOrCreateLayout();
            FileManager manager = FileManager.getFileManager(this.fileName, this.append, this.locking, bufferedIo, this.createOnDemand, this.advertiseUri, layout, bufferSize, this.filePermissions, this.fileOwner, this.fileGroup, this.getConfiguration());
            return manager == null ? null : new Log4jFilePush(this.getName(), layout, this.getFilter(), manager, this.fileName, this.isIgnoreExceptions(), !bufferedIo || this.isImmediateFlush(), this.advertise ? this.getConfiguration().getAdvertiser() : null, this.getPropertyArray());
        }

        /**
         * Gets advertise uri.
         *
         * @return the advertise uri
         */
        public String getAdvertiseUri() {
            return this.advertiseUri;
        }

        /**
         * Gets file name.
         *
         * @return the file name
         */
        public String getFileName() {
            return this.fileName;
        }

        /**
         * Is advertise boolean.
         *
         * @return the boolean
         */
        public boolean isAdvertise() {
            return this.advertise;
        }

        /**
         * Is append boolean.
         *
         * @return the boolean
         */
        public boolean isAppend() {
            return this.append;
        }

        /**
         * Is create on demand boolean.
         *
         * @return the boolean
         */
        public boolean isCreateOnDemand() {
            return this.createOnDemand;
        }

        /**
         * Is locking boolean.
         *
         * @return the boolean
         */
        public boolean isLocking() {
            return this.locking;
        }

        /**
         * Gets file permissions.
         *
         * @return the file permissions
         */
        public String getFilePermissions() {
            return this.filePermissions;
        }

        /**
         * Gets file owner.
         *
         * @return the file owner
         */
        public String getFileOwner() {
            return this.fileOwner;
        }

        /**
         * Gets file group.
         *
         * @return the file group
         */
        public String getFileGroup() {
            return this.fileGroup;
        }

        /**
         * With advertise b.
         *
         * @param advertise the advertise
         * @return the b
         */
        public B withAdvertise(final boolean advertise) {
            this.advertise = advertise;
            return (B) this.asBuilder();
        }

        /**
         * With advertise uri b.
         *
         * @param advertiseUri the advertise uri
         * @return the b
         */
        public B withAdvertiseUri(final String advertiseUri) {
            this.advertiseUri = advertiseUri;
            return (B) this.asBuilder();
        }

        /**
         * With append b.
         *
         * @param append the append
         * @return the b
         */
        public B withAppend(final boolean append) {
            this.append = append;
            return (B) this.asBuilder();
        }

        /**
         * With file name b.
         *
         * @param fileName the file name
         * @return the b
         */
        public B withFileName(final String fileName) {
            this.fileName = fileName;
            return (B) this.asBuilder();
        }

        /**
         * With create on demand b.
         *
         * @param createOnDemand the create on demand
         * @return the b
         */
        public B withCreateOnDemand(final boolean createOnDemand) {
            this.createOnDemand = createOnDemand;
            return (B) this.asBuilder();
        }

        /**
         * With locking b.
         *
         * @param locking the locking
         * @return the b
         */
        public B withLocking(final boolean locking) {
            this.locking = locking;
            return (B) this.asBuilder();
        }

        /**
         * With file permissions b.
         *
         * @param filePermissions the file permissions
         * @return the b
         */
        public B withFilePermissions(final String filePermissions) {
            this.filePermissions = filePermissions;
            return (B) this.asBuilder();
        }

        /**
         * With file owner b.
         *
         * @param fileOwner the file owner
         * @return the b
         */
        public B withFileOwner(final String fileOwner) {
            this.fileOwner = fileOwner;
            return (B) this.asBuilder();
        }

        /**
         * With file group b.
         *
         * @param fileGroup the file group
         * @return the b
         */
        public B withFileGroup(final String fileGroup) {
            this.fileGroup = fileGroup;
            return (B) this.asBuilder();
        }
    }
}