package io.papermc.hangar.controller.internal;

import io.papermc.hangar.HangarComponent;
import io.papermc.hangar.model.common.PermissionType;
import io.papermc.hangar.model.db.servers.ServerVerificationTable;
import io.papermc.hangar.model.internal.api.requests.servers.NewServerForm;
import io.papermc.hangar.model.internal.api.requests.servers.UpdateServerForm;
import io.papermc.hangar.service.internal.servers.ServerFactory;
import io.papermc.hangar.service.internal.servers.ServerService;
import io.papermc.hangar.service.internal.servers.ServerVerificationService;
import io.papermc.hangar.model.common.NamedPermission;
import io.papermc.hangar.model.db.servers.ServerTable;
import io.papermc.hangar.security.annotations.Anyone;
import io.papermc.hangar.security.annotations.LoggedIn;
import io.papermc.hangar.security.annotations.permission.PermissionRequired;
import io.papermc.hangar.security.annotations.ratelimit.RateLimit;
import io.papermc.hangar.security.annotations.unlocked.Unlocked;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RateLimit(path = "server", greedy = true)
@RequestMapping(path = "/api/internal/servers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServerController extends HangarComponent {

    private final ServerService serverService;
    private final ServerVerificationService serverVerificationService;
    private final ServerFactory serverFactory;

    @Autowired
    public ServerController(final ServerService serverService, final ServerVerificationService serverVerificationService, final ServerFactory serverFactory) {
        this.serverService = serverService;
        this.serverVerificationService = serverVerificationService;
        this.serverFactory = serverFactory;
    }

    @Anyone
    @GetMapping("/{uuid}")
    public ResponseEntity<ServerTable> getServer(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(this.serverService.getServer(uuid));
    }

    @Anyone
    @GetMapping
    public ResponseEntity<List<ServerTable>> listServers() {
        return ResponseEntity.ok(this.serverService.listServers());
    }

    // TODO: add featured servers

    @Unlocked
    @LoggedIn
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RateLimit(overdraft = 5, refillTokens = 1, refillSeconds = 60)
    public ResponseEntity<ServerTable> createServer(@RequestBody final @Valid NewServerForm newServer) {
        final ServerTable serverTable = this.serverFactory.createServer(newServer);
        return ResponseEntity.ok(serverTable);
    }

    @Unlocked
    @LoggedIn
    @PermissionRequired(type = PermissionType.SERVER, perms = NamedPermission.IS_SERVER_OWNER, args = "{#uuid}")
    @PutMapping(path = "/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServerTable> updateServer(@PathVariable("uuid") UUID uuid, @RequestBody final @Valid UpdateServerForm updateServer) {
        ServerTable serverTable = this.serverFactory.updateServer(uuid, updateServer);
        return ResponseEntity.ok(serverTable);
    }

    @Unlocked
    @LoggedIn
    @PermissionRequired(type = PermissionType.SERVER, perms = NamedPermission.DELETE_SERVER, args = "{#uuid}")
    @DeleteMapping(path = "/{uuid}")
    public void deleteServer(UUID uuid) {
        this.serverService.deleteServer(uuid);
    }

    // TODO: implement get all servers for user

    @Unlocked
    @LoggedIn
    @PermissionRequired(NamedPermission.REVIEWER)
    @GetMapping(path = "/verification/queue")
    public ResponseEntity<List<ServerVerificationTable>> getVerificationQueue() {
        return ResponseEntity.ok(this.serverVerificationService.getVerificationQueue());
    }

    @Unlocked
    @LoggedIn
    @PermissionRequired(NamedPermission.REVIEWER)
    @PostMapping(path = "/verification/{uuid}/approve")
    public void approveVerification(@PathVariable("uuid") UUID uuid, String notes) {
        this.serverService.approveServer(uuid);
        this.serverVerificationService.approveServer(uuid, notes);
    }

    @Unlocked
    @LoggedIn
    @PermissionRequired(NamedPermission.REVIEWER)
    @PostMapping(path = "/verification/{uuid}/reject")
    public void rejectVerification(@PathVariable("uuid") UUID uuid, String notes) {
        this.serverService.rejectServer(uuid);
        this.serverVerificationService.rejectServer(uuid, notes);
    }

    // TODO: add server statistics

}
