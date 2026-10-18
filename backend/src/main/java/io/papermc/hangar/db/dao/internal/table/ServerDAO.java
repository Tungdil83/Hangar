package io.papermc.hangar.db.dao.internal.table;

import io.papermc.hangar.model.db.servers.ServerTable;
import io.papermc.hangar.model.internal.api.requests.servers.UpdateServerForm;
import java.util.List;
import java.util.UUID;
import org.jdbi.v3.spring.JdbiRepository;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@JdbiRepository
@RegisterConstructorMapper(ServerTable.class)
public interface ServerDAO {
    @Timestamped
    @SqlUpdate("INSERT INTO servers (id, uuid, name, ip, port, description, banner_image, logo_image, tags, website_url, discord_url, minecraft_version, player_count, owner_id, created_at, updated_at, status) VALUES (:id, :uuid, :name, :ip, :port, :description, :bannerImage, :logoImage, :tags, :websiteURL, :discordURL, :minecraftVersion, :playerCount, :ownerId, :now, :now, :status)")
    ServerTable createServer(@BindBean ServerTable server);

    @Timestamped
    @SqlUpdate("UPDATE servers SET name = :name, ip = :ip, port = :port, description = :description, banner_image = :bannerImage, logo_image = :logoImage, tags = :tags, website_url = :websiteURL, discord_url = :discordURL, minecraft_version = :minecraftVersion, updated_at = :now WHERE uuid = :serveruuid")
    ServerTable updateServer(UUID serveruuid, @BindBean UpdateServerForm updateServer);

    @SqlQuery("SELECT * FROM servers WHERE uuid=:uuid")
    ServerTable getServer(UUID uuid);

    // TODO: add more filter criteria, maybe separate listServers() into listAllServers() and listVerificatedServers()
    @SqlQuery("SELECT * FROM servers WHERE name LIKE :name AND status = 'APPROVED'")
    List<ServerTable> filterServers(String name);

    @SqlQuery("SELECT * FROM servers WHERE status = 'APPROVED'")
    List<ServerTable> listServers();

    @SqlUpdate("DELETE FROM servers WHERE uuid = :uuid")
    void deleteServer(UUID uuid);

    @SqlUpdate("UPDATE servers SET status = 'APPROVED' WHERE uuid = :uuid")
    void approveServer(UUID uuid);

    @SqlUpdate("UPDATE servers SET status = 'REJECTED' WHERE uuid = :uuid")
    void rejectServer(UUID uuid);
}
