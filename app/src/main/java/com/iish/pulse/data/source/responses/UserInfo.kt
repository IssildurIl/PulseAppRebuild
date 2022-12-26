package com.iish.pulse.data.source.responses

import com.iish.pulse.utils.enums.PulseUserRules

data class UserInfo(
    val preview: UserPreview,
    val paySubscription: Subscription?,
    val basicRules: Collection<String>?,
    val organizations: Collection<UserOrganization?>
) {


    private fun containsRule(rule: PulseUserRules): Boolean {
        if (basicRules?.contains(rule.name) == true) {
            return true
        }
        return paySubscription?.rules?.map {
            it.rule
        }?.contains(rule.toString()) ?: false
    }


    fun containsGlobalRule(rule: PulseUserRules): Boolean {
        return if (containsRule(rule))
            true
        else {
            organizations.flatMap {
                it?.paySubscription?.rules?.map { r -> r.rule }!!
            }.contains(rule.toString())
        }
    }
}