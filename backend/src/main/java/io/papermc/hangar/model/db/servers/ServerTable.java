package io.papermc.hangar.model.db.servers;

import io.papermc.hangar.model.Named;
import io.papermc.hangar.model.Owned;
import io.papermc.hangar.model.common.servers.ServerStatus;
import io.papermc.hangar.model.db.Table;
import io.papermc.hangar.model.internal.api.requests.servers.NewServerForm;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.jdbi.v3.core.enums.EnumByName;
import org.jdbi.v3.core.mapper.PropagateNull;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

public class ServerTable extends Table implements Owned, Named {
    private UUID uuid;
    private String name;
    private String ip;
    private Integer port;
    private String description;
    private String bannerImage;
    private String logoImage;
    // TODO: Create Server Tags
    private List<String> tags;
    private String websiteURL;
    private String discordURL;
    private String minecraftVersion;
    private Integer playerCount;
    private long ownerId;
    private ServerStatus status;

    public ServerTable(final ServerOwner serverOwner, final NewServerForm form) {
        this.uuid = UUID.randomUUID();
        this.name = form.getName();
        this.ip = form.getIp();
        this.port = form.getPort();
        this.description = form.getDescription();
        this.bannerImage = form.getBannerImage();
        this.logoImage = form.getLogoImage();
        this.tags = form.getTags();
        this.websiteURL = form.getWebsiteURL();
        this.discordURL = form.getDiscordURL();
        this.minecraftVersion = form.getMinecraftVersion();
        this.playerCount = 0;
        this.ownerId = serverOwner.getUserId();
        this.status = ServerStatus.PENDING;
    }

    protected ServerTable(final ServerTable other) {
        this.uuid = other.uuid;
        this.name = other.name;
        this.ip = other.ip;
        this.port = other.port;
        this.description = other.description;
        this.bannerImage = other.bannerImage;
        this.logoImage = other.logoImage;
        this.tags = other.tags;
        this.websiteURL = other.websiteURL;
        this.discordURL = other.discordURL;
        this.minecraftVersion = other.minecraftVersion;
        this.playerCount = other.playerCount;
        this.ownerId = other.ownerId;
        this.status = other.status;
    }

    @JdbiConstructor
    public ServerTable(final OffsetDateTime createdAt, @PropagateNull final long id, final UUID uuid, final String name, final String ip, final Integer port,
                       final String description, final String bannerImage, final String logoImage, final List<String> tags,
                       final String websiteURL, final String discordURL, final String minecraftVersion, final Integer playerCount,
                       final long ownerId, final ServerStatus status) {
        super(createdAt, id);
        this.uuid = uuid;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.description = description;
        this.bannerImage = bannerImage;
        this.logoImage = logoImage;
        this.tags = tags;
        this.websiteURL = websiteURL;
        this.discordURL = discordURL;
        this.minecraftVersion = minecraftVersion;
        this.playerCount = playerCount;
        this.ownerId = ownerId;
        this.status = status;
    }

    public UUID getUuid() { return this.uuid; }

    public void setUuid(final UUID uuid) { this.uuid = uuid; }

    @Override
    public String getName() { return this.name; }

    public void setName(final String name) { this.name = name; }

    public String getIp() { return this.ip; }

    public void setIp(final String ip) { this.ip = ip; }

    public Integer getPort() { return this.port; }

    public void setPort(final Integer port) { this.port = port; }

    public String getDescription() { return this.description; }

    public void setDescription(final String description) { this.description = description; }

    public String getBannerImage() { return this.bannerImage; }

    public void setBannerImage(final String bannerImage) { this.bannerImage = bannerImage; }

    public String getLogoImage() { return this.logoImage; }

    public void setLogoImage(final String logoImage) { this.logoImage = logoImage; }

    public List<String> getTags() { return this.tags; }

    public void setTags(final List<String> tags) { this.tags = tags; }

    public String getWebsiteURL() { return this.websiteURL; }

    public void setWebsiteURL(final String websiteURL) { this.websiteURL = websiteURL; }

    public String getDiscordURL() { return this.discordURL; }

    public void setDiscordURL(final String discordURL) { this.discordURL = discordURL; }

    public String getMinecraftVersion() { return this.minecraftVersion; }

    public void setMinecraftVersion(final String minecraftVersion) { this.minecraftVersion = minecraftVersion; }

    public Integer getPlayerCount() { return this.playerCount; }

    public void setPlayerCount(final Integer playerCount) { this.playerCount = playerCount; }

    @Override
    public long getOwnerId() { return this.ownerId; }

    public void setOwnerId(final long ownerId) { this.ownerId = ownerId; }

    @EnumByName
    public ServerStatus getStatus() { return this.status; }

    public void setStatus(final ServerStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "ServerTable{" +
            "uuid='" + this.uuid + "'" +
            ", name='" + this.name + "'" +
            ", ip='" + this.ip + "'" +
            ", port=" + this.port +
            ", description='" + this.description + "'" +
            ", bannerImage='" + this.bannerImage + "'" +
            ", logoImage='" + this.logoImage + "'" +
            ", tags=" + this.tags +
            ", websiteURL='" + this.websiteURL + "'" +
            ", discordURL='" + this.discordURL + "'" +
            ", minecraftVersion='" + this.minecraftVersion + "'" +
            ", playerCount=" + this.playerCount +
            ", ownerId=" + this.ownerId +
            ", status=" + this.status +
            "}";
    }
}
