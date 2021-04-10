package com.chattriggers.ctjs.minecraft.wrappers.objects

import com.chattriggers.ctjs.minecraft.wrappers.Client
import com.chattriggers.ctjs.utils.kotlin.MCEntityPlayer
import net.minecraft.client.network.NetworkPlayerInfo

abstract class EntityPlayer(val player: MCEntityPlayer) : EntityLivingBase(player) {
    /**
     * Gets the players ping, -1 if not available
     *
     * @return the player's ping
     */
    fun getPing(): Int {
        return getPlayerInfo()?.responseTime ?: -1
    }

    private fun getPlayerInfo(): NetworkPlayerInfo? = Client.getConnection().getPlayerInfo(this.player.uniqueID)
}