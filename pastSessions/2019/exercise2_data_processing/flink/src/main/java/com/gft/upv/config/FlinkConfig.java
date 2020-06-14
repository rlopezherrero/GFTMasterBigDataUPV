package com.gft.upv.config;

import com.typesafe.config.Config;

import java.io.Serializable;

public class FlinkConfig implements Serializable{
    private final String mode;
    private final String checkpointDir;
    private final String checkpointData;
    private final long msCheckpointing;

    public FlinkConfig(Config config) {
        this.mode = config.getString("flink.mode");
        this.checkpointDir = config.getString("flink.checkpointDir");
        this.checkpointData = config.getString("flink.checkpointData");
        this.msCheckpointing = config.getLong("flink.msCheckpointing");
    }

    public String getMode() {
        return mode;
    }

    public long getMsCheckpointing() {
        return msCheckpointing;
    }

    public String getCheckpointDir() {
        return checkpointDir;
    }

    public String getCheckpointData() {
        return checkpointData;
    }
}
