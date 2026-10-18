package io.papermc.hangar.model.common.servers;

public enum ServerVerificationStatus {
    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected");

    private String status;

    ServerVerificationStatus(String status) {
        this.status = status;
    }
}
