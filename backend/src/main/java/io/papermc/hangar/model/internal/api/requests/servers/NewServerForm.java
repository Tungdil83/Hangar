package io.papermc.hangar.model.internal.api.requests.servers;

import io.papermc.hangar.controller.validations.Validate;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;

public class NewServerForm {

    // @el(root: String)
    @NotNull(message = "server.new.error.invalidName")
    private final @Validate(SpEL = "@validate.max(#root, 100)", message = "server.new.error.nameLength") String name;

    // @el(root: String)
    @NotNull(message = "server.new.error.ipRequired")
    private final @Validate(SpEL = "@validate.max(#root, 255)", message = "server.new.error.ipLength") String ip;

    // @el(root: Integer)
    @NotNull(message = "server.new.error.portRequired")
    private final Integer port;

    // @el(root: String)
    private final @Validate(SpEL = "@validate.max(#root, 5000)", message = "server.new.error.descriptionTooLong") String description;

    // @el(root: String)
    private final @Validate(SpEL = "@validate.max(#root, 255)", message = "server.new.error.bannerImageTooLong") String bannerImage;

    // @el(root: String)
    private final @Validate(SpEL = "@validate.max(#root, 255)", message = "server.new.error.logoImageTooLong") String logoImage;

    // @el(root: List<String>)
    private final @Validate(SpEL = "@validate.max(#root, 20)", message = "server.new.error.tooManyTags") List<String> tags;

    // @el(root: String)
    private final @Validate(SpEL = "@validate.max(#root, 255)", message = "server.new.error.websiteUrlTooLong") String websiteURL;

    // @el(root: String)
    private final @Validate(SpEL = "@validate.max(#root, 255)", message = "server.new.error.discordUrlTooLong") String discordURL;

    @NotNull(message = "server.new.error.minecraftVersionRequired")
    private final @Validate(SpEL = "@validate.max(#root, 50)", message = "server.new.error.minecraftVersionLength") String minecraftVersion;

    private final long ownerId;


    public NewServerForm(final String name, final String ip, final Integer port, final String description, final String bannerImage, final String logoImage, final List<String> tags, final String websiteURL, final String discordURL, final String minecraftVersion, final long ownerId) {
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
        this.ownerId = ownerId;
    }

    public String getName() { return this.name; }

    public String getIp() { return this.ip; }

    public Integer getPort() { return this.port; }

    public String getDescription() { return this.description; }

    public String getBannerImage() { return this.bannerImage; }

    public String getLogoImage() { return this.logoImage; }

    public List<String> getTags() { return this.tags; }

    public String getWebsiteURL() { return this.websiteURL; }

    public String getDiscordURL() { return this.discordURL; }

    public String getMinecraftVersion() { return this.minecraftVersion; }

    public long getOwnerId() { return this.ownerId; }

    public String toString() {
        return "NewServerForm{" +
            "name='" + this.name + "'" +
            ", ip='" + this.ip + "'" +
            ", port=" + this.port +
            ", description='" + this.description + "'" +
            ", bannerImage='" + this.bannerImage + "'" +
            ", logoImage='" + this.logoImage + "'" +
            ", tags=" + this.tags +
            ", websiteURL='" + this.websiteURL + "'" +
            ", discordURL='" + this.discordURL + "'" +
            ", minecraftVersion='" + this.minecraftVersion + "'" +
            ", ownerId=" + this.ownerId +
            "}";
    }
}
