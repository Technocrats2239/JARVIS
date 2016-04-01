package com.deanveloper.slackapi.event.file

import com.deanveloper.slackapi.message.SlackFile
import java.time.Instant

class FilePrivatizeEvent(file: SlackFile, ts: Instant) : FileEvent {
    override val name = "file_private";
    override val file = file;
    override val ts = ts;
}