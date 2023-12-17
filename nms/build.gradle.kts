plugins {
    alias(libs.plugins.fabric.loom)
}

loom {
// disabled due to https://github.com/FabricMC/fabric-loom/issues/966
// serverOnlyMinecraftJar()
}

dependencies {
    api(projects.api)
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())
}
