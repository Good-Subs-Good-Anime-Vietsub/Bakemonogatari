import myaa.subkt.ass.*
import myaa.subkt.tasks.*
import myaa.subkt.tasks.Mux.*
import myaa.subkt.tasks.Nyaa.*
import java.awt.Color
import java.time.*

plugins {
    id("myaa.subkt")
}

subs {
    readProperties("sub.properties")
    release(arg("release") ?: "BD")
    episodes(getList("episodes"))

    merge {
        from(get("dialogue")) {
            incrementLayer(12)
        }
        
        /*
        if (propertyExists("OP")) {
            from(get("OP")) {
                syncSourceLine("sync")
                syncTargetLine("opsync")
            }
        }

        if (propertyExists("ED")) {
            from(get("ED")) {
                syncSourceLine("sync")
                syncTargetLine("edsync")
            }
        }
        */

        from(getList("TS"))

        includeExtraData(false)
        includeProjectGarbage(false)

        scriptInfo {
                title = "GSGA"
                scaledBorderAndShadow = true
        }

        out(get("mergedname"))
    }

    mux {
        title(get("title"))

        from(get("raw")) {
            video {
                name("BDRip by Beatrice")
                lang("jpn")
                default(true)
            }
            audio(0) {
                name("Japanese 2.0 Opus")
                lang("jpn")
                default(true)
            }
            audio(1) {
                name("Commentary 2.0 Opus")
                lang("jpn")
                default(true)    
            }
            subtitles {
                include(false)
            }
            attachments {
                include(false)
            }
        }

        from(merge.item()) {
            tracks {
                name(get("group"))
                lang("vie")
                default(true)
                forced(false)
                compression(CompressionType.ZLIB)
            }
        }

        attach(get("fonts")) {
            includeExtensions("ttf", "otf")
        }
        skipUnusedFonts(true)
        verifyFonts(false)
        onMissingFonts(ErrorMode.IGNORE)
        out(get("muxout"))
    }
}
