package io.papermc.hangar.model.db.servers;

import io.papermc.hangar.model.Identified;
import io.papermc.hangar.model.Named;
import io.papermc.hangar.model.db.projects.ProjectOwner;
import io.papermc.hangar.model.identified.UserIdentified;

// inherits methods like getId() or getUserId() from ProjectOwner
public interface ServerOwner extends UserIdentified, Named, Identified {
}
