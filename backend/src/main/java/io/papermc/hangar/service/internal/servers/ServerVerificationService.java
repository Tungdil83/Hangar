package io.papermc.hangar.service.internal.servers;

import io.papermc.hangar.HangarComponent;
import io.papermc.hangar.db.dao.internal.table.ServerDAO;
import io.papermc.hangar.db.dao.internal.table.ServerVerificationDAO;
import io.papermc.hangar.model.db.servers.ServerVerificationTable;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ServerVerificationService extends HangarComponent {

    private final ServerVerificationDAO serverVerificationDAO;
    private final ServerDAO serverDAO;

    public ServerVerificationService(final ServerVerificationDAO serverVerificationDAO, final ServerDAO serverDAO) {
        this.serverVerificationDAO = serverVerificationDAO;
        this.serverDAO = serverDAO;
    }

    public void submitForVerification(UUID uuid) {
        Long serverId = this.serverDAO.getServerWithoutStatusRequirement(uuid).getId();
        this.serverVerificationDAO.submitForVerification(uuid, serverId);
    }

    public void approveServer(UUID uuid, String notes) {
        this.serverVerificationDAO.approveServer(uuid, notes, this.getHangarUserId());
    }

    public void rejectServer(UUID uuid, String notes) {
        this.serverVerificationDAO.rejectServer(uuid, notes, this.getHangarUserId());
    }

    public List<ServerVerificationTable> getVerificationQueue() {
        return this.serverVerificationDAO.getVerificationQueue();
    }
}
