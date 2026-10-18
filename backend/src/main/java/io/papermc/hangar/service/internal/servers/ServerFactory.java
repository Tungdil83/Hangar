package io.papermc.hangar.service.internal.servers;

import io.papermc.hangar.HangarComponent;
import io.papermc.hangar.db.dao.internal.table.ServerDAO;
import io.papermc.hangar.exceptions.HangarApiException;
import io.papermc.hangar.model.db.servers.ServerOwner;
import io.papermc.hangar.model.db.servers.ServerTable;
import io.papermc.hangar.model.internal.api.requests.servers.NewServerForm;
import io.papermc.hangar.model.internal.api.requests.servers.UpdateServerForm;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServerFactory extends HangarComponent {

    private final ServerService serverService;
    private final ServerDAO serverDAO;
    private final ServerVerificationService serverVerificationService;

    public ServerFactory(final ServerService serverService, final ServerDAO serverDAO, final ServerVerificationService serverVerificationService) {
        this.serverService = serverService;
        this.serverDAO = serverDAO;
        this.serverVerificationService = serverVerificationService;
    }

    @Transactional
    public ServerTable createServer(final NewServerForm newServer) {
        final ServerOwner serverOwner = this.serverService.getServerOwner(newServer.getOwnerId());
        if (serverOwner == null) {
            throw new HangarApiException(HttpStatus.BAD_REQUEST, "error.server.ownerNotFound");
        }
        ServerTable serverTable = null;
        try {
            serverTable = this.serverService.createServer(new ServerTable(serverOwner, newServer));
            this.serverVerificationService.submitForVerification(serverTable.getUUID());
        } catch (final Exception exception) {
            if (serverTable != null) {
                this.serverDAO.deleteServer(serverTable.getUUID());
            }
            throw exception;
        }

        return serverTable;
    }

    @Transactional
    public ServerTable updateServer(final UUID uuid, final UpdateServerForm updateServer) {
        ServerTable serverTable = this.serverService.updateServer(uuid, updateServer);
        return serverTable;
    }

}
