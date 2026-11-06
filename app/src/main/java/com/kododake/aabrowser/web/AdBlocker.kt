package com.kododake.aabrowser.web

import android.webkit.WebResourceResponse
import java.io.ByteArrayInputStream

/** Simple host-based ad/tracker blocker. Add more hosts to BLOCKED_HOSTS as needed. */
object AdBlocker {
    @Volatile
    var enabled: Boolean = true

    private val BLOCKED_HOSTS = setOf(
        "doubleclick.net",
        "googlesyndication.com",
        "googleadservices.com",
        "adservice.google.com",
        "google-analytics.com",
        "googletagmanager.com",
        "googletagservices.com",
        "adnxs.com",
        "taboola.com",
        "outbrain.com",
        "criteo.com",
        "scorecardresearch.com",
        "moatads.com",
        "amazon-adsystem.com",
        "ads.pubmatic.com",
        "rubiconproject.com",
        "openx.net",
        "adsrvr.org",
        "popads.net",
        "propellerads.com"
    )

    fun shouldBlock(host: String?): Boolean {
        if (!enabled || host.isNullOrEmpty()) return false
        val h = host.lowercase()
        return BLOCKED_HOSTS.any { h == it || h.endsWith(".$it") }
    }

    fun emptyResponse(): WebResourceResponse =
        WebResourceResponse("text/plain", "utf-8", ByteArrayInputStream(ByteArray(0)))
}
