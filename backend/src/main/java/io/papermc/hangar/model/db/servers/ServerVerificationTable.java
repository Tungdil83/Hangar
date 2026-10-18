package io.papermc.hangar.model.db.servers;

import io.papermc.hangar.model.common.servers.ServerVerificationStatus;
import io.papermc.hangar.model.db.Table;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

public class ServerVerificationTable extends Table {
    private UUID uuid;
    private Integer serverId;
    private Integer reviewerId;
    private ServerVerificationStatus status;
    private String notes;

    public ServerVerificationTable(final long id, final UUID uuid, final Integer serverId, final Integer reviewerId,
                                   final ServerVerificationStatus status, final String notes) {
        super(id);
        this.uuid = uuid;
        this.serverId = serverId;
        this.reviewerId = reviewerId;
        this.status = status;
        this.notes = notes;
    }

    @JdbiConstructor
    public ServerVerificationTable(final OffsetDateTime createdAt, final long id, final UUID uuid, final Integer serverId,
                                   final Integer reviewerId, final  ServerVerificationStatus status, final String notes) {
        super(createdAt, id);
        this.uuid = uuid;
        this.serverId = serverId;
        this.reviewerId = reviewerId;
        this.status = status;
        this.notes = notes;
    }

    public UUID getUuid() { return uuid; }

    public void setUUID(final UUID uuid) { this.uuid = uuid; }

    public Integer getServerId() { return serverId; }

    public void setServerId(final Integer serverId) { this.serverId = serverId; }

    public Integer getReviewerId() { return reviewerId; }

    public void setReviewerId(final Integer reviewerId) { this.reviewerId = reviewerId; }

    public ServerVerificationStatus getStatus() { return status; }

    public void setStatus(final ServerVerificationStatus status) { this.status = status; }

    public String getNotes() { return notes; }

    public void setNotes(final String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return "ServerVerificationTable{" +
            "uuid='" + this.uuid + "'" +
            ", serverId=" + this.serverId +
            ", reviewerId=" + this.reviewerId +
            ", status=" + this.status +
            ", notes='" + this.notes + "'";
    }
}
