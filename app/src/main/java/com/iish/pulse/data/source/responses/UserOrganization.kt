package com.iish.pulse.data.source.responses

import com.iish.pulse.utils.enums.PulseUserRules

data class UserOrganization(
    val id: String?,
    val name:String?,
    val description:String?,
    val files:Collection<String>?,
    val latLon:List<Double>,
    val paySubscription: Subscription?
) {
    fun containsRule(rule: PulseUserRules): Boolean {
        val count = paySubscription?.rules?.first {
            it.rule == rule.toString()
        }?.count
        return count != null && count>0
    }
}
