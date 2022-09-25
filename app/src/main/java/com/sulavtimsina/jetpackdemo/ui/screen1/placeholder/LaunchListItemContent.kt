package com.sulavtimsina.jetpackdemo.ui.screen1.placeholder

/**
 * Helper class for providing sample tv_rocket_name for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object LaunchListItemContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<LaunchListItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, LaunchListItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createPlaceholderItem(i))
        }
    }

    private fun addItem(item: LaunchListItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.missionName, item)
    }

    private fun createPlaceholderItem(position: Int): LaunchListItem {
        return LaunchListItem(position.toString(), "Item " + position, makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A placeholder item representing a piece of tv_rocket_name.
     */
    data class LaunchListItem(
        val missionName: String = "Mission Name",
        val rocketName: String ="Rocket Name",
        val launchSiteName: String = "Launch Site Name",
        val dateOfLaunch: String = "2021-01-22",
        val launchPatchImageUrl: String = "http"
    ) {
        override fun toString(): String =
            "Mission: $missionName Rocket: $rocketName Launch Site Name: $launchSiteName Date of Launch: $dateOfLaunch"
    }
}