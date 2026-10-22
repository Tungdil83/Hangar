package io.papermc.hangar.db.dao.internal.table;

import io.papermc.hangar.model.db.servers.ServerVerificationTable;
import java.util.List;
import java.util.UUID;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.spring.JdbiRepository;

@JdbiRepository
@RegisterConstructorMapper(ServerVerificationTable.class)
public interface ServerVerificationDAO {
    @SqlUpdate("INSERT INTO server_verifications (uuid, server_id) VALUES (:uuid, :id)")
    void submitForVerification(UUID uuid, Long id);

    @Timestamped
    @SqlUpdate("UPDATE server_verifications SET status = 'APPROVED', notes = :notes, reviewer_id = :reviewerId, updated_at = :now WHERE uuid = :uuid")
    void approveServer(UUID uuid, String notes, Long reviewerId);

    @Timestamped
    @SqlUpdate("UPDATE server_verifications SET status = 'REJECTED', notes = :notes, reviewer_id = :reviewerId, updated_at = :now WHERE uuid = :uuid")
    void rejectServer(UUID uuid, String notes, Long reviewerId);

    @SqlQuery("SELECT * FROM server_verifications WHERE status = 'PENDING' ORDER BY created_at DESC")
    List<ServerVerificationTable> getVerificationQueue();

    @SqlUpdate("DELETE FROM server_verifications WHERE uuid = :uuid")
    void deleteVerification(UUID uuid);
}
