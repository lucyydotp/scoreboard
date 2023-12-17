plugins {
    id("me.lucyydotp.minecraft.paper") version "1.0-SNAPSHOT"
    `java-library`
}

paper {
    version = libs.versions.minecraft
    mainClass = "me.lucyydotp.scoreboard.paper.ScoreboardPaperPlugin"
}

dependencies {
    shadowApi(projects.nms) {
        targetConfiguration = "namedElements"
        exclude("net.kyori")
    }
}
