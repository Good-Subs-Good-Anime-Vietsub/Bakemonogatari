
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
    episodes(getList("episodes"))

    merge {
        from(get("dialogue")) {
            incrementLayer(12)
        }
        from(getList("TS"))

        includeExtraData(false)
        includeProjectGarbage(false)
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
