package io.papermc.hangar.service.internal.servers;

import io.papermc.hangar.HangarComponent;
import io.papermc.hangar.db.dao.internal.table.ServerDAO;
import io.papermc.hangar.db.dao.internal.table.UserDAO;
import io.papermc.hangar.model.common.Permission;
import io.papermc.hangar.model.db.projects.ProjectOwner;
import io.papermc.hangar.model.db.servers.ServerOwner;
import io.papermc.hangar.model.db.servers.ServerTable;
import io.papermc.hangar.model.internal.api.requests.servers.UpdateServerForm;
import io.papermc.hangar.service.internal.organizations.OrganizationService;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

@Service
public class ServerService extends HangarComponent {

    private final ServerDAO serverDAO;
    private final OrganizationService organizationService;

    public ServerService(final ServerDAO serverDAO, final UserDAO userDAO, final OrganizationService organizationService) {
        this.serverDAO = serverDAO;
        this.organizationService = organizationService;
    }

    public ServerTable createServer(final ServerTable server) {
        ServerTable serverTable = this.serverDAO.createServer(server);
        return serverTable;
    }

    public ServerTable updateServer(final UUID uuid, final UpdateServerForm updateServer) {
        ServerTable serverTable = this.serverDAO.updateServer(uuid, updateServer);
        return serverTable;
    }

    public ServerTable getServer(final UUID uuid) {
        return this.serverDAO.getServer(uuid);
    }

    public List<ServerTable> filterServers(String name) {
        return this.serverDAO.filterServers(name);
    }

    public List<ServerTable> listServers() {
        return this.serverDAO.listServers();
    }

    public void deleteServer(final UUID uuid) {
        this.serverDAO.deleteServer(uuid);
    }

    public void approveServer(final UUID uuid) {
        this.serverDAO.approveServer(uuid);
    }

    public void rejectServer(final UUID uuid) {
        this.serverDAO.rejectServer(uuid);
    }

    public @Nullable ServerOwner getServerOwner(final long userId) {
        if (Objects.equals(this.getHangarUserId(), userId)) {
            return this.getHangarPrincipal();
        }
        return this.organizationService.getOrganizationTableWithPermission(this.getHangarPrincipal().getId(), userId, Permission.CreateServer);
    }
}
