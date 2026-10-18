package io.papermc.hangar.model.internal.api.requests.servers;

import io.papermc.hangar.controller.validations.Validate;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class UpdateServerForm {

    // @el(root: String)
    @NotNull(message = "server.edit.error.invalidName")
    private final String name;

    // @el(root: String)
    private final String ip;

    // @el(root: Integer)
    @NotNull(message = "server.edit.error.portRequired")
    private final Integer port;

    // @el(root: String)
    private final @Validate(SpEL = "@validate.max(#root, 5000)", message = "server.edit.error.descriptionTooLong") String description;

    // @el(root: String)
    private final @Validate(SpEL = "@validate.max(#root, 255)", message = "server.edit.error.bannerImageTooLong") String bannerImage;

    // @el(root: String)
    private final @Validate(SpEL = "@validate.max(#root, 255)", message = "server.edit.error.logoImageTooLong") String logoImage;

    // @el(root: List<String>)
    private final @Validate(SpEL = "@validate.max(#root, 20)", message = "server.edit.error.tooManyTags") List<String> tags;

    // @el(root: String)
    private final @Validate(SpEL = "@validate.max(#root, 255)", message = "server.edit.error.websiteUrlTooLong") String websiteURL;

    // @el(root: String)
    private final @Validate(SpEL = "@validate.max(#root, 255)", message = "server.edit.error.discordUrlTooLong") String discordURL;

    // @el(root: String)
    @NotNull(message = "server.edit.error.minecraftVersionRequired")
    private final @Validate(SpEL = "@validate.max(#root, 50)", message = "server.edit.error.minecraftVersionLength") String minecraftVersion;


    public UpdateServerForm(final String name, final String ip, final Integer port, final String description, final String bannerImage, final String logoImage, final List<String> tags, final String websiteURL, final String discordURL, final String minecraftVersion) {
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

    public String toString() {
        return "UpdateServerForm{" +
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
            "}";
    }
}
