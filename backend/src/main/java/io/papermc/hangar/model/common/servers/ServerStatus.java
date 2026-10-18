package io.papermc.hangar.model.common.servers;

public enum ServerStatus {
    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected");

    private final String status;

    ServerStatus(final String status) {
        this.status = status;
    }
}
