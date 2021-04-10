package com.chattriggers.ctjs.minecraft.wrappers.objects

import com.chattriggers.ctjs.utils.kotlin.MCEntityLivingBase
import com.chattriggers.ctjs.utils.kotlin.External

@External
open class EntityLivingBase(override val entity: MCEntityLivingBase) : Entity(entity) {
    /**
     * Gets the entity's health
     *
     * @return the entity's health
     */
    fun getHP() = this.entity.health

    /**
     * Gets the entity's maximum health
     *
     * @return the entity's max health
     */
    fun getMaxHP() = this.entity.maxHealth

    /**
     * Get's all the entity's active potion effects
     *
     * @return A list of PotionEffect wrappers
     * @see PotionEffect
     */
    fun getActivePotionEffects(): List<PotionEffect> {
        return entity.activePotionEffects.map {
            PotionEffect(it)
        }
    }
}